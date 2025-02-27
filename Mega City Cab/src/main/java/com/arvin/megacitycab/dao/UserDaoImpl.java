package com.arvin.megacitycab.dao;

import com.arvin.megacitycab.dao.UserDao;
import com.arvin.megacitycab.dao.base.BaseDao;
import com.arvin.megacitycab.db.DatabaseConnectionPool;
import com.arvin.megacitycab.model.Admin;
import com.arvin.megacitycab.model.Customer;
import com.arvin.megacitycab.model.Driver;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {

    UserDaoImpl(){}

    @Override
    public User login(User user) throws SQLException {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ? AND is_verified = 1";
        Connection conn = null;

        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getEmail());
                stmt.setString(2, user.getPassword());

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return resultSetToUser(rs);
                    } else {
                        return null;
                    }
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
    }

    @Override
    public User addUser(User user) throws SQLException {
        String sql = "INSERT INTO user (name, nic, address, email, mobile, type, password, verification_code, is_verified) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getNic());
                stmt.setString(3, user.getAddress());
                stmt.setString(4, user.getEmail());
                stmt.setString(5, user.getMobile());
                stmt.setInt(6, user.getType());
                stmt.setString(7, user.getPassword());
                stmt.setString(8, user.getVerification_code());
                stmt.setBoolean(9, user.isVerified());
                int rowsAffected = stmt.executeUpdate();

                // Retrieve the generated ID
                if (rowsAffected > 0) {
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            int generatedId = rs.getInt(1);
                            user = getUserById(generatedId);
                        }
                    }
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return user;
    }

    @Override
    public List<User> getAllUsers(int type) throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name, nic, address, email, mobile, type, is_verified, created_at, updated_at FROM user WHERE type = " + type;
        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = resultSetToUser(rs);
                    userList.add(user);
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return userList;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM user WHERE id = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        user = resultSetToUser(rs);
                    }
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return user;
    }

    @Override
    public User updateUser(User user) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE user SET ");
        List<Object> params = new ArrayList<>();

        if (user.getName() != null) {
            sql.append("name = ?, ");
            params.add(user.getName());
        }
        if (user.getNic() != null) {
            sql.append("nic = ?, ");
            params.add(user.getNic());
        }
        if (user.getAddress() != null) {
            sql.append("address = ?, ");
            params.add(user.getAddress());
        }
        if (user.getEmail() != null) {
            sql.append("email = ?, ");
            params.add(user.getEmail());
        }
        if (user.getMobile() != null) {
            sql.append("mobile = ?, ");
            params.add(user.getMobile());
        }
        if (user.getType() == 1 || user.getType() == 2 || user.getType() == 3) {
            sql.append("type = ?, ");
            params.add(user.getType());
        }
        if (user.getPassword() != null) {
            sql.append("password = ?, ");
            params.add(user.getPassword());
        }
        if (user.getVerification_code() != null) {
            sql.append("verification_code = ?, ");
            params.add(user.getVerification_code());
        }

        if (user.isVerified() != null) {
            sql.append("is_verified = ?, ");
            params.add(user.isVerified());
        }

        if (user.getAccess_token() != null) {
            sql.append("access_token = ?, ");
            params.add(user.getAccess_token());
        }

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE id = ?");
        params.add(user.getId());

        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
                for (int i = 0; i < params.size(); i++) {
                    stmt.setObject(i + 1, params.get(i));
                }
                stmt.executeUpdate();

                return getUserById(user.getId());
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM user WHERE id=?";
        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
    }

    private User resultSetToUser(ResultSet rs) throws SQLException {
        User user;
        int type = rs.getInt("type");
        if (type == UserType.DRIVER.getValue()) {
            user = new Driver();
        } else if (type == UserType.ADMIN.getValue()) {
            user = new Admin();
        } else {
            user = new Customer();
        }

        user.setId(rs.getInt("id"));
        user.setName(resToString(rs, "name"));
        user.setNic(resToString(rs, "nic"));
        user.setAddress(resToString(rs, "address"));
        user.setEmail(resToString(rs, "email"));
        user.setMobile(resToString(rs, "mobile"));
        user.setPassword(resToString(rs, "password"));
        user.setVerification_code(resToString(rs, "verification_code"));
        user.setVerified(rs.getBoolean("is_verified"));
        user.setCreated_at(resToString(rs, "created_at"));
        user.setUpdated_at(resToString(rs, "updated_at"));
        return user;
    }
}
