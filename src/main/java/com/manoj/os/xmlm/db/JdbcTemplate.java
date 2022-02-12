/**
 * 
 */
package com.manoj.os.xmlm.db;

import static com.manoj.os.xmlm.db.JdbcUtils.interceptPrepareStatementParameters;
import static com.manoj.os.xmlm.db.JdbcUtils.resultSetToList;
import static com.manoj.os.xmlm.db.JdbcUtils.resultSetToObject;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Manoj Pawar
 *
 */
@Slf4j
public class JdbcTemplate {
	private static JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	private JdbcTemplate(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void initJdbcTemplate(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public static JdbcTemplate getInstance() {
		if (Objects.isNull(jdbcTemplate)) {
			throw new JdbcException(
					"JdbcTemplate instance is not initiated. Call initJdbcTemplate method on application startup.");
		}
		return jdbcTemplate;
	}

	public <T> T queryForObject(@NonNull String sql, @NonNull RowMapper rowMapper, Object... parameters) {
		try (var connection = dataSource.getConnection(); var prepareStatement = connection.prepareStatement(sql)) {
			interceptPrepareStatementParameters(prepareStatement, parameters);
			return resultSetToObject(prepareStatement, rowMapper);
		} catch (SQLException e) {
			log.error("Error occure while query-for-object {}", e);
		}
		return null;
	}

	public <T> List<T> queryForList(@NonNull String sql, @NonNull RowMapper rowMapper, Object... parameters) {
		try (var connection = dataSource.getConnection(); var prepareStatement = connection.prepareStatement(sql)) {
			interceptPrepareStatementParameters(prepareStatement, parameters);
			return resultSetToList(prepareStatement, rowMapper);
		} catch (SQLException e) {
			log.error("Error occure while query-for-list {}", e);
			throw new JdbcException("Error occure while creating jdbc-batach {}", e);
		}
	}

	public int update(@NonNull String sql, Object... parameters) {
		try (var connection = dataSource.getConnection(); var prepareStatement = connection.prepareStatement(sql)) {
			interceptPrepareStatementParameters(prepareStatement, parameters);
			return prepareStatement.executeUpdate();
		} catch (SQLException e) {
			log.error("Error occure while update operation {}", e);
			throw new JdbcException("Error occure while creating jdbc-batach {}", e);
		}
	}

	public JdbcBatch getBatch(@NonNull String sql) {
		try {
			var connection = dataSource.getConnection();
			var prepareStatement = connection.prepareStatement(sql);
			return JdbcBatch.builder().preparedStatement(prepareStatement).connection(connection).build();
		} catch (SQLException e) {
			log.error("Error {}", e);
			throw new JdbcException("Error occure while creating jdbc-batach {}", e);
		}
	}

}
