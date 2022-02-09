package com.Rev.P1_Core.Data;

import static com.Rev.P1_Core.AppUtils.Log;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.Rev.P1_Core.Math.aVector;
import com.Rev.P1_Core.Primitive.aList;
import com.Rev.P1_Core.Primitive.aMap.Entry;

public class DB_Manager {

	public Connection Connection;

	public DB_Manager(Connection connection) {
		this.Connection = connection;
	}

	private Connection connectMariaDB() {
		// Server DB
		Connection connection = null;
		try {
			Properties props = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream input = loader.getResourceAsStream("jdbc.properties");
			props.load(input);
			Log(props);

			String connectionString = "jdbc:mariadb://" + props.getProperty("hostname") + ":"
					+ props.getProperty("port") + "/" + props.getProperty("dbname") + "?user="
					+ props.getProperty("username") + "&password=" + props.getProperty("password");

			// Class.forName("org.mariadb.jdbc.Driver");

			connection = DriverManager.getConnection(connectionString);

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	private Connection connectSqliteDB(String fileName) {
		// Local DB, copy data from Maria, werx but not implemented atm
		String url = "jdbc:sqlite:C:\\Users\\Public\\Rev\\" + fileName; // <storage path, folder must already exist lol

		try {
			// moved from predicate of try(){}, leaving it there auto-closed connection lol
			Connection conn = DriverManager.getConnection(url);
			if (conn == null) {
				Connection = conn;
				DatabaseMetaData meta = conn.getMetaData();
				Log("The driver name is " + meta.getDriverName());
				Log("A new database has been created.");
				// build schema if there were one lolol
				return conn;
			} else {
				Connection = conn;
				DatabaseMetaData meta = conn.getMetaData();
				Log("got Database");
				Log("The driver name is " + meta.getDriverName());
				return conn;
			}

		} catch (SQLException e) {
			Log(e.getMessage());
			return null;
		}

		// return null;
	}

	private boolean isEmpty(Connection connection) {
		try {
			Log("(ConnectionClosed?)" + connection.isClosed());

			DatabaseMetaData meta = connection.getMetaData();
			ResultSet resultSet;
			resultSet = meta.getTables(null, null, null, new String[] { "TABLE" });
			Log(">> " + resultSet + " : " + resultSet.next());
			return !resultSet.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	private boolean tableExists(Connection connection, String tableName) throws SQLException {
		DatabaseMetaData meta = connection.getMetaData();
		ResultSet resultSet = meta.getTables(null, null, tableName, new String[] { "TABLE" });

		return resultSet.next();
	}

	/////////
	//
	public boolean tableExists(String tableName) throws SQLException {
		DatabaseMetaData meta = this.Connection.getMetaData();
		ResultSet resultSet = meta.getTables(null, null, tableName.toLowerCase(), new String[] { "TABLE" });

		return resultSet.next();
	}

	public void createTable(Connection connection, String tableName, boolean autoInc,
			Entry<String, Object> columnNames) {
		Log("_Filling[Creatures]");
		try {
			if (tableExists(connection, tableName)) {
				String sql = "DROP TABLE IF EXISTS " + tableName + ";";
				connection.createStatement().executeUpdate(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ResultSet getTable(String tableName) {

		try {
			Statement s = Connection.createStatement();
			ResultSet result;
			result = s.executeQuery("SELECT * FROM " + tableName);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public int getTableHeight(ResultSet subject) {
		int h = -1;
		try {
			subject.last();
			h = subject.getRow();
			subject.first();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return h;
	}

	public int getTableWidth(ResultSet subject) {
		int w = -1;
		try {
			ResultSetMetaData rs = subject.getMetaData();

			w = rs.getColumnCount();

			return w;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return w;
	}

	public static aVector getTableSize(ResultSet subject) {
		try {
			ResultSetMetaData rs = subject.getMetaData();

			int x = rs.getColumnCount();
			subject.last();
			int y = subject.getRow();
			subject.first();
			return new aVector(x, y);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new aVector(-1);
	}

	public aList<String> getColumnNames(String ofTable) {
		ResultSet rs = getTable(ofTable);
		aList<String> columns = new aList<String>();
		try {

			ResultSetMetaData rsMetaData = rs.getMetaData();
			int count = rsMetaData.getColumnCount();
			for (int i = 1; i <= count; i++) {
				String s = rsMetaData.getColumnName(i);
				columns.append(s);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return columns;
	}

	public void logTable(String tableName) {
		Log(getColumnNames(tableName));
		ResultSet rs = getTable(tableName);
		Log("   " + getTableSize(rs));
	}

	public String toLog() {
		String log = "";
		try {
			DatabaseMetaData meta = Connection.getMetaData();
			ResultSet resultSet;
			resultSet = meta.getTables(null, null, null, new String[] { "TABLE" });
			// log += (">> " + resultSet + " : " + resultSet.next());
			while (resultSet.next()) {
				String name = resultSet.getString("TABLE_NAME");
				String schema = resultSet.getString("TABLE_SCHEM");
				log += ("[" + name.toUpperCase() + "] on schema (" + schema + ")\n");
				log += "  " + getColumnNames(name) + "\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return log;
	}
}