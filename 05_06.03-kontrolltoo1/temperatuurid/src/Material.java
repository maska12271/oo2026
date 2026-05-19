public class Material {
    private final String name;
    private final double specificHeatCapacity;
    private final double density;

    public Material(String name, double specificHeatCapacity, double density) {
        this.name = name;
        this.specificHeatCapacity = specificHeatCapacity;
        this.density = density;
    }

    public String getName() {
        return name;
    }

    public double getSpecificHeatCapacity() {
        return specificHeatCapacity;
    }

    public double getDensity() {
        return density;
    }

    @Override
    public String toString() {
        return "Material{name='" + name + "', specificHeatCapacity=" + specificHeatCapacity + ", density=" + density + "}";
    }
}