package com.thomas.dao.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.thomas.dao.model.*;
import org.jdbi.v3.core.Jdbi;

import java.sql.SQLException;

public class JDBIConnect {
    private static Jdbi jdbi;
    static String url = "jdbc:mysql://" + DBProperties.host() + ":" + DBProperties.port() + "/" + DBProperties.dbname() + "?" + DBProperties.option();

    public static Jdbi get() {
        if (jdbi == null) makeConnect();
        return jdbi;
    }

    private static void makeConnect() {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL(url);
        ds.setUser(DBProperties.username());
        ds.setPassword(DBProperties.password());
        try {
            ds.setUseCompression(true);
            ds.setAutoReconnect(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbi = Jdbi.create(ds);
        jdbi.registerRowMapper(new CouponMapper());
        jdbi.registerRowMapper(new TokenMapper());
        jdbi.registerRowMapper(new UserMapper());
        jdbi.registerRowMapper(new ReviewMapper());
        jdbi.registerRowMapper(new UsersUsageMapper());
        jdbi.registerRowMapper(new BeltMapper());
        jdbi.registerRowMapper(new BeltVariantsMapper());
        jdbi.registerRowMapper(new PuchasesMapper());
        jdbi.registerRowMapper(new CartItemMapper());
        jdbi.registerRowMapper(Inventory.class, new InventoryMapper());
    }
}
