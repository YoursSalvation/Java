package lab9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import javax.swing.*;
import java.util.ArrayList;

public class DBClass {
	Connection conn = null;
	Statement stmt = null;
	HashMap<String, String> selectsForTables;
	public DBClass(String curPath) {
		super();
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file://" + curPath + "\\myDB", "sa", "");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
			ResultSet.CONCUR_UPDATABLE);
		} catch (ClassNotFoundException | SQLException e) {
			Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, e);
			System.out.println("Trouble with connection!!");
		} 
		finally {
			if (conn!=null) System.out.println("DB connected!!");
			selectsForTables = new HashMap();
			selectsForTables.put("flycompany", "select flycompany.plane_id, flycompany.helicopter_id, flycompany.firm, flycompany.maxpassengers, "
			+ "flycompany.tip, flycompany.maxspeed, flycompany.power from flycompany ");
			selectsForTables.put("plane", "select plane.plane_id, plane.firm, plane.maxpassengers,"
			+ " plane.tip, plane.maxspeed from plane");
			selectsForTables.put("helicopter", "select helicopter.helicopter_id, helicopter.firm, helicopter.maxpassengers, helicopter.tip, "
			+ "helicopter.power from helicopter");
		}
	}
	
	public void showTable(String tableName) {
		System.out.println("Table = "+tableName);
		ResultSet localRS=null;
		int rowCount=0;
		try {
			localRS=stmt.executeQuery("select * from "+tableName);
			localRS.last();
			rowCount = localRS.getRow();
			System.out.println("RowsCount="+rowCount);
			ResultSetMetaData rsmd = localRS.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			localRS.beforeFirst();
			while (localRS.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) System.out.print(", ");
					String columnValue = localRS.getString(i);
					System.out.print(rsmd.getColumnName(i)+ " " + columnValue); 
				}
				System.out.println("");
			}
		} catch (SQLException e) {
		System.out.println("Trouble with query!!");
		e.printStackTrace();
		}
	}
	
	public JTable getTableWithJoin(String tableName) {
		ResultSet localRS = null;
		ArrayList<String> colNames = new ArrayList<>();
		String[][] data = null;
		try {
			localRS = stmt.executeQuery(selectsForTables.get(tableName));
			ResultSetMetaData rsmd = localRS.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			
			for (int i = 1; i <= columnsNumber; i++) {
				colNames.add(rsmd.getColumnName(i));
			}
			
			localRS.last();
			int rowCount = localRS.getRow();
			data = new String[rowCount][colNames.size()];
			localRS.beforeFirst();
			
			while (localRS.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					data[localRS.getRow() - 1][i - 1] = localRS.getString(i);
					System.out.print(data[localRS.getRow()-1][i-1] + " ");
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			System.out.println("Trouble with query!!");
			e.printStackTrace();
		}
		JTable tempTable = new JTable(data, colNames.toArray());
		return tempTable;
	}
		
	public void closeConnection() {
		if (conn!=null)
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}