package com.Rev.Core;

import static com.Rev.Core.AppUtils.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.Rev.Core.Primitive.aSet;
import com.Rev.Core.Primitive.Data.aDataField;

public class Main {

	public static App Main;

	// entreport
	public static void main(String[] args) {
		Log("---");
		//

		Main = new App();
		Log("___");

	}

	//// get auto-incremented id
	//
	// int newID;
	// ResultSet rs = pstmt.getGeneratedKeys();
	// if(rs.next()) {
	// newID = rs.getInt(1);
	// }
}
