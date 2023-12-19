import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.event.*;
public class ListWithHashPopUp extends JFrame{
    JPanel panel1 = null;
    static File f1;
    final JList myList = new JList();
    final DefaultListModel myListModel = new DefaultListModel();
    private static HashMap<String, HashMap> myHashKey = new HashMap<String, HashMap>();
    static JPopupMenu myPopup;
    public static void main(String[] args) {
        ListWithHashPopUp window = new ListWithHashPopUp("Работа со списком");
        window.setVisible(true);
        window.pack();
        window.setMinimumSize(window.getSize());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public ListWithHashPopUp(String s){
        super(s);
        JScrollPane myScroll = new JScrollPane(myList);
        myList.setModel(myListModel);
        myPopup = new JPopupMenu();

        myList.setComponentPopupMenu(myPopup);
        myList.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                myList.setSelectedIndex(myList.locationToIndex(e.getPoint()));
            }
        });
        myList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
            }
        });

        Box myBox1 = new Box(BoxLayout.Y_AXIS);
        final JTextField myText = new JTextField();
        myBox1.add(myText);
        Box box1 = new Box(BoxLayout.X_AXIS);
        JButton button1 = new JButton("Добавить в список");
        box1.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListModel.addElement(myText.getText());
            }
        });
        JButton button2 = new JButton("Убрать из списка");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int j = myList.getSelectedIndex();
                myListModel.removeElementAt(j);
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

        JButton subButton = new JButton("Подтвердить");

        Box box2 = new Box(BoxLayout.Y_AXIS);
        box2.add(subButton);

        final JTextField adres = new JTextField();
        final JTextField age = new JTextField();

        subButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit(myList.getSelectedValue().toString(), adres.getText(), age.getText());
            }
        });

        box2.add(adres);
        box2.add(age);

        myList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (myList.getSelectedValue() != null) {
                    HashMap temp = myHashKey.get(myList.getSelectedValue().toString());
                    if (temp != null) {
                        age.setText(temp.get("age").toString());
                        adres.setText(temp.get("adres").toString());
                    } else {
                        age.setText("0");
                        adres.setText("0");
                    }
                }
            }
        });

        Box box3 = new Box(BoxLayout.X_AXIS);
        JButton buttonSave = new JButton("Сохранить");
        JButton buttonUpload = new JButton("Загрузить");

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

        buttonUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    upload();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        box3.add(buttonSave);
        box3.add(buttonUpload);
        box1.add(buttonClear);
        myBox1.add(box1);
        panel1 = new JPanel();
        Box centerBox = new Box(BoxLayout.X_AXIS);
        centerBox.add(myScroll);
        centerBox.add(box2);
        add(centerBox,BorderLayout.CENTER);
        add(myBox1,BorderLayout.NORTH);
        add(box3,BorderLayout.SOUTH);
    }

    public void submit(String f, String adres, String age) {
        HashMap<String, String> myHash = new HashMap<String, String>();
        myHash.put("age",age);
        myHash.put("adres",adres);
        myHashKey.put(f,myHash);
    }

    public void save() {
        final FileDialog fdlg = new FileDialog(this, "");
        fdlg.setMode(FileDialog.SAVE);
        fdlg.setTitle("Сохранить файл");
        fdlg.setVisible(true);
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(fdlg.getDirectory()+fdlg.getFile());
            BufferedWriter myBWriter = new BufferedWriter(myWriter);
            for (int i = 0; i < myListModel.getSize(); i++) {
                String f = myListModel.getElementAt(i).toString();
                HashMap<String, String> myHash = new HashMap<String, String>();
                myHash = myHashKey.get(f);
                String age = myHash.get("age");
                String adres = myHash.get("adres");

                myBWriter.write(""+f + " "+ age + " " + adres);
                myBWriter.newLine();
            }
            myBWriter.close();
            myWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    public void upload() throws IOException {
        final FileDialog fdlg = new FileDialog(this, "");
        fdlg.setMode(FileDialog.LOAD);
        fdlg.setTitle("Загрузить файл");
        fdlg.setVisible(true);
        FileReader myReader = null;
        myReader=new FileReader(fdlg.getDirectory()+fdlg.getFile());

        try (BufferedReader br = new BufferedReader(myReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 3) {
                    String key = parts[0].trim();
                    String key2 = parts[1].trim();
                    String value = parts[2].trim();
                    HashMap<String, String> myHash = new HashMap<String, String>();
                    myHash.put("adres",value);
                    myHash.put("age",key2);
                    myHashKey.put(key,myHash);
                    myListModel.addElement(key);
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}