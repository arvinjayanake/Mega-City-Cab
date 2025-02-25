package com.arvin.megacitycab.model;

import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.UserType;

public class Customer extends User {

    public Customer(){
        this.type = UserType.CUSTOMER.getValue();
    }
}
