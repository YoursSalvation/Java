import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListWork extends JFrame{
    JPanel panel1=null;
    private static Image image;
    static File f1;

    public static void main(String[] args) {
        ListWork window = new ListWork("Работа со списком");
        window.setVisible(true);
        window.pack();
        window.setMinimumSize(window.getSize());
    }
    public ListWork(String s){
        super(s);
        final DefaultListModel myListModel = new DefaultListModel();
        for (int i = 0; i < 10; i++){
            myListModel.addElement("" + i);
        }
        final JList myList = new JList();
        JScrollPane myScroll = new JScrollPane(myList);
        myList.setModel(myListModel);
        Box myBox1 = new Box(BoxLayout.Y_AXIS);
        final JTextField myText = new JTextField();
        myBox1.add(myText);
        Box box1 = new Box(BoxLayout.X_AXIS);
        JButton button1 = new JButton("Добавить в список");
        box1.add(button1);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myListModel.addElement(myText.getText());
            }
        });

        JButton button2 = new JButton("Убрать из списка");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                while (myListModel.contains(myText.getText())) {
                    myListModel.removeElement(myText.getText());
                }
            }
        });
        box1.add(button2);

        JButton buttonClear = new JButton("Очистить список");
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListModel.clear();
            }
        });

        Box myBox2 = new Box(BoxLayout.X_AXIS);
        JButton button3 = new JButton("Сохранить...");
        myBox2.add(button3);
        final FileDialog fdlg = new FileDialog(this, "");

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fdlg.setMode(FileDialog.SAVE);
                fdlg.setTitle("Сохранить файл");
                fdlg.setVisible(true);
                FileWriter myWriter = null;
                try {
                    myWriter = new FileWriter(fdlg.getDirectory()+fdlg.getFile());
                    BufferedWriter myBWriter = new BufferedWriter(myWriter);
                    for(int i = 0; i < myListModel.getSize(); i++){
                        myBWriter.write(""+myListModel.getElementAt(i));
                        myBWriter.newLine();
                    }
                    myBWriter.close();
                    myWriter.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        myBox2.add(Box.createHorizontalGlue());
        JButton button4 = new JButton("Загрузить...");
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fdlg.setMode(FileDialog.LOAD);
                fdlg.setTitle("Загрузить файл");
                fdlg.setVisible(true);
                FileReader myReader = null;
                try {
                    myReader = new FileReader(fdlg.getDirectory()+fdlg.getFile());
                    myListModel.clear();
                    BufferedReader myBReader = new BufferedReader(myReader);
                    String s;
                    while ((s = myBReader.readLine()) != null){
                        myListModel.addElement(s);
                    }
                    myBReader.close();
                    myReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        myList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                JList tempList = (JList)e.getSource();
                f1 = new File(tempList.getSelectedValue().toString()+".jpg");
                try {
                    image = ImageIO.read(f1);
                    loadImage(image);
                } catch (IOException e1) {
                    showDialog();
                    panel1.repaint();
                }
            }
        });

        panel1 = new JPanel();
        Box centerBox = new Box(BoxLayout.X_AXIS);
        centerBox.add(myScroll);
        centerBox.add(panel1);
        add(centerBox,BorderLayout.CENTER);

        myBox2.add(button4);
        add(myBox2,BorderLayout.SOUTH);

        box1.add(buttonClear);
        myBox1.add(box1);
        add(myBox1,BorderLayout.NORTH);
    }
    public void loadImage(Image im){
        Graphics2D g = (Graphics2D)panel1.getGraphics();
        g.drawImage(im, 0, 0, null);
    }
    public void showDialog(){
        JDialog myDialog = new JDialog();
        myDialog.setModal(true);
        //окно будет недоступным
        myDialog.add(new JLabel("No file!!!"));
        myDialog.pack();
        myDialog.setLocation(getLocation().x+getWidth()/2, getLocation().y+getHeight()/2);
        myDialog.setVisible(true);
    }
}