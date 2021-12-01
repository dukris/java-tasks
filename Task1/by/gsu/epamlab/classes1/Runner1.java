package by.gsu.epamlab.classes1;

import by.gsu.epamlab.Utils;

public class Runner1 {

    public static void main(String[] args) {
        BusinessTrip[] trips = {
                new BusinessTrip("Hanna", "7.50", 5),
                new BusinessTrip("Iliaz", "9.80", 7),
                null,
                new BusinessTrip("John", "4.50", 3),
                new BusinessTrip()
        };
        for (BusinessTrip trip : trips) {
            if (trip != null) {
                trip.show();
            }
        }
        System.out.println("Maximum cost of a business trip = " + Utils.getMaxTrip(trips));
        trips[trips.length - 1].setTransport(120);
        System.out.println("Duration = " + (trips[0].getDays() + trips[1].getDays()));
        for (BusinessTrip trip : trips) {
            System.out.println(trip);
        }
    }
}
