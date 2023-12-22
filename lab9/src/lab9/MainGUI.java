package lab9;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.TableModel;

public class MainGUI {
	static JFrame mainFrame;
	static JScrollPane scrollPane;
	static DBClass myDB;
	static FileDialog fdlg;
	static String curTableName = "";

	
	public static void main(String[] args) {
		mainFrame = new JFrame("SuperDB_Viewer");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scrollPane = new JScrollPane();
		String curPath = ("C:\\Users\\clubw\\eclipse-workspace\\lab8\\DBwork");
		myDB = new DBClass(curPath);
		String buttonNames[] = {"Show flycompany", "Show planes", "Show helicopters"};
		String tableNames[] = {"flycompany", "plane", "helicopter"};
		String tableNamesRus[] = { "Инфо о авиакомпании", "Инфо о самолетах", "Инфо о вертолетах"};

		HashMap<String, String> mapForTables = new HashMap<>();
		HashMap<String, String> mapForTablesRus = new HashMap<>();

		
		for (int i = 0; i < buttonNames.length; i++) {
			mapForTables.put(buttonNames[i], tableNames[i]);
			mapForTablesRus.put(buttonNames[i], tableNamesRus[i]);
		}
		mainFrame.add(setMenu(buttonNames, mapForTables, mapForTablesRus), BorderLayout.NORTH);
		mainFrame.add(setBottom(), BorderLayout.SOUTH);
		mainFrame.setSize(600, 400);
		mainFrame.setMinimumSize(mainFrame.getSize());
		mainFrame.setVisible(true);
		mainFrame.pack();
		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Exit");
				myDB.closeConnection();
				e.getWindow().dispose();
			}
		});
		fdlg = new FileDialog(mainFrame, "");
		fdlg.setMode(FileDialog.SAVE);
	}

	private static Component setMenu(String[] buttonNames, HashMap<String, String> mapForTables, HashMap<String, String> mapForTablesRus) {
		Box mainMenu = new Box(BoxLayout.X_AXIS);
		for (int i = 0; i < buttonNames.length; i++) {
			JButton tempButton = new JButton(buttonNames[i]);
			tempButton.addActionListener(e -> {
				if (e.getActionCommand().equals("Show planes")) {
					JFrame planeFrame = new JFrame("Plane Table");
					JTable planeTable = myDB.getTableWithJoin(mapForTables.get(e.getActionCommand()));
					curTableName = mapForTablesRus.get(e.getActionCommand());
					JScrollPane scrollPane = new JScrollPane(planeTable);
					
					planeFrame.remove(scrollPane);
					planeTable.setFillsViewportHeight(true);
					planeFrame.add(scrollPane, BorderLayout.CENTER);
					planeFrame.pack();
					planeFrame.add(setBottom(), BorderLayout.SOUTH);
					planeFrame.setSize(400, 200);
					
					BorderLayout layout = (BorderLayout) planeFrame.getContentPane().getLayout();
					Box southBox = (Box) layout.getLayoutComponent(BorderLayout.SOUTH);
					
					for (int j = 0; j < southBox.getComponentCount(); j++) {
                    	southBox.getComponent(j).setEnabled(true);
                    }
					
					planeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					planeFrame.setVisible(true);
				} else if (e.getActionCommand().equals("Show helicopters")) {
					JFrame helicopterFrame = new JFrame("Helicopter Table");
					JTable helicopterTable = myDB.getTableWithJoin(mapForTables.get(e.getActionCommand()));
					curTableName = mapForTablesRus.get(e.getActionCommand());
					JScrollPane scrollPane = new JScrollPane(helicopterTable);
					
					helicopterFrame.remove(scrollPane);
					helicopterTable.setFillsViewportHeight(true);
					helicopterFrame.add(scrollPane, BorderLayout.CENTER);
					helicopterFrame.pack();
					helicopterFrame.add(setBottom(), BorderLayout.SOUTH);
					helicopterFrame.setSize(400, 200);
					
					BorderLayout layout = (BorderLayout) helicopterFrame.getContentPane().getLayout();
					Box southBox = (Box) layout.getLayoutComponent(BorderLayout.SOUTH);
					
					for (int j = 0; j < southBox.getComponentCount(); j++) {
                    	southBox.getComponent(j).setEnabled(true);
                    }
					
					helicopterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					helicopterFrame.setVisible(true);
				}
				else {
					JTable tempTable = myDB.getTableWithJoin(mapForTables.get(e.getActionCommand()));
					curTableName = mapForTablesRus.get(e.getActionCommand());
					mainFrame.remove(scrollPane);
					scrollPane = new JScrollPane(tempTable);
					tempTable.setFillsViewportHeight(true);
					mainFrame.add(scrollPane, BorderLayout.CENTER);
					mainFrame.pack();
					BorderLayout layout = (BorderLayout) mainFrame.getContentPane().getLayout();
					Box southBox = (Box) layout.getLayoutComponent(BorderLayout.SOUTH);
					for (int j = 0; j < southBox.getComponentCount(); j++) {
						southBox.getComponent(j).setEnabled(true);
					}
				}
			});
			mainMenu.add(tempButton);
		}
	return mainMenu;
	}
	
	private static Component setBottom() {
		Box bottom = new Box(BoxLayout.X_AXIS);
		JButton toWord = new JButton("toWord");
		
		toWord.addActionListener(e -> {
			String[] columnNames = getTableHeader();
			String[][] data = getTableData();
			File file = getFile("Save to Word file", "docx");
			if (!file.getName().contains("nullnull")) ToOffice.toWordDocx(columnNames, data, file, 
			curTableName);
		});
		
		toWord.setEnabled(false); 
		bottom.add(toWord);
		bottom.add(Box.createHorizontalGlue());
		JButton toExcel = new JButton("toExcel");
		toExcel.addActionListener(e-> {
			String[] columnNames = getTableHeader();
			String[][] data = getTableData();
			File file = getFile("Save to Excel file", "xls");
			if (!file.getName().contains("nullnull")) ToOffice.toExcel(columnNames, data, file, 
			curTableName);
		});
		toExcel.setEnabled(false);
		bottom.add(toExcel);
		return bottom;
	}
	
	private static File getFile(String caption, String ext) {
		System.out.println(caption);
		fdlg.setTitle(caption);
		fdlg.setFile("*."+ext);
		fdlg.setVisible(true);
		String fileName = fdlg.getDirectory()+fdlg.getFile();
		if (!fileName.contains("."+ext)) fileName = fileName.concat("."+ext);
		File file = new File(fileName);
		System.out.println("file = "+file);
		return file;
	}
	
	private static String[] getTableHeader() {
		JViewport viewPort = (JViewport) scrollPane.getComponent(0);
		JTable tempTable = (JTable) viewPort.getComponent(0);
		TableModel tableModel = tempTable.getModel();
		int colCount = tableModel.getColumnCount();
		String[] columnNames = new String[colCount];
		for (int i = 0; i < colCount; i++) {
			columnNames[i] = tableModel.getColumnName(i);
		}
		System.out.println(" " + Arrays.asList(columnNames));
		return columnNames;
	}
	
	private static String[][] getTableData() {
		JViewport viewPort = (JViewport) scrollPane.getComponent(0);
		JTable tempTable = (JTable) viewPort.getComponent(0);
		TableModel tableModel = tempTable.getModel();
		int colCount = tableModel.getColumnCount();
		int rowCount = tableModel.getRowCount();
		String[][] data = new String[rowCount][colCount];
		
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				data[i][j] = (String) tableModel.getValueAt(i, j);
			}
		}
		
		for (int i = 0; i < data.length; i++) {
			System.out.println(Arrays.asList(data[i]));
		}
		return data;
	}
}