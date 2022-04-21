package by.epam.lab;

public class Runner {

    public static void main(String[] args) {
	    Purchase<Product> p1 = new Purchase<>(new Product("Milk", new Byn(170)), 20);
	    PurchaseUtils<Product> pu1 = new PurchaseUtils<>(p1);
	    pu1.printPurchase();
	    pu1.printCost();
		Purchase<Product> p2 = new Purchase<>(new Product("Sugar", new Byn(300)), 12.5);
		PurchaseUtils<Product> pu2 = new PurchaseUtils<>(p2);
		pu2.printCost();
		pu2.printCostDiff(p1);
		Purchase<DiscountProduct> p3 = new Purchase<>(new DiscountProduct("Sugar", new Byn(280), new Byn(10)), 60);
		PurchaseUtils<Service> pu4 = new PurchaseUtils<>(new Service("Gym", new Byn(7560), 5), 2.25);
		Service service = pu4.getPurchase().getItem();
		System.out.println(service);
		pu4.printCost();
		pu2.printIsSameCost(p1, p3, pu4.getPurchase());
    }
}
