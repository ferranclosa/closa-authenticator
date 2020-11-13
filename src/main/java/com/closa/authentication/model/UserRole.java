package com.closa.authentication.model;

import com.closa.global.status.model.ItemStatus;

import javax.persistence.*;

@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ROLE_ID")
    @TableGenerator(name = "ROLE_ID",
            table ="GEN_ID",
            pkColumnName = "key_name",
            valueColumnName = "key_val",
            pkColumnValue = "ROLE_ID",
            initialValue = 0,
            allocationSize = 100 )
    private Long id;
    @Column(length = 20, name = "role_code", nullable = false, unique = true)
    private String roleCode;
    @Column(length = 150, name = "role_description")
    private String roleDescription;
    @Column(length = 2, nullable = false, name = "role_number")
    private String roleCodeNumber;
    @Embedded
    private ItemStatus roleStatus;

    public UserRole() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleCodeNumber() {
        return roleCodeNumber;
    }

    public void setRoleCodeNumber(String roleCodeNumber) {
        this.roleCodeNumber = roleCodeNumber;
    }

    public ItemStatus getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(ItemStatus roleStatus) {
        this.roleStatus = roleStatus;
    }
}
