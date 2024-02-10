package org.Bookstore;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class User {
    private UUID userId;
    private String userName;
    private  String userAddress;
    private UserType userType;
    private List<Order> orders;

    public User(String userName, String userAddress, UserType userType) {
        this.userId = UUID.randomUUID();
        this.userName = userName;
        this.userAddress = userAddress;
        this.userType = userType;
        this.orders = new ArrayList<>();
    }

    public User(String userName,  UserType userType) {
        this.userId = UUID.randomUUID();
        this.userName = userName;
        this.userType = userType;

    }

}
