import java.util.ArrayList;
import java.util.List;

public class ItemCollection {
    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    public void updateItem(int index, double newMass, double newTemperature, Material newMaterial) {
        if (index >= 0 && index < items.size()) {
            Item item = items.get(index);
            item.setMass(newMass);
            item.setTemperature(newTemperature);
            item.setMaterial(newMaterial);
        }
    }

    public double getTotalMassByMaterial(Material material) {
        double total = 0;
        for (Item item : items) {
            if (item.getMaterial().getName().equalsIgnoreCase(material.getName())) {
                total += item.getMass();
            }
        }
        return total;
    }

    public double getEquilibriumTemperature() {
        if (items.isEmpty()) {
            throw new RuntimeException("Collection is empty");
        }

        double numerator = 0;
        double denominator = 0;

        for (Item item : items) {
            double massTimesHeatCapacity = item.getMass() * item.getMaterial().getSpecificHeatCapacity();
            numerator += massTimesHeatCapacity * item.getTemperature();
            denominator += massTimesHeatCapacity;
        }

        return numerator / denominator;
    }

    public void printItems() {
        if (items.isEmpty()) {
            System.out.println("No items found.");
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + ": " + items.get(i));
        }
    }
}