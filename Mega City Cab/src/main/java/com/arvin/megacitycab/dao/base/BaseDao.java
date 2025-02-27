package com.arvin.megacitycab.dao.base;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BaseDao {

    protected boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            if (columnName.equalsIgnoreCase(metaData.getColumnName(i))) {
                return true;
            }
        }
        return false;
    }

    protected String resToString(ResultSet rs, String column) throws SQLException {
        if (hasColumn(rs, column)) {
            return rs.getString(column);
        }
        return null;
    }
}
