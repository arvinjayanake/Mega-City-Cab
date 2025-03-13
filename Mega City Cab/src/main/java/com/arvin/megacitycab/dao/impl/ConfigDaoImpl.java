package com.arvin.megacitycab.dao.impl;

import com.arvin.megacitycab.dao.ConfigDao;
import com.arvin.megacitycab.dao.base.BaseDao;
import com.arvin.megacitycab.db.DatabaseConnectionPool;
import com.arvin.megacitycab.model.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class ConfigDaoImpl extends BaseDao implements ConfigDao {

    ConfigDaoImpl(){}

    @Override
    public List<Config> getAllConfigs() throws SQLException {
        List<Config> configList = new ArrayList<>();
        String sql = "SELECT * FROM config";
        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Config config = resultSetToConfig(rs);
                    configList.add(config);
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return configList;
    }

    @Override
    public Config updateConfig(Config config) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE config SET ");
        List<Object> params = new ArrayList<>();

        Config mC = getConfigById(config.getId());

        if (config.getValue() != null) {
            mC.setValue(config.getValue());
            sql.append("value = ?, ");
            params.add(config.getValue());
        }

        if (config.getDescription() != null) {
            mC.setDescription(config.getDescription());
            sql.append("description = ?, ");
            params.add(config.getDescription());
        }

        if (params.isEmpty()) {
            return config;
        }
        config = mC;

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE id = ? ");
        params.add(config.getId());

        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
                for (int i = 0; i < params.size(); i++) {
                    stmt.setObject(i + 1, params.get(i));
                }
                stmt.executeUpdate();

                return getConfigById(config.getId());
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
    }

    @Override
    public Config getConfigById(int id) throws SQLException {
        Config config = null;
        String sql = "SELECT * FROM config WHERE id = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        config = resultSetToConfig(rs);
                    }
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return config;
    }

    private Config resultSetToConfig(ResultSet rs) throws SQLException {
        Config config = new Config();
        config.setId(rs.getInt("id"));
        config.setConfig_key(resToString(rs, "config_key"));
        config.setValue(resToString(rs, "value"));
        config.setType(resToString(rs, "type"));
        config.setDescription(resToString(rs, "description"));
        config.setCreated_at(resToString(rs, "created_at"));
        config.setUpdated_at(resToString(rs, "updated_at"));
        return config;
    }
}
