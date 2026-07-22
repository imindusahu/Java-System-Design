import java.util.*;

interface CartItem{
    double getPrice();
    void display(String indent);
}

class Product implements CartItem {
    private String name;
    private double price;

    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice(){
        return price;
    }
    
    @Override
    public void display(String indent){
        System.out.println(indent + "Product: " + name + " - Rs. " + price);
    }
}

class ProductBundle implements CartItem {
    private String bundleName;
    private List<CartItem> items = new ArrayList<>();

    public ProductBundle(String bundleName){
        this.bundleName = bundleName;
    }

    public void addItem(CartItem item){
        items.add(item);
    }

    @Override
    public double getPrice(){
        double total = 0;
        for(CartItem item : items){
            total += item.getPrice();
        }
        return total;
    }

    @Override
    public void display(String indent){
        System.out.println(indent + "Bundle: " + bundleName);
        for(CartItem item : items){
            item.display(indent + " ");
        }
    }
}

public class Main{
    public static void main(String[] args){
        //Individual items
        CartItem book = new Product("Atomic Habits", 499);
        CartItem phone = new Product("iPhone 15", 79999);
        CartItem earbuds = new Product("AirPods", 15999);
        CartItem charger = new Product("20W Charger", 1999);

        //Bundle: iPhone Combo
        ProductBundle iphoneCombo = new ProductBundle("iPhone Essentials Combo");
        iphoneCombo.addItem(phone);
        iphoneCombo.addItem(earbuds);
        iphoneCombo.addItem(charger);

        //Bundle: School Kit
        ProductBundle schoolKit = new ProductBundle("Back to School Kit");
        schoolKit.addItem(new Product("Notebook Pack", 249));
        schoolKit.addItem(new Product("Pen Set", 99));
        schoolKit.addItem(new Product("Highlighter", 149));

        //Add to cart - problem begins!
        List<CartItem> cart = new ArrayList<>();
        cart.add(book);
        cart.add(iphoneCombo);
        cart.add(schoolKit);

        //Display cart
        System.out.println("Your Cart (without Composite Pattern):");
        double total = 0;
        for(CartItem item : cart){

            if(item instanceof Product){
                Product product = (Product) item;
                product.display(" ");
                total += product.getPrice();
            } else if(item instanceof ProductBundle){
                ProductBundle bundle = (ProductBundle) item;
                bundle.display(" ");
                total += bundle.getPrice();
            }
        }
        System.out.println("\n Total: Rs. " + total);
    }
}