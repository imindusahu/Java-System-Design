import java.util.*;

//Item Interface
interface Item {
    void accept(ItemVisitor visitor);
}

//Concrete Element
class PhysicalProduct implements Item {
    String name;
    double weight;

    public PhysicalProduct(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }
}

class DigitalProduct implements Item {
    String name;
    int downloadSizeInMB;

    public DigitalProduct(String name, int downloadSizeInMB) {
        this.name = name;
        this.downloadSizeInMB = downloadSizeInMB;
    }

    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }
}

class GiftCard implements Item {
    String code;
    double amount;

    public GiftCard(String code, double amount) {
        this.code = code;
        this.amount = amount;
    }

    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }
}

//Visitor Interface
interface ItemVisitor {
    void visit(PhysicalProduct item);
    void visit(DigitalProduct item);
    void visit(GiftCard item);
}

class InvoiceVisitor implements ItemVisitor {
    public void visit(PhysicalProduct item) {
        System.out.println("Invoice: " + item.name + " - Shipping to customer");
    }

    public void visit(DigitalProduct item) {
        System.out.println("Invoice: " + item.name + " - Email with download link");
    }

    public void visit(GiftCard item) {
        System.out.println("Invoice: " + item.code + " - Gift card");
    }
}

class ShippingCostVisitor implements ItemVisitor {
    public void visit(PhysicalProduct item) {
        System.out.println("Shipping cost for " + item.name + ": Rs." + (item.weight * 10));
    }

    public void visit(DigitalProduct item) {
        System.out.println(item.name + " is digital - No shipping cost.");
    }

    public void visit(GiftCard item) {
        System.out.println("GiftCard delivery via email - No shipping cost.");
    }
}


public class Main {
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new PhysicalProduct("Laptop", 2.5));
        items.add(new DigitalProduct("E-book", 50));
        items.add(new GiftCard("GIFT123", 100));

        ItemVisitor invoiceGenerator = new InvoiceVisitor();
        ItemVisitor shippingCalculator = new ShippingCostVisitor();

        System.out.println("Generating Invoices:");
        for (Item item : items) {
            item.accept(invoiceGenerator);
        }

        System.out.println("\nCalculating Shipping Costs:");
        for (Item item : items) {
            item.accept(shippingCalculator);
        }
    }
}
