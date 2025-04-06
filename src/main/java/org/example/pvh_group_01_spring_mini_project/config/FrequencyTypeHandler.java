package org.example.pvh_group_01_spring_mini_project.config;



import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.example.pvh_group_01_spring_mini_project.enumeration.Frequency;

import java.sql.*;

public class FrequencyTypeHandler extends BaseTypeHandler<Frequency> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Frequency parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public Frequency getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : Frequency.valueOf(value);
    }

    @Override
    public Frequency getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : Frequency.valueOf(value);
    }

    @Override
    public Frequency getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : Frequency.valueOf(value);
    }
}

