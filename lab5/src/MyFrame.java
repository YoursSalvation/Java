import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.Flow;
import java.util.jar.JarEntry;

public class MyFrame {
    public static void main(String[] args) {

        JFrame frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2, 3));
        ArrayList<JTextField> masTF = new ArrayList<JTextField>();
        for (int i = 0; i < 6; i++) {
            masTF.add(new JTextField("Текстовое поле " + (1 + i)));
            northPanel.add(masTF.get(i));
        }

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        JSlider slider = new JSlider();
        southPanel.add(slider);

        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(9, 1));
        ArrayList<JCheckBox> masCB = new ArrayList<JCheckBox>();
        for (int i =0; i < 9; i++) {
            masCB.add(new JCheckBox("Выбор" + (i + 1)));
            westPanel.add(masCB.get(i));
        }

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        String[] items = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
        JComboBox comboBox = new JComboBox(items);
        centerPanel.add(comboBox);
        JSpinner spinner = new JSpinner();
        centerPanel.add(spinner);
        ButtonGroup RBGroup = new ButtonGroup();
        JRadioButton RB1 = new JRadioButton("Радиокнопка 1");
        JRadioButton RB2 = new JRadioButton("Радиокнопка 2");
        RBGroup.add(RB1);
        RBGroup.add(RB2);
        centerPanel.add(RB1);
        centerPanel.add(RB2);
        RB1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spinner.setValue(15);
            }
        });
        RB2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox.addItem("12");
            }
        });

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BorderLayout());
        JTextArea TA = new JTextArea("Текстовая область", 1, 30);
        eastPanel.add(TA);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = ((JSlider)e.getSource()).getValue();
                TA.setText("" + value);
            }
        });
        RB1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                TA.setText("Радиокнопка 1");
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //Component horizontalStrut = Box.createHorizontalStrut(40);
        //northPanel.add(horizontalStrut);

        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(eastPanel, BorderLayout.EAST);
        frame.add(centerPanel, FlowLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.add(westPanel, BorderLayout.WEST);
        frame.setVisible(true);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
    }
}