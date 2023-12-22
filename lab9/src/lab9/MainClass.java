package lab9;

public class MainClass {
	public static void main(String[] args) {
		String curPath = ("C:\\Users\\clubw\\eclipse-workspace\\lab8\\DBwork");
		System.out.println("Working directory = " + curPath);
		DBClass myDB = new DBClass(curPath);
		myDB.showTable("flycompany");
	}
}