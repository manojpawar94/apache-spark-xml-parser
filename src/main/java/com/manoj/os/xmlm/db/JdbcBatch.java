package com.manoj.os.xmlm.db;

import static com.manoj.os.xmlm.db.JdbcUtils.interceptPrepareStatementParameters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
public class JdbcBatch implements AutoCloseable {
	private final Connection connection;
	private final PreparedStatement preparedStatement;
	@Getter
	@Setter
	@Builder.Default
	private int batchSize = 10;
	@Getter
	@Builder.Default
	private int counter = 0;

	public void put(Object... objects) {
		try {
			interceptPrepareStatementParameters(preparedStatement, objects);
			preparedStatement.addBatch();
			counter++;

			if (counter == batchSize) {
				preparedStatement.executeBatch();
				preparedStatement.clearBatch();
				counter = 0;
			}

		} catch (SQLException e) {
			log.error("Error: {}", e);
		}
	}

	@Override
	public void close() throws Exception {
		preparedStatement.closeOnCompletion();
		connection.close();
	}

}
