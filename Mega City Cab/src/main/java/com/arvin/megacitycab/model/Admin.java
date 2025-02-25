package com.arvin.megacitycab.model;

import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.UserType;

public class Admin extends User {
    public Admin() {
        this.type = UserType.ADMIN.getValue();
    }
}
