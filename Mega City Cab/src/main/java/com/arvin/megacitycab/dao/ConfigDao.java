package com.arvin.megacitycab.dao;

import com.arvin.megacitycab.model.Config;

import java.sql.SQLException;
import java.util.List;

public interface ConfigDao {
    List<Config> getAllConfigs() throws SQLException;

    Config updateConfig(Config config) throws SQLException;

    Config getConfigById(int id) throws SQLException;
}
