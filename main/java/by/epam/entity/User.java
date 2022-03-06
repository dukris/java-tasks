package by.epam.entity;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Objects;

public class User extends Entity {
    private String name;
    private String surname;
    private String passport;
    private String email;
    private String password;
    private RoleType roleType;

    public User() {

    }

    public User(String name, String surname, String passport, String email, String password, RoleType roleType) {
        this.name = name;
        this.surname = surname;
        this.passport = passport;
        this.email = email;
        this.password = DigestUtils.sha256Hex(password);
        this.roleType = roleType;
    }

    public User(long id, String name, String surname, String passport, String email, String password, RoleType roleType) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.passport = passport;
        this.email = email;
        this.password = DigestUtils.sha256Hex(password);
        this.roleType = roleType;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return name + ";" + email + ";" + password;
    }
}
