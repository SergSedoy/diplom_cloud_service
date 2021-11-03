package ru.netology.diplom_cloud_service.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class User {
    @Id
    private long id;
    private String login;
    private String password;
    private String dtbase;

    public User(){
    }

    public User(String login, String password, String dtbase) {
        this.login = login;
        this.password = password;
        this.dtbase = dtbase;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDtbase() {
        return dtbase;
    }

    public void setDtbase(String dtbase) {
        this.dtbase = dtbase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", dtbase='" + dtbase + '\'' +
                '}';
    }
}
