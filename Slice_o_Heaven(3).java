import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;


public class Slice_o_Heaven {

    private String orderID;
    private double orderTotal;
    public String storeName;
    public String storeAdddress;
    public String storeEmail;
    public long storePhone;
    public String storeMenu;
    private int orderIndex = 0;

    public static final String DEF_ORDER_ID = "DEF-SOH-099";
    public static final double DEF_ORDER_TOTAL = 0.0;

    
    public Slice_o_Heaven() {
        this.orderID = DEF_ORDER_ID;
            this.orderTotal = DEF_ORDER_TOTAL;
        }

    
    public Slice_o_Heaven(String orderID, double orderTotal) {
        this.orderID = orderID;
        this.orderTotal = orderTotal;
    }
    
    public String getOrderID() {
        return orderID;
    }

    
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    
    public double getOrderTotal() {
        return orderTotal;
    }

    
    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public boolean isValidNumber(String input)
    {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public void takeOrder() {
        Scanner sc = new Scanner(System.in);
        boolean continueOrdering = true;
        int index;
        while(continueOrdering){
            index = 1;
        System.out.println("Welcome to Slice-o-Heaven Pizzeria. Here’s what we serve:");
        for (PizzaSelection pizza : PizzaSelection.values()) {
            System.out.println(index + ". " + pizza.getpizzaName() + " Pizza with " + pizza.getpizzaTopping() + ", for €" + pizza.getPrice());
            index++;
        }
        System.out.println(index + ". Custom Pizza with a maximum of 10 toppings that you choose");
        System.out.print("Please enter your choice (1 - " + index + "): ");
        String pizzaChoiceStr = sc.nextLine();
        if(!isValidNumber(pizzaChoiceStr)) {
            System.out.println("Invalid input. Please enter a valid number.");
            continue;
        }
        int pizzaChoice = Integer.parseInt(pizzaChoiceStr);

        if (pizzaChoice >= 1 && pizzaChoice <= PizzaSelection.values().length) {
            PizzaSelection selectedPizza = PizzaSelection.values()[pizzaChoice - 1];
            pizzasOrdered[orderIndex] = selectedPizza.getpizzaName() + " Pizza with " + selectedPizza.getpizzaTopping() + ", for €" + selectedPizza.getPrice();
            orderTotal += selectedPizza.getPrice();
        } else if (pizzaChoice == PizzaSelection.values().length + 1) {

            double customPizzaPrice = PIZZA_BASE_PRICE;
            StringBuilder customPizzaDesc = new StringBuilder("Custom Pizza with ");
            System.out.println("Please choose your toppings (enter numbers separated by spaces, max 10 choices):");
            System.out.println("Toppings:");
            for (int i = 0; i < PizzaToppings.values().length; i++) {
                System.out.println((i + 1) + ". " + PizzaToppings.values()[i].gettopping() + " (€" + PizzaToppings.values()[i].gettoppingPrice() + ")");
            }
            String[] toppingchoices = sc.nextLine().split(" ");
            int validToppingCount = 0;
                for (String toppingChoice : toppingchoices) {
                    if (!isValidNumber(toppingChoice)) {
                        continue;
                    }
                    int toppingIndex = Integer.parseInt(toppingChoice) - 1;
                    if (toppingIndex >= 0 && toppingIndex < PizzaToppings.values().length) {
                        customPizzaDesc.append(PizzaToppings.values()[toppingIndex].gettopping()).append(", ");
                        customPizzaPrice += PizzaToppings.values()[toppingIndex].gettoppingPrice();
                        validToppingCount++;
                        if (validToppingCount >= 10) {
                            break;
                        }
                    }
                }
                if (customPizzaDesc.length() > "Custom Pizza with ".length()) {
                    customPizzaDesc.setLength(customPizzaDesc.length() - 2);
                }
                customPizzaDesc.append(", for €").append(customPizzaPrice);
                pizzasOrdered[orderIndex] = customPizzaDesc.toString();
                orderTotal += customPizzaPrice;
            } else {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }


boolean validSizeInput = false;
boolean validSideInput = false;
        while (!validSizeInput) {
            System.out.println("Please choose a side dish:");
            for (SideDish side : SideDish.values()) {
                for (int i = 0; i < SideDish.values().length; i++) {
                    if (SideDish.values()[i] == side) {
                        System.out.println((i + 1) + ". " + side.getSideDishName() + " (€" + side.getAddToPizzaPrice() + ")");
                        break;
                    }
                }
            }
            System.out.print("Enter only one choice (1 - " + SideDish.values().length + "): ");
            String sideInput = sc.nextLine();
            if (isValidNumber(sideInput)) {
                int sideChoice = Integer.parseInt(sideInput);
                if (sideChoice >= 1 && sideChoice <= SideDish.values().length) {
                    SideDish selectedSide = SideDish.values()[sideChoice - 1];
                    sideDishesOrdered[orderIndex] = selectedSide.getSideDishName();
                    orderTotal += selectedSide.getAddToPizzaPrice();
                    validSideInput = true;
                } else {
                    System.out.println("Invalid choice. Please pick only from the given list:");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number from the list:");
            }
        }

        
    boolean validDrinkInput = false;
    while (!validDrinkInput) {
        System.out.println("Please choose a drink:");
        for (Drinks drink : Drinks.values()) {
            for (int i = 0; i < Drinks.values().length; i++) {
                if (Drinks.values()[i] == drink) {
                    System.out.println((i + 1) + ". " + drink.getDrinkName() + " (€" + drink.getAddToPizzaPrice() + ")");
                    break;
                }
            }
        }
        System.out.print("Enter only one choice (1 - " + Drinks.values().length + "): ");
        String drinkInput = sc.nextLine();
        if (isValidNumber(drinkInput)) {
            int drinkChoice = Integer.parseInt(drinkInput);
            if (drinkChoice >= 1 && drinkChoice <= Drinks.values().length) {
                Drinks selectedDrink = Drinks.values()[drinkChoice - 1];
                drinksOrdered[orderIndex] = selectedDrink.getDrinkName();
                orderTotal += selectedDrink.getAddToPizzaPrice();
                validDrinkInput = true;
            } else {
                System.out.println("Invalid choice. Please pick only from the given list:");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number from the list:");
        }
    }

    orderIndex++;

    System.out.print("Do you want to order more? (Y/N): ");
    String moreOrder = sc.nextLine();
    if (!moreOrder.equalsIgnoreCase("Y")) {
        continueOrdering = false;
    }
}
}

        
    
    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder("Thank you for dining with Slice-o-Heaven Pizzeria. Your order details are as follows:\n");
        for (int i = 0; i < orderIndex; i++) {
            receipt.append((i + 1) + ". ").append(pizzasOrdered[i]).append("\n");
            receipt.append(pizzaSizesOrdered[i]).append(": €").append(getSizePrice(pizzaSizesOrdered[i])).append("\n");
            receipt.append(sideDishesOrdered[i]).append(": €").append(getSidePrice(sideDishesOrdered[i])).append("\n");
            receipt.append(drinksOrdered[i]).append(": €").append(getDrinkPrice(drinksOrdered[i])).append("\n\n");
        }
        receipt.append("ORDER TOTAL: €").append(orderTotal);
        return receipt.toString();
    }

    private double getSizePrice(String size) {
        for (PizzaSize pizzaSize : PizzaSize.values()) {
            if (pizzaSize.getPizzaSize().equals(size)) {
                return pizzaSize.getAddToPizzaPrice();
            }
        }
        return 0;
    }

    private double getSidePrice(String side) {
        for (SideDish sideDish : SideDish.values()) {
            if (sideDish.getSideDishName().equals(side)) {
                return sideDish.getAddToPizzaPrice();
            }
        }
        return 0;
    }

    private double getDrinkPrice(String drink) {
        for (Drinks drinks : Drinks.values()) {
            if (drinks.getDrinkName().equals(drink)) {
                return drinks.getAddToPizzaPrice();
            }
        }
        return 0;
    }
    
    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        String cardLength = Long.toString(cardNumber);
        if (cardLength.length() == 14) {
            System.out.println("Card accepted");
        } else {
            System.out.println("Invalid card");
        }


        long blacklistedNumber = 12345678901234L;
        if (cardNumber == blacklistedNumber){
            System.out.println("Card is blacklisted. Please use another card");
        }

        String cardNumberStr = Long.toString(cardNumber);
        long lastFourDigits = Long.parseLong(cardNumberStr.substring(cardNumberStr.length() - 4));
        String cardNumberToDisplay = cardNumberStr.charAt(0) + cardNumberStr.substring(1, cardNumberStr.length() - 4).replaceAll(".", "*") + cardNumberStr.substring(cardNumberStr.length() - 4);
        System.out.println("Display Card Number: " + cardNumberToDisplay);
    }
    public int calculateAge(Date birthdate) {
        Date now = new Date();
        long diff = now.getTime() - birthdate.getTime();
        return (int) (diff / (1000 * 60 * 60 * 24 * 365.242199));
    }

    public void isItYourBirthday() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter your birthday(yyyy-MM-dd):");
            String birthdaydate = sc.nextLine();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date birthdate = sdf.parse(birthdaydate);
                long fiveYearsAgo = System.currentTimeMillis() - 5 * 365 * 24 * 60 * 60 * 1000;
                long oneTwentyYearsAgo = System.currentTimeMillis() - 120 * 365 * 24 * 60 * 60 * 1000;
                if (birthdate.getTime() < fiveYearsAgo || birthdate.getTime() > oneTwentyYearsAgo) {
                    System.out.println("Invalid date. You are either too young or too dead to order. Please enter a valid date:");
                } else {
                    int age = calculateAge(birthdate);
                    if (age < 18 && sdf.format(new Date()).equals(sdf.format(birthdate))) {
                        System.out.println("Congratulations! You pay only half the price for your order");
                        orderTotal /= 2;
                    } else {
                        System.out.println("Too bad! You do not meet the conditions to get our 50% discount");
                    }
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter a valid date(yyyy-MM-dd):");
            }
        }
    }

       public void makeCardPayment(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your card number:");
        long cardNumber = sc.nextLong();

        String expiryDate = "";
        while (true) {
            System.out.println("Please enter the card's expiry date (format: yyyy-MM):");
            expiryDate = sc.next();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            try {
                Date date = sdf.parse(expiryDate);
                if (date.before(new Date())) {
                    System.out.println("Invalid date. The expiry date must be in the future. Please enter the correct date:");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter a valid date (format: yyyy-MM):");
            }
        }

        System.out.println("Please enter the card's cvv number:");
        int cvv = sc.nextInt();

        processCardPayment(cardNumber, expiryDate, cvv);
    }


    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, String specialPrice) {
        String info = "Today's Special - Pizza: " + pizzaOfTheDay + ", Side: " + sideOfTheDay + ", Price: " + specialPrice;
        System.out.println(info);
    }

    enum PizzaSelection{
        PEPPERONI("Pepperoni", "Lots of pepperoni and extra cheese", 18),
        HAWAIIAN("Hawaiian", "Pineapple, ham, and extra cheese", 22),
        VEGGIE("Veggie", "Green pepper, onion, tomatoes, mushroom, and black olives", 25),
        BBQ_CHICKEN("BBQ Chicken", "Chicken in BBQ sauce, bacon, onion, green pepper, and cheddar cheese", 35),
        EXTRAVAGANZA("Extravaganza", "Pepperoni, ham, Italian sausage, beef, onions, green pepper, mushrooms, black olives, and extra cheese", 45);
       
    
       private final String pizzaName;
       private final String pizzaTopping;
       private final int price;
    
       PizzaSelection(String pizzaName,String pizzaTopping,int price){
           this.pizzaName = pizzaName;
           this.pizzaTopping = pizzaTopping;
           this.price = price;
       }
    
       public String getpizzaName(){
           return pizzaName;
       }
    
       public String getpizzaTopping(){
           return pizzaTopping;
       }
    
       public int getPrice(){
           return price;
       }
    
       @Override
       public String toString(){
        return "Pizza Name: " + pizzaName + "\nToppings: " + pizzaTopping + "\nPrice: " + price;
       }
    }
    
    
       enum PizzaToppings {
        HAM("Ham", 2),
        PEPPERONI("Pepperoni", 2),
        BEEF("Beef", 2),
        CHICKEN("Chicken", 2),
        SAUSAGE("Sausage", 2),
        PINEAPPLE("Pineapple", 1),
        ONION("Onion", 0.5),
        TOMATOES("Tomatoes", 0.4),
        GREEN_PEPPER("Green Pepper", 0.5),
        BLACK_OLIVES("Black Olives", 0.5),
        SPINACH("Spinach", 0.5),
        CHEDDAR_CHEESE("Cheddar Cheese", 0.8),
        MOZZARELLA_CHEESE("Mozzarella Cheese", 0.8),
        FETA_CHEESE("Feta Cheese", 1),
        PARMESAN_CHEESE("Parmesan Cheese", 1);
    
        private final String topping;
        private final double toppingPrice;
    
        PizzaToppings(String topping, double toppingPrice){
            this.topping = topping;
            this.toppingPrice = toppingPrice;
        }
        
        public String gettopping(){
            return topping;
        }
    
        public double gettoppingPrice(){
            return toppingPrice;
        }
       
        @Override
        public String toString(){
            return "Topping: " + topping + ", Price: " + toppingPrice;
        }
       }
    
       enum PizzaSize{
        LARGE("Large", 10),
        MEDIUM("Medium", 5),
        SMALL("Small", 0);
    
        private final String pizzaSize;
        private final int addToPizzaPrice;
    
        PizzaSize(String pizzaSize, int addToPizzaPrice) {
            this.pizzaSize = pizzaSize;
            this.addToPizzaPrice = addToPizzaPrice;
        }
    
        public String getPizzaSize() {
            return pizzaSize;
        }
    
        public int getAddToPizzaPrice() {
            return addToPizzaPrice;
        }
    
        @Override
        public String toString() {
            return "Pizza Size: " + pizzaSize + ", Additional Price: " + addToPizzaPrice;
        }
    
    }
    
        enum SideDish {
            CALZONE("Calzone", 15),
            CHICKEN_PUFF("Chicken Puff", 20),
            MUFFIN("Muffin", 12),
            NOTHING("No side dish", 0);
    
            private final String sideDishName;
            private final int addToPizzaPrice;
    
            SideDish(String sideDishName, int addToPizzaPrice) {
                this.sideDishName = sideDishName;
                this.addToPizzaPrice = addToPizzaPrice;
            }
    
            public String getSideDishName() {
                return sideDishName;
            }
    
            public int getAddToPizzaPrice() {
                return addToPizzaPrice;
            }
    
            @Override
            public String toString() {
                return "Side Dish: " + sideDishName + ", Additional Price: " + addToPizzaPrice;
            }
        }
    
        enum Drinks {
            COCA_COLA("Coca Cola", 8),
            COCOA_DRINK("Cocoa Drink", 10),
            NOTHING("No drinks", 0);
    
            private final String drinkName;
            private final int addToPizzaPrice;
    
            Drinks(String drinkName, int addToPizzaPrice) {
                this.drinkName = drinkName;
                this.addToPizzaPrice = addToPizzaPrice;
            }
    
            public String getDrinkName() {
                return drinkName;
            }
    
            public int getAddToPizzaPrice() {
                return addToPizzaPrice;
            }
    
            @Override
            public String toString() {
                return "Drink: " + drinkName + ", Additional Price: " + addToPizzaPrice;
            }
        }
    
        public static final double PIZZA_BASE_PRICE = 10.0;
    
        public static String[] pizzasOrdered = new String[10];
        public static String[] pizzaSizesOrdered = new String[10];
        public static String[] sideDishesOrdered = new String[20];
        public static String[] drinksOrdered = new String[20];
       
}