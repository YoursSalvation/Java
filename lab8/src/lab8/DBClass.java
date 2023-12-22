package lab8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;

public class DBClass {
	Connection conn = null; // объект для связи с БД
	Statement stmt = null; // объект для создания SQL-запросов
	HashMap<String, String> selectsForTables;
	public DBClass(String curPath) {
		super();
		try { // нужен блок try … catch для работы с БД
			Class.forName("org.h2.Driver"); // подгружаем драйвер для H2
			// получаем доступ к БД jdbc:h2:путь к папке проекта\\myDB
			conn = DriverManager.getConnection("jdbc:h2:file://" + curPath + "\\myDB", "sa", "");
			// получаем объект для выполнения команд SQL
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
			ResultSet.CONCUR_UPDATABLE);
		} catch (ClassNotFoundException | SQLException e) { // обрабатываем возможные ошибки
			 //подключаем логгер для вывода понятных сообщений в случае ошибок 
			Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, e);
			System.out.println("Trouble with connection!!");
		} 
		finally { //финальный блок для вывода сообщения о подключении
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
	public void showTable(String tableName) {//метод для вывода в консоль содержимого нужной таблицы
		System.out.println("Table = "+tableName); //выводим имя таблицы
		ResultSet localRS=null; //локальный объект для результатов запроса
		int rowCount=0; //кол-во записей в таблице
		try {
			localRS=stmt.executeQuery("select * from "+tableName); //выполняем запрос для нужной таблицы
			localRS.last(); //переходим на последнюю запись
			rowCount = localRS.getRow(); //получаем номер последнего ряда 
			System.out.println("RowsCount="+rowCount); //выводим его, это кол-во записей в таблице
			ResultSetMetaData rsmd = localRS.getMetaData(); //получаем доп.данные о таблице
			int columnsNumber = rsmd.getColumnCount(); //и берем из них кол-во полей
			localRS.beforeFirst(); //переходим в начало списка записей
			while (localRS.next()) { //цикл пока есть записи
				for (int i = 1; i <= columnsNumber; i++) { //цикл по всем полям таблицы
				if (i > 1) System.out.print(", "); //запятая после первого поля
					String columnValue = localRS.getString(i); //получаем значение поля
					//выводим имя поля и его значение
					System.out.print(rsmd.getColumnName(i)+ " " + columnValue); 
				}
				System.out.println("");
			}
		} catch (SQLException e) { //обрабатываем ошибки, если будут
		System.out.println("Trouble with query!!");
		e.printStackTrace();
		}
	}
	public JTable getTableWithJoin(String tableName) {
		ResultSet localRS = null;
		ArrayList<String> colNames = new ArrayList<>(); // массив для названий полей таблицы
		String[][] data = null; // двумерный массив для записей
		try {
		localRS = stmt.executeQuery(selectsForTables.get(tableName));
		ResultSetMetaData rsmd = localRS.getMetaData(); // получаем доп.данные о таблице
		int columnsNumber = rsmd.getColumnCount(); // и берем из них кол-во полей
		for (int i = 1; i <= columnsNumber; i++) { // цикл по всем полям таблицы
		colNames.add(rsmd.getColumnName(i)); // добавляем название поля в массив
		}
		localRS.last(); // переходим на последнюю запись результата SQL-запроса
		int rowCount = localRS.getRow(); // считаем кол-во записей в результате SQL-запроса
		data = new String[rowCount][colNames.size()]; // задаем размер двумерного массива записей
		localRS.beforeFirst(); // переходим в начало списка записей
		while (localRS.next()) { // цикл пока есть записи
		for (int i = 1; i <= columnsNumber; i++) { // цикл по всем полям таблицы
		 // в нужный элемент двумерного массива вставляем элемент записи
		 data[localRS.getRow() - 1][i - 1] = localRS.getString(i);
		 System.out.print(data[localRS.getRow()-1][i-1] + " "); //выводим его для проверки
		}
		System.out.println("");
		}
		} catch (SQLException e) {
		System.out.println("Trouble with query!!");
		e.printStackTrace();
		}
		JTable tempTable = new JTable(data, colNames.toArray());
		return tempTable; // возвращаем таблицу как результат метода
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