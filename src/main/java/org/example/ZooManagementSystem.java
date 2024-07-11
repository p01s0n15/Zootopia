package org.example;
import java.util.*;

interface ZooManagement {
    void enterAsAdmin();
    void enterAsVisitor();
}

abstract class Attraction {
    private String name;
    private String description;
    private int attractionId;
    private double ticketPrice;
    private int ticketedVisitors;

    public Attraction(String name, String description, int attractionId, double ticketPrice) {
        this.name = name;
        this.description = description;
        this.attractionId = attractionId;
        this.ticketPrice = ticketPrice;
        this.ticketedVisitors = 0;
    }

    public abstract void visit(Visitor visitor);

    public String getName() {
        return name;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
    public void incrementTicketedVisitors() {
        ticketedVisitors++;
    }


    public int getTicketedVisitors() {
        return ticketedVisitors;
    }
}

class Attract extends Attraction {
    private int ticketedVisitors;

    public Attract(String name, String description, int attractionId, double ticketPrice) {
        super(name, description, attractionId, ticketPrice);
        this.ticketedVisitors = 0;
    }

    @Override
    public void visit(Visitor visitor) {
        System.out.println("Welcome to " + getName());
        double price = getTicketPrice();
    }


    public int getTicketedVisitors() {
        return ticketedVisitors;
    }

    public void incrementTicketedVisitors() {
        ticketedVisitors++;
    }
}


class Admin implements ZooManagement {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    private List<Attraction> attractions = new ArrayList<>();
    private List<Animal> animals = new ArrayList<>();
    private List<Discount> discounts = new ArrayList<>();
    private List<SpecialDeal> specialDeals = new ArrayList<>();
    private List<Visitor> visitors = new ArrayList<>();

    private List<String> visitorFeedback = new ArrayList<>();


