package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JFrame{

    private ButtonGroup G1;
    public static String qual;
    private static final long serialVersionUID = 1L;

    public Panel() {
        // set flow layout for the frame
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(new Dimension(700, 550));
        Dimension frameSize = this.getSize();
        Color color = new Color(235, 219, 196);
        Color color1 = new Color(232, 228, 178);

        // Initialization of object of "JRadioButton" class.
        JRadioButton btn1 = new JRadioButton();
        JRadioButton btn2 = new JRadioButton();
        JRadioButton btn3 = new JRadioButton();

        JButton radio = new JButton("Continuati");
        radio.setFont(new Font(radio.getName(), Font.BOLD, 14));
        radio.setBackground(color1);
        // Initialization of object of "ButtonGroup" class.
        G1 = new ButtonGroup();
        // Initialization of object of " JLabel" class.
        JLabel L1 = new JLabel("Dimensiunea datelor de intrare:");
        L1.setFont(new Font(L1.getName(), Font.BOLD, 20));

        btn1.setText("Mica");
        btn1.setFont(new Font(btn1.getName(), Font.BOLD, 14));
        btn1.setBackground(color);

        btn2.setText("Medie");
        btn2.setFont(new Font(btn2.getName(), Font.BOLD, 14));
        btn2.setBackground(color);

        btn3.setText("Mare");
        btn3.setFont(new Font(btn3.getName(), Font.BOLD, 14));
        btn3.setBackground(color);
        // Setting Bounds of "jRadioButton2".
        btn1.setBounds(30, 300, 60, 30);
        // Setting Bounds of "jRadioButton4".
        btn2.setBounds(90, 300, 75, 30);
        // Setting Bounds of "jRadioButton4".
        btn3.setBounds(165, 300, 70, 30);

        // Setting Bounds of "jButton".
        radio.setBounds(115, 350, 120, 30);

        // Setting Bounds of JLabel "L2".
        L1.setBounds(20, 250, 350, 50);

        add(btn1);
        add(btn2);
        add(btn3);
        add(radio);
        add(L1);

        // Adding "jRadioButton1" and "jRadioButton3" in a Button Group "G2".
        G1.add(btn1);
        G1.add(btn2);
        G1.add(btn3);

        // Adding Listener to JButton.
        radio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = " ";
                if (btn1.isSelected()) {
                    text = "Ati selectat 'Mica' pentru dimensiunea datelor de intrare!";
                    qual = "small";
                } else if (btn2.isSelected()) {
                    text = "Ati selectat 'Medie' pentru dimensiunea datelor de intrare!";
                    qual = "medium";
                } else if (btn3.isSelected()) {
                    text = "Ati selectat 'Mare' pentru dimensiunea datelor de intrare!";
                    qual = "large";
                } else {
                    text = "Nu ati selectat nicio dimensiune!";
                    JOptionPane.showMessageDialog(Panel.this, text, "Mesaj", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(Panel.this, text, "Mesaj", JOptionPane.INFORMATION_MESSAGE);

                Second.createAndShowGUI();
            }
        });
        ImageIcon background = new ImageIcon("D:\\llicenta\\lucrare-licenta\\img\\bg.jpg");
        Image img = background.getImage();
        Image temp = img.getScaledInstance(frameSize.width, frameSize.height, Image.SCALE_SMOOTH);
        background = new ImageIcon(temp);
        JLabel back = new JLabel(background);
        back.setLayout(null);
        back.setBounds(0, 0, frameSize.width, frameSize.height);
        add(back);
        back.setLayout(new FlowLayout());
    }

    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new Panel();
        frame.setTitle("Warehouse Location");
        frame.validate();

        // Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

