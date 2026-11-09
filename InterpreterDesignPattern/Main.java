package InterpreterDesignPattern;
import java.util.*;

// 1. CONTEXT
// Stores the arithmetic expression and current reading position.
class Context {

    private String expression;
    private int position;

    public Context(String expression) {
        this.expression = expression;
        this.position = 0;
    }

    public String getExpression() {
        return expression;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void skipSpaces() {
        while (position < expression.length()
                && Character.isWhitespace(expression.charAt(position))) {
            position++;
        }
    }

    public boolean hasNext() {
        skipSpaces();
        return position < expression.length();
    }

    public char peek() {
        skipSpaces();
        if (!hasNext()) {
            return '\0';
        }
        return expression.charAt(position);
    }

    public char consume() {
        char current = peek();
        if (current != '\0') {
            position++;
        }
        return current;
    }
}

// 2. ABSTRACT EXPRESSION
interface AbstractExpression {
    int interpret(Context context);
}

// 3. TERMINAL EXPRESSION
// Represents a number.
class NumberExpression implements AbstractExpression {

    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret(Context context) {
        return number;
    }
}

// 4. NON-TERMINAL EXPRESSIONS

// Addition
class AddExpression implements AbstractExpression {

    private AbstractExpression left;
    private AbstractExpression right;

    public AddExpression(AbstractExpression left,
                         AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        return left.interpret(context) + right.interpret(context);
    }
}

// Subtraction
class SubtractExpression implements AbstractExpression {

    private AbstractExpression left;
    private AbstractExpression right;

    public SubtractExpression(AbstractExpression left,
                              AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        return left.interpret(context) - right.interpret(context);
    }
}

// Multiplication
class MultiplyExpression implements AbstractExpression {

    private AbstractExpression left;
    private AbstractExpression right;

    public MultiplyExpression(AbstractExpression left,
                              AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        return left.interpret(context) * right.interpret(context);
    }
}

// Division
class DivideExpression implements AbstractExpression {

    private AbstractExpression left;
    private AbstractExpression right;

    public DivideExpression(AbstractExpression left,
                            AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {

        int divisor = right.interpret(context);

        if (divisor == 0) {
            throw new ArithmeticException("Cannot divide by zero!");
        }

        return left.interpret(context) / divisor;
    }
}

// 5. EXPRESSION PARSER
// Builds the expression tree while respecting operator precedence.
class ExpressionParser {

    private Context context;

    public ExpressionParser(Context context) {
        this.context = context;
    }

    // Handles + and -
    public AbstractExpression parseExpression() {

        AbstractExpression result = parseTerm();

        while (context.hasNext()) {

            char operator = context.peek();

            if (operator == '+') {
                context.consume();
                result = new AddExpression(result, parseTerm());

            } else if (operator == '-') {
                context.consume();
                result = new SubtractExpression(result, parseTerm());

            } else {
                break;
            }
        }

        return result;
    }

    // Handles * and /
    private AbstractExpression parseTerm() {

        AbstractExpression result = parseFactor();

        while (context.hasNext()) {

            char operator = context.peek();

            if (operator == '*') {
                context.consume();
                result = new MultiplyExpression(result, parseFactor());

            } else if (operator == '/') {
                context.consume();
                result = new DivideExpression(result, parseFactor());

            } else {
                break;
            }
        }

        return result;
    }

    // Handles numbers and parentheses
    private AbstractExpression parseFactor() {

        char current = context.peek();

        if (current == '(') {

            context.consume();

            AbstractExpression result = parseExpression();

            if (context.consume() != ')') {
                throw new IllegalArgumentException(
                    "Missing closing parenthesis!"
                );
            }

            return result;
        }

        if (current == '-') {
            context.consume();
            return new SubtractExpression(
                new NumberExpression(0),
                parseFactor()
            );
        }

        if (Character.isDigit(current)) {

            StringBuilder number = new StringBuilder();

            while (context.hasNext()
                    && Character.isDigit(context.peek())) {
                number.append(context.consume());
            }

            return new NumberExpression(
                Integer.parseInt(number.toString())
            );
        }

        throw new IllegalArgumentException(
            "Invalid expression at position " + context.getPosition()
        );
    }
}

// 6. MAIN CLASS
public class Main {

    public static void main(String[] args) {

        String input = "10 + 5 * 2";

        Context context = new Context(input);

        ExpressionParser parser = new ExpressionParser(context);

        try {

            AbstractExpression expression =
                parser.parseExpression();

            if (context.hasNext()) {
                throw new IllegalArgumentException(
                    "Unexpected character: " + context.peek()
                );
            }

            int result = expression.interpret(context);

            System.out.println("Expression: " + input);
            System.out.println("Result: " + result);

        } catch (ArithmeticException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}