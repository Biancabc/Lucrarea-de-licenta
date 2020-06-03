package gui;

import problem.FileParser;

import javax.swing.*;
import java.awt.*;

public class InputFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public InputFrame() {
        Color color = new Color(238, 238, 238);
        // set flow layout for the frame
        this.getContentPane().setLayout(new FlowLayout());
        this.setSize(new Dimension(650, 450));
        this.setTitle("Afisarea Solutiei");
        Dimension frameSize = this.getSize();

        //Text Area for the problem solution
        String content = FileParser.parse(MainFrame.selectedFilePath);
        JTextArea inputArea = new JTextArea(content,0, 6);
        inputArea.setBackground(color);
        add(inputArea);
        inputArea.setLayout(new FlowLayout());

        //Adding a background
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
