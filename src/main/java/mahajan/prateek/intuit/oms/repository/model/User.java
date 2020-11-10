package mahajan.prateek.intuit.oms.repository.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by: pramahajan on 11/11/20 3:19 AM GMT+05:30
 */
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private String address;
    @NotNull
    private UsersType type;

    public User(@NotNull String name, @NotNull String email, @NotNull String phone, @NotNull String address, @NotNull UsersType type) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UsersType getType() {
        return type;
    }

    public void setType(UsersType type) {
        this.type = type;
    }

    public boolean isAdmin() {
        return this.type == UsersType.ADMIN;
    }
}