    @Override
    public void enterAsAdmin() {
        Scanner scanner = new Scanner(System.in);
        String username, password;

        System.out.print("Enter Admin Username: ");
        username = scanner.next();
        System.out.print("Enter Admin Password: ");
        password = scanner.next();

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("Logged in as Admin.");
            adminMenu();
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    private void viewVisitorFeedback() {
        if (visitorFeedback.size() == 0) {
            System.out.println("No visitor feedback available.");
        } else {
            System.out.println("Visitor Feedback:");
            for (String feedback : visitorFeedback) {
                System.out.println(feedback);
            }
        }
    }

    private void adminMenu() {
        int adminChoice;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Admin Menu:");
            System.out.println("1. Manage Attractions");
            System.out.println("2. Manage Animals");
            System.out.println("3. Schedule Events");
            System.out.println("4. Set Discounts");
            System.out.println("5. Set Special Deal");
            System.out.println("6. View Visitor Stats");
            System.out.println("7. View Feedback");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            adminChoice = scanner.nextInt();

            switch (adminChoice) {
                case 1:
                    Scanner attractionScanner = new Scanner(System.in);
                    System.out.print("Enter Attraction Name: ");
                    String attractionName = attractionScanner.nextLine();
                    System.out.print("Enter Attraction Description: ");
                    String attractionDescription = attractionScanner.nextLine();
                    System.out.print("Enter Ticket Price: ");
                    double ticketPrice = attractionScanner.nextDouble();
                    Attraction newAttraction = new Attract(attractionName, attractionDescription, attractions.size() + 1, ticketPrice);
                    attractions.add(newAttraction);
                    System.out.println("Attraction added successfully.");
                    break;
                case 2:
                    Scanner animalScanner = new Scanner(System.in);
                    System.out.print("Enter Animal Name: ");
                    String animalName = animalScanner.nextLine();
                    System.out.print("Enter Animal Type (Mammal/Amphibian/Reptile): ");
                    String animalType = animalScanner.nextLine();
                    Animal newAnimal = new Animal(animalName, animalType);
                    animals.add(newAnimal);
                    System.out.println("Animal added to Zoo.");
                    break;
                case 3:
                    System.out.println("Enter event details:");
                    Scanner eventScanner = new Scanner(System.in);

                    System.out.print("Enter Event Name: ");
                    String eventName = eventScanner.nextLine();
                    System.out.print("Enter Event Date (YYYY-MM-DD): ");
                    String eventDate = eventScanner.nextLine();
                    System.out.print("Enter Event Time (HH:MM AM/PM): ");
                    String eventTime = eventScanner.nextLine();
                    System.out.println("Event scheduled successfully.");
                    break;
                case 4:
                    System.out.println("Enter discount details:");
                    Scanner discountScanner = new Scanner(System.in);

                    System.out.print("Enter Discount Category: ");
                    String discountCategory = discountScanner.nextLine();
                    System.out.print("Enter Discount Percentage: ");
                    double discountPercentage = discountScanner.nextDouble();
                    Discount newDiscount = new Discount(discountCategory, discountPercentage);
                    discounts.add(newDiscount);

                    System.out.println("Discount set successfully.");
                    break;


                case 5:
                    break;
                case 6:
                    //viewVisitorStatistics();
                    break;

                case 7:
                    viewVisitorFeedback();
                    break;

                case 8:
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (adminChoice != 8);
    }

    @Override
    public void enterAsVisitor() {
        System.out.println("Not applicable for admin");
    }
}

class Visitor implements ZooManagement {
    private String name;
    private int age;
    private String phoneNumber;
    private double balance;
    private String email;
    private String password;
    private Membership membership;
    private List<Attraction> attractions;
    private List<Discount> discounts = new ArrayList<>();
    private List<SpecialDeal> specialDeals = new ArrayList<>();
    private List<Animal> animals = new ArrayList<>();
    private List<String> visitorFeedback;

    public Visitor(String name, int age, String phoneNumber, double balance, String email, String password) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.email = email;
        this.password = password;
        this.membership = new BasicMembership();
        this.attractions = new ArrayList<>();
        this.visitorFeedback = new ArrayList<>();


    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void enterAsAdmin() {
        System.out.println("Not applicable for visitors");
    }

    @Override
    public void enterAsVisitor() {
        visitorMenu();
    }

    private void visitorMenu() {
        int visitorChoice;
        Scanner visitorScanner = new Scanner(System.in);
        do {
            System.out.println("Visitor Menu:");
            System.out.println("1. Explore the Zoo");
            System.out.println("2. Buy Membership");
            System.out.println("3. Buy Tickets");
            System.out.println("4. View Discounts");
            System.out.println("5. View Special Deals");
            System.out.println("6. Visit Animals");
            System.out.println("7. Visit Attractions");
            System.out.println("8. Leave Feedback");
            System.out.println("9. Log Out");
            System.out.print("Enter your choice: ");
            visitorChoice = visitorScanner.nextInt();

            switch (visitorChoice) {
                case 1:
                    System.out.println("Exploring the Zoo...");
                    System.out.println("Available Attractions:");
                    for (Attraction attraction : attractions) {
                        System.out.println(attraction.getName());
                    }

                    System.out.print("Enter the name of the attraction you want to visit (or type 'exit' to return to the main menu): ");
                    Scanner exploreScanner = new Scanner(System.in);
                    String attractionName = exploreScanner.nextLine();

                    if ("exit".equalsIgnoreCase(attractionName)) {
                        break;
                    }

                    Attract selectedAttraction = null;
                    for (Attraction attraction : attractions) {
                        if (attraction instanceof Attract && attraction.getName().equalsIgnoreCase(attractionName)) {
                            selectedAttraction = (Attract) attraction;
                            break;
                        }
                    }

                    if (selectedAttraction != null) {
                        if (selectedAttraction.getTicketedVisitors() < 100) {
                            System.out.println("You have visited " + selectedAttraction.getName());

                            double ticketPrice = selectedAttraction.getTicketPrice();
                            if (balance >= ticketPrice) {
                                balance -= ticketPrice;
                                selectedAttraction.visit(this);
                                selectedAttraction.incrementTicketedVisitors();
                            } else {
                                System.out.println("Insufficient balance to visit this attraction.");
                            }
                        } else {
                            System.out.println("This attraction is currently at maximum capacity.");
                        }
                    } else {
                        System.out.println("Attraction not found. Please enter a valid attraction name.");
                    }
                    break;



                case 2:
                    System.out.println("Available Memberships:");
                    System.out.println("1. Basic Membership (₹20)");
                    System.out.println("2. Premium Membership (₹50)");
                    System.out.print("Select a membership: ");
                    int membershipChoice = visitorScanner.nextInt();

                    if (membershipChoice == 1) {
                        System.out.println("Basic Membership purchased successfully.");
                        membership = new BasicMembership();
                        balance -= membership.getPrice();
                    } else if (membershipChoice == 2) {
                        System.out.println("Premium Membership purchased successfully.");
                        membership = new PremiumMembership();
                        balance -= membership.getPrice();
                    } else {
                        System.out.println("Invalid membership choice.");
                    }
                    break;

                case 3:
                    if (membership instanceof BasicMembership) {
                        System.out.println("Buy Tickets:");
                        System.out.print("Enter the number of tickets: ");
                        int numTickets = visitorScanner.nextInt();

                        if (numTickets <= 0) {
                            System.out.println("Invalid number of tickets.");
                            break;
                        }

                        System.out.println("Select attractions to buy tickets:");
                        for (int i = 0; i < attractions.size(); i++) {
                            Attraction attraction = attractions.get(i);
                            System.out.println((i + 1) + ". " + attraction.getName() + " (₹" + attraction.getTicketPrice() + ")");
                        }

                        List<Attraction> selectedAttractions = new ArrayList<>();
                        double totalCost = 0;

                        for (int i = 0; i < numTickets; i++) {
                            System.out.print("Enter your choice for ticket " + (i + 1) + ": ");
                            int choice = visitorScanner.nextInt();

                            if (choice < 1 || choice > attractions.size()) {
                                System.out.println("Invalid attraction choice.");
                                i--;
                                continue;
                            }

                            Attraction chosenAttraction = attractions.get(choice - 1);

                            if (!selectedAttractions.contains(chosenAttraction)) {
                                selectedAttractions.add(chosenAttraction);
                                totalCost += chosenAttraction.getTicketPrice();
                            } else {
                                System.out.println("You have already selected this attraction. Please choose another one.");
                                i--;
                            }
                        }

                        if (balance >= totalCost) {
                            balance -= totalCost;
                            System.out.println("Tickets purchased successfully. Your balance is now ₹" + balance);
                        } else {
                            System.out.println("Insufficient balance to purchase tickets.");
                        }
                    } else {
                        System.out.println("Only Basic members can buy tickets.");
                    }

                    break;




                case 4:
                    if (discounts.isEmpty()) {
                        System.out.println("No discounts available at the moment.");
                        break;
                    }

                    System.out.println("Available Discounts:");
                    for (Discount discount : discounts) {
                        System.out.println("Category: " + discount.getCategory() + " - Percentage: " + discount.getPercentage() + "%");
                    }

                    System.out.println("Enter 'back' to return to the main menu.");
                    break;

                case 5:
                    if (specialDeals.isEmpty()) {
                        System.out.println("No special deals available at the moment.");
                        break;
                    }

                    System.out.println("Available Special Deals:");
                    for (SpecialDeal specialDeal : specialDeals) {
                        System.out.println("Minimum Attractions Required: " + specialDeal.getMinAttractions() + " - Percentage Discount: " + specialDeal.getDiscountPercentage() + "%");
                    }

                    System.out.println("Enter 'back' to return to the main menu.");
                    break;

                case 6:
                    if (animals.isEmpty()) {
                        System.out.println("No animals available in the zoo at the moment.");
                        break;
                    }

                    System.out.println("Available Animals:");
                    for (Animal animal : animals) {
                        System.out.println("Name: " + animal.getName() + " - Type: " + animal.getType());
                    }

                    System.out.println("Enter 'back' to return to the main menu.");
                    break;

                case 7:
                    if (attractions.isEmpty()) {
                        System.out.println("No attractions available at the moment.");
                        break;
                    }

                    List<Attraction> selectedAttractions = new ArrayList<>();

                    System.out.println("Available Attractions:");
                    for (int i = 0; i < attractions.size(); i++) {
                        Attraction attraction = attractions.get(i);
                        System.out.println((i + 1) + ". " + attraction.getName());
                    }

                    System.out.print("Select an Attraction to Visit (Enter the attraction number): ");
                    int attractionChoice = visitorScanner.nextInt();

                    if (attractionChoice <= 0 || attractionChoice > attractions.size()) {
                        System.out.println("Invalid attraction choice.");
                        break;
                    }

                    Attraction selectedAttractionToVisit = attractions.get(attractionChoice - 1);

                    if (!selectedAttractions.contains(selectedAttractionToVisit)) {
                        System.out.println("You need to buy a ticket to visit " + selectedAttractionToVisit.getName() + ".");
                    } else if (selectedAttractionToVisit.getTicketedVisitors() >= 100) {
                        System.out.println("This attraction is currently at maximum capacity.");
                    } else {
                        System.out.println("You have visited " + selectedAttractionToVisit.getName());
                        selectedAttractionToVisit.visit(this);
                    }

                    break;




                case 8:
                    System.out.println("Please leave your feedback: ");
                    Scanner feedbackScanner = new Scanner(System.in);
                    String feedback = feedbackScanner.nextLine();
                    visitorFeedback.add(feedback);
                    System.out.println("Thank you for your feedback!");
                    break;
                case 9:
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (visitorChoice != 9);
    }
}

class Animal {
    private String name;
    private String type;

    public Animal(String name, String type) {
        this.name = name;
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

}

class Discount {
    private String category;
    private double percentage;

    public Discount(String category, double percentage) {
        this.category = category;
        this.percentage = percentage;
    }
    public String getCategory() {
        return category;
    }

    public double getPercentage() {
        return percentage;
    }
}

class SpecialDeal {
    private int minAttractions;
    private double discountPercentage;
    public int getMinAttractions() {
        return minAttractions;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }
}

abstract class Membership {
    protected double price;

    public abstract double getPrice();
}

class BasicMembership extends Membership {
    public BasicMembership() {
        this.price = 20.0;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

class PremiumMembership extends Membership {
    public PremiumMembership() {
        this.price = 50.0;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

public class ZooManagementSystem {
    public static void main(String[] args) {
        Admin admin = new Admin();
        List<Visitor> visitors = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Welcome to ZOOtopia!");
            System.out.println("1. Enter as Admin");
            System.out.println("2. Enter as Visitor");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    admin.enterAsAdmin();
                    break;
                case 2:
                    System.out.println("1. Register");
                    System.out.println("2. Login");
                    System.out.print("Enter your choice: ");
                    int visitorChoice = scanner.nextInt();

                    if (visitorChoice == 1) {
                        System.out.print("Enter Visitor Name: ");
                        String name = scanner.next();
                        System.out.print("Enter Visitor Age: ");
                        int age = scanner.nextInt();
                        System.out.print("Enter Visitor Phone Number: ");
                        String phoneNumber = scanner.next();
                        System.out.print("Enter Visitor Balance: ");
                        double balance = scanner.nextDouble();
                        System.out.print("Enter Visitor Email: ");
                        String email = scanner.next();
                        System.out.print("Enter Visitor Password: ");
                        String password = scanner.next();

                        Visitor newVisitor = new Visitor(name, age, phoneNumber, balance, email, password);
                        visitors.add(newVisitor);
                        System.out.println("Registration is successful.");
                        newVisitor.enterAsVisitor();
                    } else if (visitorChoice == 2) {
                        System.out.print("Enter Visitor Email: ");
                        String email = scanner.next();
                        System.out.print("Enter Visitor Password: ");
                        String password = scanner.next();

                        boolean loggedIn = false;
                        for (Visitor visitor : visitors) {
                            if (visitor.getEmail().equals(email) && visitor.getPassword().equals(password)) {
                                loggedIn = true;
                                visitor.enterAsVisitor();
                                break;
                            }
                        }

                        if (!loggedIn) {
                            System.out.println("Login failed. Invalid credentials.");
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 3:
                    System.out.println("Thanks for visiting!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }
}
