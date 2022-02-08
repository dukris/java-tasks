package by.epam.utils;

public class ConstantsSQL {
    public static final String SQL_SELECT_ALL_USERS = "SELECT id,name,email,password FROM users LIMIT ? OFFSET ?";
    public static final int SELECT_ALL_LIMIT_INDEX = 1;
    public static final int SELECT_ALL_OFFSET_INDEX = 2;
    public static final int SELECT_ALL_ID_INDEX = 1;
    public static final int SELECT_ALL_NAME_INDEX = 2;
    public static final int SELECT_ALL_EMAIL_INDEX = 3;
    public static final int SELECT_ALL_PASSWORD_INDEX = 4;

    public static final String SQL_SELECT_USER_BY_EMAIL = "SELECT id,name,password FROM users WHERE email=?";
    public static final int SELECT_BY_EMAIL_EMAIL_INDEX = 1;
    public static final int SELECT_BY_EMAIL_ID_INDEX = 1;
    public static final int SELECT_BY_EMAIL_NAME_INDEX = 2;
    public static final int SELECT_BY_EMAIL_PASSWORD_INDEX = 3;

    public static final String SQL_SELECT_USER_BY_ID = "SELECT id,name,email,password FROM users WHERE id=?";
    public static final int SELECT_BY_ID_ID_INDEX = 1;
    public static final int SELECT_BY_ID_NAME_INDEX = 1;
    public static final int SELECT_BY_ID_EMAIL_INDEX = 2;
    public static final int SELECT_BY_ID_PASSWORD_INDEX = 3;

    public static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id=?";
    public static final int DELETE_INDEX = 1;

    public static final String SQL_CREATE_USER = "INSERT users(name,email,password) VALUES (?,?,?)";
    public static final int NAME_CREATE_INDEX = 1;
    public static final int EMAIL_CREATE_INDEX = 2;
    public static final int PASSWORD_CREATE_INDEX = 3;

    public static final String SQL_UPDATE_USER = "UPDATE users SET password=?,name=? WHERE email=?";
    public static final int PASSWORD_UPDATE_INDEX = 1;
    public static final int NAME_UPDATE_INDEX = 2;
    public static final int EMAIL_UPDATE_INDEX = 3;

    public static final String DB_PROPERTIES = "db.properties";
    public static final String USER_PROPERTY = "dataSource.user";
    public static final String PASSWORD_PROPERTY = "dataSource.password";
    public static final String URL_PROPERTY = "datasource.jdbcUrl";
    public static final String POOL_SIZE_PROPERTY = "dataSource.maxPoolSize";
    public static final String DRIVER_PROPERTY = "datasource.driverClassName";
}
