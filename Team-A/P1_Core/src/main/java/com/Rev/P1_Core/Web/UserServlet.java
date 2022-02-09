package com.Rev.P1_Core.Web;

import static com.Rev.P1_Core.AppUtils.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Rev.P1_Core.Data.DB_Manager;
import com.Rev.P1_Core.Math.aVector;
import com.Rev.P1_Core._MonDexApp.MonDirector;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class UserServlet extends HttpServlet {

	public static Connection DB_Link;
	private DB_Manager Management;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(202);
		if (MonDirector.DB_Link == null)
			DB_Link = connectMariaDB();

		this.Management = new DB_Manager(DB_Link);

		Log(DB_Link);
		Log(this.Management);
		try {
			Log("CREATURES TABLE EXISTS? " + this.Management.tableExists("Creatures"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Log(new aVector(1, 2, 3));
		resp.getWriter().print("Pongity! " + DB_Link.getClass().getSimpleName());
	}

	private static Connection connectMariaDB() {
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

			Class.forName("org.mariadb.jdbc.Driver");

			connection = DriverManager.getConnection(connectionString);

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

}