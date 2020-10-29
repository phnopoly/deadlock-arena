package com.deadlockArena.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
public class Uploader {

	private final String STATEMENT = "SELECT * FROM deadlock.%s";
	private final String INSERT_BLOB_QUERY = "INSERT INTO deadlock.%s (FILE_NAME, CONTENTS) values (?, ?)";
	private final String SELECT_QUERY = "SELECT * FROM deadlock.%s";

	@Value("${spring.datasource.url}")
	public String url = "jdbc:oracle:thin:@server.ericopoku.com:1521:xe";

	@Value("${spring.datasource.username}")
	public String username = "deadlock";

	@Value("${spring.datasource.password}")
	public String password = "Deadlock123";

	private Connection conn;
	private Statement stmt;

	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.conn = DriverManager.getConnection(this.url, this.username, this.password);
			DeadlockArenaDatabase.LOG.debug("Connection success!");
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public void uploadBlob(final String fileName, final String table, final byte[] bytes) {
		try {
			final PreparedStatement preparedStmt = this.conn
					.prepareStatement(String.format(this.INSERT_BLOB_QUERY, table));
			preparedStmt.setString(1, fileName);
			preparedStmt.setBytes(2, bytes);
			preparedStmt.execute();
			DeadlockArenaDatabase.LOG.debug("Done Executing " + fileName);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

	public void readBlob(final String table) {
		try {
			final ResultSet rs = this.conn.createStatement().executeQuery(String.format(this.SELECT_QUERY, table));
			final ResultSetMetaData meta = rs.getMetaData();
			while (rs.next()) {
				for (int col = 1; col <= meta.getColumnCount(); col++) {
					final Object value = rs.getObject(col);
					if (value != null) {
						DeadlockArenaDatabase.LOG.debug(value.toString());
					}
				}
				DeadlockArenaDatabase.LOG.debug("----------------------------------------");
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}
}
