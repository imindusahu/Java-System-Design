import java.util.*;
//BurgerMeal
//Bun
//Patty

//sides
//toppings
//cheese

class BurgerMeal{
    //Mandatory
    private String bunType;
    private String patty;


    //Optional
    private final  boolean hasCheese;
    private final List<String> toppings;
    private final String side;
    private final String drink;

    private BurgerMeal(BurgerBuilder builder){
        this.bunType = builder.bunType;
        this.patty = builder.patty; 
        this.hasCheese = builder.hasCheese;
        this.toppings = builder.toppings;
        this.side = builder.side;
        this.drink = builder.drink;
    }

    public static class BurgerBuilder{
        //Mandatory
        private final String bunType;
        private final String patty;

        //Optional
        private boolean hasCheese;
        private List<String> toppings = new ArrayList<>();
        private String side;
        private String drink;

        public BurgerBuilder(String bunType, String patty){
            this.bunType = bunType;
            this.patty = patty;
        }

        public BurgerBuilder withCheese(boolean hasCheese){
            this.hasCheese = hasCheese;
            return this;
        }

        public BurgerBuilder withTopping(List<String> toppings){
            this.toppings = toppings;
            return this;
        }

        public BurgerBuilder withSide(String side){
            this.side = side;
            return this;
        }

        public BurgerBuilder withDrink(String drink){
            this.drink = drink;
            return this;
        }

        public BurgerMeal build(){
            return new BurgerMeal(this);
        }
    }
}

public class Main{
    public static void main(String[] args){

        BurgerMeal plainBurger = new BurgerMeal.BurgerBuilder("wheat", "veg").build();

        // Burger with cheese only
        BurgerMeal cheeseBurger = new BurgerMeal.BurgerBuilder("wheat", "veg").withCheese(true).build();

        // Fully loaded burger
        List<String> toppings = Arrays.asList("tomato", "onion", "jalapeno");
        BurgerMeal loadedBurger = new BurgerMeal.BurgerBuilder("multigrain", "chicken").withCheese(true).withTopping(toppings).withSide("fries").withDrink("coke").build(); 
    }
}