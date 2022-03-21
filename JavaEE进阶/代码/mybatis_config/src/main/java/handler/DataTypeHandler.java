package handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author
 * @Program
 * @create 2022-03-20-22:00
 */
public class DataTypeHandler extends BaseTypeHandler<Date> {
    // 将 java 类型转换成数据库需要的类型
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        long time = date.getTime();
        preparedStatement.setLong(i,time);
    }

    // 将数据库类型转换成 java 类型
    // String 参数代表要转换的名称
    // ResultSet 查询出的结果集
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        // 获得结果集中需要的数据（long） 将其转换成date类型，并返回
        long aLong = resultSet.getLong(s);
        Date date = new Date(aLong);
        return date;
    }

    // 将数据库类型转换成 java 类型
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        long aLong = resultSet.getLong(i);
        Date date = new Date(aLong);
        return date;
    }

    // 将数据库类型转换成 java 类型
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        long aLong = callableStatement.getLong(i);
        Date date = new Date(aLong);
        return date;
    }
}
