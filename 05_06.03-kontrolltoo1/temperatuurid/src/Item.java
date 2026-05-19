public class Item {
    private double mass;
    private double temperature;
    private Material material;

    public Item(double mass, double temperature, Material material) {
        this.mass = mass;
        this.temperature = temperature;
        this.material = material;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Item{mass=" + mass + ", temperature=" + temperature + ", material=" + material.getName() + "}";
    }
}