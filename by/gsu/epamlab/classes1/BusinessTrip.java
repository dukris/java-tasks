package by.gsu.epamlab.classes1;

import by.gsu.epamlab.Utils;

public class BusinessTrip {
    private static final int RATE = 700;
    private String account;
    private int transport;
    private int days;

    public BusinessTrip() {
    }

    public BusinessTrip(String account, String transport, int days) {
        this.account = account;
        this.transport = Integer.parseInt(transport.replace(".", ""));
        this.days = days;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getTransport() {
        return transport;
    }

    public void setTransport(int transport) {
        this.transport = transport;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getTotal() {
        return RATE * days + transport;
    }

    public void show() {
        System.out.println("Rate = " + Utils.createDouble(RATE) + "\nAccount = " + account + "\nTransport = " + Utils.createDouble(transport) + "\nDays = " + days + "\nTotal = " + Utils.createDouble(getTotal()));
    }

    @Override
    public String toString() {
        return account + ";" + Utils.createDouble(transport) + ";" + days + ";" + Utils.createDouble(getTotal());
    }
}
