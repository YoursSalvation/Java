package lab8;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

public class MainGUI {
static JFrame mainFrame;
static JScrollPane scrollPane;
static DBClass myDB;
public static void main(String[] args) {
mainFrame = new JFrame("SuperDB_Viewer");
mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
scrollPane = new JScrollPane();
String curPath = ("C:\\Users\\clubw\\eclipse-workspace\\lab8\\DBwork");
myDB = new DBClass(curPath);
String buttonNames[] = {"Show flycompany", "Show planes", "Show helicopters"};
String tableNames[] = {"flycompany", "plane", "helicopter"};
HashMap<String, String> mapForTables = new HashMap<>();
for (int i = 0; i < buttonNames.length; i++) {
mapForTables.put(buttonNames[i], tableNames[i]);
}
mainFrame.add(setMenu(buttonNames, mapForTables), BorderLayout.NORTH);
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
}

	private static Component setMenu(String[] buttonNames, HashMap<String, String> mapForTables) {
		Box mainMenu = new Box(BoxLayout.X_AXIS);
		for (int i = 0; i < buttonNames.length; i++) {
			JButton tempButton = new JButton(buttonNames[i]);
			tempButton.addActionListener(e -> {
				if (e.getActionCommand().equals("Show planes")) {
					showPlaneTable();
				} else if (e.getActionCommand().equals("Show helicopters")) {
					showHelicopterTable();
				}
				else {
					JTable tempTable = myDB.getTableWithJoin(mapForTables.get(e.getActionCommand()));
					mainFrame.remove(scrollPane);
					scrollPane = new JScrollPane(tempTable);
					tempTable.setFillsViewportHeight(true);
					mainFrame.add(scrollPane, BorderLayout.CENTER);
					mainFrame.pack();
				}
			});
			mainMenu.add(tempButton);
		}
	return mainMenu;
	}
	
	private static void showPlaneTable() {
		JFrame planeFrame = new JFrame("Plane Table");
		JTable planeTable = myDB.getTableWithJoin("plane");
		JScrollPane planeScrollPane = new JScrollPane(planeTable);
		planeFrame.add(planeScrollPane);
		planeFrame.setSize(400, 200);
		planeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		planeFrame.setVisible(true);
	}
	

	private static void showHelicopterTable() {
		JFrame helicopterFrame = new JFrame("Helicopter Table");
		JTable helicopterTable = myDB.getTableWithJoin("helicopter");
		JScrollPane helicopterScrollPane = new JScrollPane(helicopterTable);
		helicopterFrame.add(helicopterScrollPane);
		helicopterFrame.setSize(400, 200);
		helicopterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		helicopterFrame.setVisible(true);
	}
}