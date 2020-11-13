package com.closa.authentication.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADDR_ID")
    @TableGenerator(name = "ADDR_ID",
            table ="GEN_ID",
            pkColumnName = "key_name",
            valueColumnName = "key_val",
            pkColumnValue = "ADDR_ID",
            initialValue = 0,
            allocationSize = 100 )
    private Long id;

    @ManyToOne
    private UserUser userUser;
    private String  userMainEmail ;
    private Boolean userIsMainAddress;
    @Lob
    private char[] userAddress;

    public UserAddress() {
    }
    public UserAddress(String userMail, Boolean userIsMainAddress, UserUser user,  String address) {
        this.userIsMainAddress = userIsMainAddress;
        this.userUser = user;
        this.userAddress = address.toCharArray();
        this.userMainEmail= userMail;
    }


}
