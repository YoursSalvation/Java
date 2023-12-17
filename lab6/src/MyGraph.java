import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MyGraph extends JFrame implements ActionListener{
    private JMyPanel myPanel = new JMyPanel();
    public static void main(String[] args) {
        new MyGraph("Окно с графикой");
    }
    public MyGraph(String s){
        super(s);
        JMenuBar myMenuBar = new JMenuBar();
        JMenu menu1 = new JMenu("Фигуры");
        JMenuItem[] first = new JMenuItem[6];
        for (int i = 0; i < 6; i++){
            first[i] = new JMenuItem(JMyPanel.Figure.values()[i].toString());
            first[i].addActionListener(this);
            menu1.add(first[i]);
//            if (i != 4){
//                myBox.add(Box.createHorizontalGlue());
//            }
        }
        myMenuBar.add(menu1);
        //setJMenuBar(myMenuBar);
        myMenuBar.setAlignmentX(CENTER_ALIGNMENT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(myMenuBar,BorderLayout.NORTH);
        add(myPanel,BorderLayout.CENTER);
        Dimension size = getSize();
        size.setSize(size.width, size.height+200);
        setMinimumSize(size);
        pack();
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        myPanel.ris(e.getActionCommand());
    }
}