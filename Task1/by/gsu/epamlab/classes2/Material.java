package by.gsu.epamlab.classes2;

public class Material {
    private String name;
    private double density;

    public Material() {
    }

    public Material(String name, double density) {
        this.name = name;
        if (isCorrect()) {
            this.density = density;
        }
    }

    public boolean isCorrect() {
        switch (this.name.toLowerCase()) {
            case "steel" -> {
                this.density = 7850.0;
                return false;
            }
            case "copper" -> {
                this.density = 8500.0;
                return false;
            }
            default -> {
                return true;
            }
        }
    }

    public String getName() {
        return name;
    }

    public double getDensity() {
        return density;
    }

    @Override
    public String toString() {
        return name + ";" + density;
    }
}
