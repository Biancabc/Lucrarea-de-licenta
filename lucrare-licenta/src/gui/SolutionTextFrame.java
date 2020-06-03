package gui;


import problem.Warehouse;

import javax.swing.*;
import java.awt.*;
public class SolutionTextFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public SolutionTextFrame(Warehouse warehouse) {
        // set flow layout for the frame
        this.getContentPane().setLayout(new FlowLayout());
        this.setSize(new Dimension(550, 500));
        this.setTitle("Afisarea Solutiei");
        Dimension frameSize = this.getSize();

        // Customized color
        Color color = new Color(238, 238, 238);

        // Set the label
        JLabel title = new JLabel("Soluția optimă este:");
        title.setFont(new Font(title.getName(), Font.BOLD, 20));
        title.setBounds(30,20,200,40);
        add(title);
        title.setLayout(new FlowLayout());

        // Create a text area for displaying the solution
        JTextArea solutionArea = new JTextArea(warehouse.solutionText,0, 6);
        solutionArea.setBounds(50, 80, 400, 250);
        solutionArea.setBackground(color);
        add(solutionArea);
        solutionArea.setLayout(new FlowLayout());

        // Add background
        ImageIcon background=new ImageIcon("img\\mark.png");
        Image img=background.getImage();
        background =new ImageIcon(img);
        JLabel back=new JLabel(background);
        back.setLayout(new BorderLayout());
        back.setBounds(200,500,frameSize.width/2,frameSize.height/2);
        add(back);

        setVisible(true);
    }
}
