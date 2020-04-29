package gui;

import problem.Warehouse;

import javax.swing.*;
import java.awt.*;

public class AnotherFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private String  text;
    private  String type = Panel.qual;

    public AnotherFrame() {
        Warehouse pb = new Warehouse();
        pb.execute();
        Color color = new Color(238, 238, 238);
        // set flow layout for the frame
        this.getContentPane().setLayout(new FlowLayout());
        this.setSize(new Dimension(550, 500));
        this.setTitle("Afisarea Solutiei");
        Dimension frameSize = this.getSize();

        JLabel label = new JLabel("Cea mai optimă soluție:");
        label.setFont(new Font(label.getName(), Font.BOLD, 20));
        label.setBounds(30,20,200,40);
        add(label);
        label.setLayout(new FlowLayout());
        text = pb.text;
        JTextArea ta = new JTextArea(text,0, 6);
        switch (type) {
            case "small":
                ta.setBounds(50, 80, 400, 100);
                break;
            case "medium":
                ta.setBounds(50, 80, 400, 150);
                break;
            case "large":
                ta.setBounds(50, 80, 400, 250);
                break;
        }
        ta.setBackground(color);
        add(ta);
        ta.setLayout(new FlowLayout());
        ImageIcon background=new ImageIcon("D:\\llicenta\\lucrare-licenta\\img\\mark.png");
        Image img=background.getImage();
//        Image temp=img.getScaledInstance(frameSize.width/2,frameSize.height/2,Image.SCALE_SMOOTH);
        background =new ImageIcon(img);

        JLabel back=new JLabel(background);
        back.setLayout(new BorderLayout());
        back.setBounds(200,500,frameSize.width/2,frameSize.height/2);
        add(back);

        setVisible(true);
    }
}
