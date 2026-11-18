package StrategyDesignPattern;


 // 1. STRATEGY INTERFACE
interface PaymentStrategy {
    void pay(double amount);
}

// 2. CREDIT CARD STRATEGY
class CreditCardPayment implements PaymentStrategy {

    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Processing Credit Card Payment...");
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Amount Paid: Rs. " + amount);
    }
}

// 3. PAYPAL STRATEGY
class PayPalPayment implements PaymentStrategy {

    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Processing PayPal Payment...");
        System.out.println("PayPal Email: " + email);
        System.out.println("Amount Paid: Rs. " + amount);
    }
}

// 4. BITCOIN STRATEGY
class BitcoinPayment implements PaymentStrategy {

    private String walletAddress;

    public BitcoinPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Processing Bitcoin Payment...");
        System.out.println("Wallet Address: " + walletAddress);
        System.out.println("Amount Paid: Rs. " + amount);
    }
}

// 5. CONTEXT CLASS
class PaymentContext {

    private PaymentStrategy paymentStrategy;

    // Change payment strategy at runtime
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Process payment using selected strategy
    public void makePayment(double amount) {

        if (paymentStrategy == null) {
            System.out.println("Please select a payment method!");
            return;
        }

        paymentStrategy.pay(amount);
    }
}

// 6. MAIN CLASS
public class Main {

    public static void main(String[] args) {

        PaymentContext paymentContext = new PaymentContext();

        // Payment using Credit Card
        System.out.println("===== CREDIT CARD PAYMENT =====");

        paymentContext.setPaymentStrategy(
                new CreditCardPayment("1234-5678-9012-3456")
        );

        paymentContext.makePayment(1000);

        // Payment using PayPal
        System.out.println("\n===== PAYPAL PAYMENT =====");

        paymentContext.setPaymentStrategy(
                new PayPalPayment("user@gmail.com")
        );

        paymentContext.makePayment(2000);

        // Payment using Bitcoin
        System.out.println("\n===== BITCOIN PAYMENT =====");

        paymentContext.setPaymentStrategy(
                new BitcoinPayment("BTC-WALLET-12345")
        );

        paymentContext.makePayment(3000);
    }
}
