import java.util.*;

abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String RequestType);
}

class GeneralSupport extends SupportHandler {
    public void handleRequest(String requestType) {
        if(requestType.equalsIgnoreCase("general")) {
            System.out.println("GeneralSupport: Handling general query.");
        } else if(nextHandler != null) {
            nextHandler.handleRequest(requestType);
        }
    }
}

class BillingSupport extends SupportHandler {
    public void handleRequest(String requestType) {
        if(requestType.equalsIgnoreCase("refund")) {
            System.out.println("BillingSupport: Handling refund request.");
        } else if(nextHandler != null) {
            nextHandler.handleRequest(requestType);
        }
    }
}

class TechnicalSupport extends SupportHandler {
    public void handleRequest(String requestType) {
        if(requestType.equalsIgnoreCase("technical")) {
            System.out.println("TechnicalSupport: Handling technical issue.");
        } else if(nextHandler != null) {
            nextHandler.handleRequest(requestType);
        }
    }
}

class DeliverySupport extends SupportHandler {
    public void handleRequest(String requestType) {
        if(requestType.equalsIgnoreCase("delivery")) {
            System.out.println("DeliverySupport: Handling delivery issue.");
        } else if(nextHandler != null) {
            nextHandler.handleRequest(requestType);
        } else {
            System.out.println("DeliverySupport: No handler found for request");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Create handlers
        SupportHandler general = new GeneralSupport();
        SupportHandler billing = new BillingSupport();
        SupportHandler technical = new TechnicalSupport();
        SupportHandler delivery = new DeliverySupport();

        // Set up the chain of responsibility: general -> billing -> technical -> delivery
        general.setNextHandler(billing);
        billing.setNextHandler(technical);
        technical.setNextHandler(delivery);

        // Test the chain with different requests
        String[] requests = {"general", "refund", "technical", "delivery", "unknown"};

        for(String request : requests) {
            System.out.println("Requesting: " + request);
            general.handleRequest(request);
            System.out.println();
        }
    }
}



