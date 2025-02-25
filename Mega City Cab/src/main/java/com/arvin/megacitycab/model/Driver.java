package com.arvin.megacitycab.model;

import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.UserType;

public class Driver extends User {

    public Driver(){
        this.type = UserType.DRIVER.getValue();
    }
}
