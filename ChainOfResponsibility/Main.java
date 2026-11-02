package ChainOfResponsibility;

    
class Request {
    boolean authenticated;
    boolean authorized;
    boolean valid;

    public Request(boolean authenticated,
                    boolean authorized,
                    boolean valid) {
        this.authenticated = authenticated;
        this.authorized = authorized;
        this.valid = valid;
    }
}

// 1. Abstract Middleware
abstract class Middleware {

    private Middleware next;

    public Middleware linkWith(Middleware next) {
        this.next = next;
        return next;
    }

    public boolean handle(Request request) {

        if (!check(request)) {
            return false;
        }

        if (next == null) {
            return true;
        }

        return next.handle(request);
    }

    protected abstract boolean check(Request request);
}

// 2. Authentication Middleware
class AuthenticationMiddleware extends Middleware {

    protected boolean check(Request request) {
        System.out.println("Checking authentication...");

        if (!request.authenticated) {
            System.out.println("Authentication failed!");
            return false;
        }

        System.out.println("Authentication successful!");
        return true;
    }
}

// 3. Authorization Middleware
class AuthorizationMiddleware extends Middleware {

    protected boolean check(Request request) {
        System.out.println("Checking authorization...");

        if (!request.authorized) {
            System.out.println("Authorization failed!");
            return false;
        }

        System.out.println("Authorization successful!");
        return true;
    }
}

// 4. Logging Middleware
class LoggingMiddleware extends Middleware {

    protected boolean check(Request request) {
        System.out.println("Logging request...");
        return true;
    }
}

// 5. Validation Middleware
class ValidationMiddleware extends Middleware {

    protected boolean check(Request request) {
        System.out.println("Validating request...");

        if (!request.valid) {
            System.out.println("Request validation failed!");
            return false;
        }

        System.out.println("Request validation successful!");
        return true;
    }
}

// 6. Client
public class Main {

    public static void main(String[] args) {

        Middleware authentication =
                new AuthenticationMiddleware();

        Middleware authorization =
                new AuthorizationMiddleware();

        Middleware logging =
                new LoggingMiddleware();

        Middleware validation =
                new ValidationMiddleware();

        // Creating the chain
        authentication
                .linkWith(authorization)
                .linkWith(logging)
                .linkWith(validation);

        System.out.println("---- Request 1 ----");

        Request request1 = new Request(true, true, true);

        boolean result1 = authentication.handle(request1);

        System.out.println("Request accepted: " + result1);

        System.out.println();

        System.out.println("---- Request 2 ----");

        Request request2 = new Request(false, true, true);

        boolean result2 = authentication.handle(request2);

        System.out.println("Request accepted: " + result2);

        System.out.println();

        System.out.println("---- Request 3 ----");

        Request request3 = new Request(true, true, false);

        boolean result3 = authentication.handle(request3);

        System.out.println("Request accepted: " + result3);
    }
}
