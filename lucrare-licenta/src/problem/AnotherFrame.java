package problem;

import javax.swing.*;
import java.awt.*;

public class AnotherFrame extends JFrame {

    private static final Dimension DEFAULT_SIZE = new Dimension( 400, 500 );
    private static final long serialVersionUID = 1L;
    private String  text;
    public AnotherFrame() {
        Warehouse pb=new Warehouse();
        pb.execute();

        // set flow layout for the frame
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(new Dimension(550, 500));
        Dimension frameSize = this.getSize();

        JLabel label = new JLabel("Cea mai optimă soluție:");
        label.setBounds(30,20,200,40);
        add(label);
        label.setLayout(new FlowLayout());
        text = pb.text;
        JTextArea ta = new JTextArea(text,0, 6);
        ta.setBounds(50,80,400,100);
        add(ta);
        ta.setLayout(new FlowLayout());
        ImageIcon background=new ImageIcon("D:\\llicenta\\lucrare-licenta\\img\\rr.png");
        Image img=background.getImage();
        Image temp=img.getScaledInstance(frameSize.width,frameSize.height,Image.SCALE_SMOOTH);
        background =new ImageIcon(temp);
        JLabel back=new JLabel(background);
        back.setLayout(null);
        back.setBounds(0,0,frameSize.width,frameSize.height);
        add(back);
        back.setLayout(new FlowLayout());
    }
}