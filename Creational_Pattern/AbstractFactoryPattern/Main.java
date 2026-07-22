//========= Interfaces =========
interface PaymentGateway{
    void processPayment(double amount);
}

interface Invoice{
    void generateInvoice();
}

// ========= India Implementations =========
class RazopayGateway implements PaymentGateway{
    public void processPayment(double amount){
        System.out.println("Processing INR payment via Razopay: Rs." + amount);
    }
}

class PayUGateway implements PaymentGateway{
    public void processPayment(double amount){
        System.out.println("Processing INR payment via PayU: Rs." + amount);
    }
}

class GSTInvoice implements Invoice{
    public void generateInvoice(){
        System.out.println("Generating GST invoice for India.");
    }
}

// ========= US Implementations =========
class StripeGateway implements PaymentGateway{
    public void processPayment(double amount){
        System.out.println("Processing USD payment via Stripe: $" + amount);
    }
}

class PayPalGateway implements PaymentGateway{
    public void processPayment(double amount){
        System.out.println("Processing USD payment via PayPal: $" + amount);
    }
}

class USInvoice implements Invoice{
    public void generateInvoice(){
        System.out.println("Generating  Invoice for the US norms.");
    }
}

//========= Abstract Factory Interface =========
interface RegionFactory{
    PaymentGateway createPaymentGateway(String gatewayType);
    Invoice createInvoice();
}


// ========= Concrete Factories ==========
class IndiaFactory implements RegionFactory{
    public PaymentGateway createPaymentGateway(String gatewayType){
        switch(gatewayType.toLowerCase()){
            case "razopay":
                return new RazopayGateway();
            case "payu":
                return new PayUGateway();
            default:
                throw new IllegalArgumentException("Unsupported payment gateway in India: " + gatewayType);
        }
    }

    public  Invoice createInvoice(){
        return new GSTInvoice();
    }
}

class USFactory implements RegionFactory{

    public PaymentGateway createPaymentGateway(String gatewayType){
        switch(gatewayType.toLowerCase()){
            case "stripe":
                return new StripeGateway();
            case "paypal":
                return new PayPalGateway();
            default:
                throw new IllegalArgumentException("Unsupported payment gateway for US: " + gatewayType);
        }
    }

    public Invoice createInvoice(){
        return new USInvoice();
    }
}

//========= Checkout Service =========
class CheckoutService{
    private PaymentGateway paymentGateway;
    private Invoice invoice;
    private String gatewayType;

    public CheckoutService(RegionFactory factory, String gatewayType){
        this.gatewayType = gatewayType;
        this.paymentGateway = factory.createPaymentGateway(gatewayType);
        this.invoice = factory.createInvoice();
    }

    public void completeOrder(double amount){
        paymentGateway.processPayment(amount);
        invoice.generateInvoice();
    }
}

//========= Main Class =========
public class Main{
    public static void main(String [] args){
        //Using Razorpay in India
        CheckoutService indiaCheckout = new CheckoutService(new IndiaFactory(), "razopay");
         indiaCheckout.completeOrder(1000);

        //Using PayPal in US
        CheckoutService usCheckout = new CheckoutService(new USFactory(), "paypal");
        usCheckout.completeOrder(100);
    }
}
