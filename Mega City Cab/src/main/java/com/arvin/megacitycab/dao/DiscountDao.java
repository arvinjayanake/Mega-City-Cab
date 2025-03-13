package com.arvin.megacitycab.dao;


import com.arvin.megacitycab.model.Discount;

import java.sql.SQLException;
import java.util.List;

public interface DiscountDao {
    Discount addDiscount(Discount vehicle) throws SQLException;

    List<Discount> getAllDiscounts() throws SQLException;

    Discount getDiscountById(int id) throws SQLException;

    Discount getDiscountByCode(String code) throws SQLException;

    Discount updateDiscount(Discount discount) throws SQLException;

    void deleteDiscount(int id) throws SQLException;
}
