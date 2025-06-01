package com.thomas.dao.db;

import com.thomas.dao.model.Address;
import com.thomas.dao.model.Belts;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper implements RowMapper<Address> {
    @Override
    public Address map(ResultSet rs, StatementContext ctx) throws SQLException {
        Address a = new Address();
        a.setDistrictId(rs.getInt("districtId"));
        a.setWardId(rs.getString("wardId"));
        a.setAddressDetail(rs.getString("addressDetail"));
        a.setProvinceId(rs.getInt("provinceId"));
        a.setFullName(rs.getString("fullname"));
        a.setId(rs.getInt("id"));
        a.setPhone(rs.getInt("phone"));
        a.setIsUse(rs.getInt("isUse"));
        return a;
    }
}
