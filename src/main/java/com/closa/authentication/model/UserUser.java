package com.closa.authentication.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserUser {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_ID")
    @TableGenerator(name = "USER_ID",
            table ="GEN_ID",
            pkColumnName = "key_name",
            valueColumnName = "key_val",
            pkColumnValue = "USER_ID",
            initialValue = 0,
            allocationSize = 100 )
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToMany
    private List<UserRole> userRoles = new ArrayList<>();

    @ManyToMany
    private List<UserAddress> userAddresses = new ArrayList<>();

    public UserUser() {
    }

    public List<UserAddress> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(List<UserAddress> userAddresses) {
        this.userAddresses = userAddresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public void addRole(UserRole role){
        this.getUserRoles().add(role);
    }
    public void removeRole(UserRole role){
        this.getUserRoles().remove(role);
    }
    public void addAddress(UserAddress address){
        this.getUserAddresses().add(address);
    }
    public void removeAddress(UserAddress address){
        this.getUserAddresses().remove(address);
    }
}
