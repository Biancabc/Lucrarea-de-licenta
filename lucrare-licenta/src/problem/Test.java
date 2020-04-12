import javax.swing.*;
import java.awt.*;

class YourMainClass {
    static JFrame mainFrame;
    static YourJLabel clsLabel;
    static JPanel pnlJPanel;
    public static void main(String[]args){
        mainFrame = new JFrame("Testing"); //initialize, and set size the frame
        mainFrame.setSize(500,500);
        pnlJPanel = new JPanel();//initialize our panel
        pnlJPanel.setLayout(new GridLayout(3,1));//set its layout to gridlayout, with grid of 3 rows and 1 column
        clsLabel = new YourJLabel(); //create a jlabel, add some text to it, then add it to the jpanel
        clsLabel.setText("some");
        pnlJPanel.add(clsLabel);
        clsLabel = new YourJLabel();
        clsLabel.setText("Text");
        pnlJPanel.add(clsLabel);
        clsLabel = new YourJLabel();
        clsLabel.setText("drawn");
        pnlJPanel.add(clsLabel);
        mainFrame.add(pnlJPanel);//add the jpanel to the frame
        mainFrame.pack();        //believe you already know these two lines
        mainFrame.setVisible(true);
    }
}
class YourJLabel extends JLabel {
    YourJLabel(){
        super();
        setOpaque(true); //going on memory, by default jlabels opaque is false, or transparent
    }
    protected void paintComponent(Graphics gr){
        super.paintComponent(gr);
    }
}