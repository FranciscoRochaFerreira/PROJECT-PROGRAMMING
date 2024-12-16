import java.util.*;

class House {
    private String id;
    private String location;
    private String price;

    public House(String id, String location, String price) {
        this.id = id;
        this.location = location;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        House house = (House) obj;
        return id.equals(house.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + " | " + location + " | " + price;
    }
}

public class HouseCatalogManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<House> houses = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\nHouse Catalog Manager");
            System.out.println("1. Add a House");
            System.out.println("2. View All Houses");
            System.out.println("3. Remove a House");
            System.out.println("4. Modify a House");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addHouse();
                case 2 -> viewHouses();
                case 3 -> removeHouse();
                case 4 -> modifyHouse();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 5);
    }
    private static void addHouse() {
        System.out.print("Enter house ID: ");
        String id = scanner.nextLine();
        
        for (House house : houses) {
            if (house.getId().equals(id)) {
                System.out.println("House with this ID already exists. Cannot add duplicate.");
                return;
            }
        }

        System.out.print("Enter house location: ");
        String location = scanner.nextLine();
        System.out.print("Enter house price: ");
        String price = scanner.nextLine();

        houses.add(new House(id, location, price));
        System.out.println("House added successfully!");
    }

    private static void viewHouses() {
        if (houses.isEmpty()) {
            System.out.println("No houses available.");
            return;
        }

        System.out.println("\nID | Location | Price");
        System.out.println("------------------------");
        for (House house : houses) {
            System.out.println(house);
        }
    }

    private static void removeHouse() {
        System.out.print("Enter the ID of the house to remove: ");
        String idToRemove = scanner.nextLine();

        House houseToRemove = new House(idToRemove, "", ""); // Only ID is needed to identify
        if (houses.remove(houseToRemove)) {
            System.out.println("House removed successfully!");
        } else {
            System.out.println("House not found.");
        }
    }

    private static void modifyHouse() {
        System.out.print("Enter the ID of the house to modify: ");
        String idToModify = scanner.nextLine();

        for (House house : houses) {
            if (house.getId().equals(idToModify)) {
                System.out.print("Enter new location: ");
                String newLocation = scanner.nextLine();
                System.out.print("Enter new price: ");
                String newPrice = scanner.nextLine();

                house.setLocation(newLocation);
                house.setPrice(newPrice);
                System.out.println("House modified successfully!");
                return;
            }
        }
        System.out.println("House not found.");
    }
}

    