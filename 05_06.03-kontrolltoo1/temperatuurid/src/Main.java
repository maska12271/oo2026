import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Material> materials = new ArrayList<>();
        ItemCollection collection = new ItemCollection();

        boolean running = true;

        while (running) {
            System.out.println("\n1. Add material");
            System.out.println("2. Show materials");
            System.out.println("3. Add item");
            System.out.println("4. Show items");
            System.out.println("5. Update item");
            System.out.println("6. Delete item");
            System.out.println("7. Total mass by material");
            System.out.println("8. Equilibrium temperature");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Material name: ");
                    String name = scanner.nextLine();

                    System.out.print("Specific heat capacity (J/(kg*K)): ");
                    double c = Double.parseDouble(scanner.nextLine());

                    System.out.print("Density (kg/m³): ");
                    double density = Double.parseDouble(scanner.nextLine());

                    materials.add(new Material(name, c, density));
                    break;

                case 2:
                    for (int i = 0; i < materials.size(); i++) {
                        System.out.println(i + ": " + materials.get(i));
                    }
                    break;

                case 3:
                    for (int i = 0; i < materials.size(); i++) {
                        System.out.println(i + ": " + materials.get(i));
                    }

                    System.out.print("Material index: ");
                    int materialIndex = Integer.parseInt(scanner.nextLine());

                    System.out.print("Mass (kg): ");
                    double mass = Double.parseDouble(scanner.nextLine());

                    System.out.print("Temperature (C): ");
                    double temp = Double.parseDouble(scanner.nextLine());

                    Item item = new Item(mass, temp, materials.get(materialIndex));
                    collection.addItem(item);
                    break;

                case 4:
                    collection.printItems();
                    break;

                case 5: {
                    collection.printItems();
                    System.out.print("Item index to update: ");
                    int itemIndex = Integer.parseInt(scanner.nextLine());

                    for (int i = 0; i < materials.size(); i++) {
                        System.out.println(i + ": " + materials.get(i));
                    }

                    System.out.print("New material index: ");
                    int newMaterialIndex = Integer.parseInt(scanner.nextLine());

                    System.out.print("New mass: ");
                    double newMass = Double.parseDouble(scanner.nextLine());

                    System.out.print("New temperature: ");
                    double newTemperature = Double.parseDouble(scanner.nextLine());

                    collection.updateItem(itemIndex, newMass, newTemperature, materials.get(newMaterialIndex));
                    break;
                }

                case 6:
                    collection.printItems();
                    System.out.print("Item index to delete: ");
                    int deleteIndex = Integer.parseInt(scanner.nextLine());
                    collection.removeItem(deleteIndex);
                    break;

                case 7:
                    for (int i = 0; i < materials.size(); i++) {
                        System.out.println(i + ": " + materials.get(i));
                    }

                    System.out.print("Material index: ");
                    int index = Integer.parseInt(scanner.nextLine());

                    double totalMass = collection.getTotalMassByMaterial(materials.get(index));
                    System.out.println("Total mass (kg) = " + totalMass);
                    break;

                case 8:
                    System.out.println("Equilibrium temperature (C) = " + collection.getEquilibriumTemperature());
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Wrong choice");
            }
        }
    }
}