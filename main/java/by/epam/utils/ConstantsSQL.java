package by.epam.utils;

public class ConstantsSQL {
    public static final String SQL_SELECT_ALL_USERS = "SELECT id,name,email,password,role FROM users LIMIT ? OFFSET ?";
    public static final int SELECT_ALL_LIMIT_INDEX = 1;
    public static final int SELECT_ALL_OFFSET_INDEX = 2;
    public static final int SELECT_ALL_ID_INDEX = 1;
    public static final int SELECT_ALL_NAME_INDEX = 2;
    public static final int SELECT_ALL_EMAIL_INDEX = 3;
    public static final int SELECT_ALL_PASSWORD_INDEX = 4;
    public static final int SELECT_ALL_ROLE_INDEX = 5;

    public static final String SQL_SELECT_USER_BY_EMAIL = "SELECT id,name,password,role FROM users WHERE email=?";
    public static final int SELECT_BY_EMAIL_EMAIL_INDEX = 1;
    public static final int SELECT_BY_EMAIL_ID_INDEX = 1;
    public static final int SELECT_BY_EMAIL_NAME_INDEX = 2;
    public static final int SELECT_BY_EMAIL_PASSWORD_INDEX = 3;
    public static final int SELECT_BY_EMAIL_ROLE_INDEX = 4;

    public static final String SQL_SELECT_USER_BY_ID = "SELECT id,name,email,password,role FROM users WHERE id=?";
    public static final int SELECT_BY_ID_ID_INDEX = 1;
    public static final int SELECT_BY_ID_NAME_INDEX = 2;
    public static final int SELECT_BY_ID_EMAIL_INDEX = 3;
    public static final int SELECT_BY_ID_PASSWORD_INDEX = 4;
    public static final int SELECT_BY_ID_ROLE_INDEX = 5;

    public static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id=?";
    public static final int DELETE_INDEX = 1;

    public static final String SQL_CREATE_USER = "INSERT users(name,email,password,role) VALUES (?,?,?,?)";
    public static final int NAME_CREATE_INDEX = 1;
    public static final int EMAIL_CREATE_INDEX = 2;
    public static final int PASSWORD_CREATE_INDEX = 3;
    public static final int ROLE_CREATE_INDEX = 4;

    public static final String SQL_UPDATE_USER = "UPDATE users SET password=?,name=? WHERE email=?";
    public static final int PASSWORD_UPDATE_INDEX = 1;
    public static final int NAME_UPDATE_INDEX = 2;
    public static final int EMAIL_UPDATE_INDEX = 3;

    public static final String SQL_SELECT_ALL_FLIGHTS = "SELECT id,from_city,to_city,date,amount,price FROM flights LIMIT ? OFFSET ?";
    public static final int SELECT_ALL_FROM_INDEX = 2;
    public static final int SELECT_ALL_TO_INDEX = 3;
    public static final int SELECT_ALL_DATE_INDEX = 4;
    public static final int SELECT_ALL_AMOUNT_INDEX = 5;
    public static final int SELECT_ALL_PRICE_INDEX = 6;

    public static final String SQL_SELECT_FLIGHT_BY_ID = "SELECT id,from_city,to_city,date,amount,price FROM flights WHERE id=?";
    public static final int SELECT_BY_ID_FROM_INDEX = 2;
    public static final int SELECT_BY_ID_TO_INDEX = 3;
    public static final int SELECT_BY_ID_DATE_INDEX = 4;
    public static final int SELECT_BY_ID_AMOUNT_INDEX = 5;
    public static final int SELECT_BY_ID_PRICE_INDEX = 6;

    public static final String SQL_DELETE_FLIGHT_BY_ID = "DELETE FROM flights WHERE id=?";

    public static final String SQL_CREATE_FLIGHT = "INSERT flights(from_city,to_city,date,amount,price) VALUES (?,?,?,?,?)";
    public static final int FROM_CREATE_INDEX = 1;
    public static final int TO_CREATE_INDEX = 2;
    public static final int DATE_CREATE_INDEX = 3;
    public static final int AMOUNT_CREATE_INDEX = 4;
    public static final int PRICE_CREATE_INDEX = 5;

    public static final String SQL_UPDATE_FLIGHT = "UPDATE flights SET from_city=?,to_city=?,date=?,amount=?,price=? WHERE id=?";
    public static final int FROM_UPDATE_INDEX = 1;
    public static final int TO_UPDATE_INDEX = 2;
    public static final int DATE_UPDATE_INDEX = 3;
    public static final int AMOUNT_UPDATE_INDEX = 4;
    public static final int PRICE_UPDATE_INDEX = 5;
    public static final int ID_UPDATE_INDEX = 6;

    public static final String SQL_SELECT_FLIGHT_BY_PARAM = "SELECT id,amount,price FROM flights WHERE from_city=? AND to_city=? AND date=?";
    public static final String SQL_SELECT_FLIGHT_BY_PARAM_NO_DATE = "SELECT id,amount,price,date FROM flights WHERE from_city=? AND to_city=?";
    public static final int SELECT_BY_PARAM_FROM_INDEX = 1;
    public static final int SELECT_BY_PARAM_TO_INDEX = 2;
    public static final int SELECT_BY_PARAM_DATE_INDEX = 3;
    public static final int SELECT_BY_PARAM_ID_INDEX = 1;
    public static final int SELECT_BY_PARAM_AMOUNT_INDEX = 2;
    public static final int SELECT_BY_PARAM_PRICE_INDEX = 3;
    public static final int SELECT_BY_PARAM_DATE_INDEX_NO_DATE = 4;
}
