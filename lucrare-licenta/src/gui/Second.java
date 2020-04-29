package gui;

import drawing.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Second extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    public Second() {

        // set flow layout for the frame
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(new Dimension(700, 550));
        Dimension frameSize = this.getSize();
        Color color = new Color(226, 247, 248);

        JLabel vLabel = new JLabel("Vizualizati solutia problemei:");
        vLabel.setBounds(30, 330, 300, 40);
        vLabel.setFont(new Font(vLabel.getName(), Font.BOLD, 18));
        add(vLabel);

        ImageIcon icon = new ImageIcon("D:\\llicenta\\lucrare-licenta\\img\\btn.png");
        JButton button = new JButton(icon);
        button.setBounds(285, 330, 100, 40);
        add(button);
        button.setLayout(new FlowLayout());
        //set action listeners for buttons
        button.addActionListener(this);
        button.setSize(100, 50);

        // define a custom short action command for the button
        button.setActionCommand("Vizualizarea solutiei");

        JLabel dLabel = new JLabel("Afisati solutia problemei:");
        dLabel.setBounds(30, 250, 300, 40);
        dLabel.setFont(new Font(dLabel.getName(), Font.BOLD, 18));
        add(dLabel);
        JButton button1 = new JButton("Display");
        button1.setBounds(250, 250, 100, 40);
        button1.setBackground(color);
        add(button1);
        button1.setLayout(new FlowLayout());

        //set action listeners for buttons
        button1.addActionListener(this);
        button1.setSize(100, 50);

        // define a custom short action command for the button
        button1.setActionCommand("Display");
        button1.setFont(new Font(button1.getName(), Font.BOLD, 18));

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

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if (action.equals("Vizualizarea solutiei")) {
            new Graph();
        }
        if (action.equals("Display")) {
           new AnotherFrame();
        }

    }

    public static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new Second();
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

}