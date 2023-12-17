import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class Lab6_1 extends JFrame implements ActionListener{
    private JTextField myText;

    public static void main(String[] args) {
        new Lab6_1("Пример окна с меню");
    }
    public Lab6_1(String name){
        super(name);
        JMenuBar myMenuBar = new JMenuBar();
        JMenu menu1 = new JMenu("Пункт1");
        JMenu first = new JMenu("Пункт1_1");
        menu1.add(first);
        JMenuItem[] first_1 = new JMenuItem[3];
        for (int i = 0; i < 3; i++){
            first_1[i] = new JMenuItem("Пункт1_1_" + (i+1));
            first.add(first_1[i]);
            first_1[i].addActionListener(this);
        }
        JMenu second = new JMenu("Пункт1_2");
        menu1.add(second);
        JMenuItem[] second_1 = new JMenuItem[3];
        for (int i = 0; i < 3; i++){
            second_1[i] = new JMenuItem("Пункт1_2_" + (i+1));
            second.add(second_1[i]);
            second_1[i].addActionListener(this);
        }
        JMenu menu2 = new JMenu("Пункт2");
        JMenu first2 = new JMenu("Пункт2_1");
        menu2.add(first2);
        JMenuItem[] first2_1 = new JMenuItem[3];
        for (int i = 0; i < 3; i++){
            first2_1[i] = new JMenuItem("Пункт2_1_" + (i+1));
            first2.add(first2_1[i]);
            first2_1[i].addActionListener(this);
        }
        JMenu second2 = new JMenu("Пункт2_2");
        menu2.add(second2);
        JMenuItem[] second2_1 = new JMenuItem[3];
        for (int i = 0; i < 3; i++){
            second2_1[i] = new JMenuItem("Пункт2_2_" + (i+1));
            second2.add(second2_1[i]);
            second2_1[i].addActionListener(this);
        }
        myMenuBar.add(menu1);
        myMenuBar.add(menu2);
        myText = new JTextField();
        setJMenuBar(myMenuBar);
        add(myText,BorderLayout.SOUTH);
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        myText.setText(e.getActionCommand());
    }
}