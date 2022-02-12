/**
 * 
 */
package com.manoj.os.xmlm.db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Manoj Pawar
 *
 */
@Slf4j
@UtilityClass
class JdbcUtils {

	<T> T resultSetToObject(PreparedStatement preparedStatement, RowMapper rowMapper) {
		T result = null;
		try (var resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				result = rowMapper.map(resultSet);
			}
		} catch (SQLException e) {
			log.error("Error {}", e);
		}
		return result;
	}

	<T> List<T> resultSetToList(PreparedStatement preparedStatement, RowMapper rowMapper) {
		var list = new ArrayList<T>();
		try (var resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				list.add(rowMapper.map(resultSet));
			}
		} catch (SQLException e) {
			log.error("Error {}", e);
		}
		return list;
	}

	void interceptPrepareStatementParameters(PreparedStatement preparedStatement, Object... parameters)
			throws SQLException {
		var parameterIndex = 1;
		for (var object : parameters) {
			if (object instanceof String) {
				preparedStatement.setString(parameterIndex, (String) object);
			} else if (object instanceof Short) {
				preparedStatement.setShort(parameterIndex, (Short) object);
			} else if (object instanceof Integer) {
				preparedStatement.setInt(parameterIndex, (Integer) object);
			} else if (object instanceof Long) {
				preparedStatement.setLong(parameterIndex, (Long) object);
			} else if (object instanceof Float) {
				preparedStatement.setFloat(parameterIndex, (Float) object);
			} else if (object instanceof Double) {
				preparedStatement.setDouble(parameterIndex, (Double) object);
			} else if (object instanceof Date) {
				preparedStatement.setDate(parameterIndex, (Date) object);
			} else {
				preparedStatement.setObject(parameterIndex, object);
			}
			parameterIndex++;
		}
	}
}
