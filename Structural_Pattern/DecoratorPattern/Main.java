import java.util.*;

class PlainPizza {}
class CheesePizza extends PlainPizza {}
class OlivePizza extends PlainPizza {}
class StuffedPizza extends PlainPizza {}
class CheeseStuffedPizza extends CheesePizza {}
class CheeseOlivePizza extends CheesePizza {}
class CheeseOliveStuffedPizza extends CheeseOlivePizza {}
// N, 2^N

interface Pizza {
    String getDescription();
    double getCost();
}

//Concrete components
class MargheritaPizza implements Pizza {
    @Override
    public String getDescription(){
        return "Margherita Pizza";
    }

    @Override
    public double getCost(){
        return 200.0;
    }
}

//Decorator Abstract Class
abstract class PizzaDecorator implements Pizza{
    protected Pizza pizza;  // Has-a relationship

    public PizzaDecorator(Pizza pizza){
        this.pizza = pizza;
    }
}

class ExtraCheese extends PizzaDecorator{
    public ExtraCheese(Pizza pizza){
        super(pizza);
    }

    @Override
    public String getDescription(){
        return pizza.getDescription() + ", Extra Cheese";
    }

    @Override
    public double getCost(){
        return pizza.getCost() + 40.0;
    }
}

class Olives extends PizzaDecorator{
    public Olives(Pizza pizza){
        super(pizza);
    }

    @Override
    public String getDescription(){
        return pizza.getDescription() + ", Olives";
    }

    @Override
    public double getCost(){
        return pizza.getCost() + 30.0;
    }
}

public class Main {
    public static void main(String[] args){
       Pizza margheritaCheesePizza = new ExtraCheese(new MargheritaPizza());
       Pizza oliveCheese = new Olives(margheritaCheesePizza);
       System.out.println(oliveCheese.getCost());
    }
}