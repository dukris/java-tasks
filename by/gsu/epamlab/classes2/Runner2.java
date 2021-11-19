package by.gsu.epamlab.classes2;

public class Runner2 {
    public static void main(String[] args) {
        Subject wire = new Subject("wire", new Material("steel", 7000.0), 0.03);
        System.out.println(wire);
        wire.setMaterial(new Material("copper", 8500.0));
        System.out.println("The " + wire.getName() + " mass is " + wire.getMass() + " kg");
    }
}
