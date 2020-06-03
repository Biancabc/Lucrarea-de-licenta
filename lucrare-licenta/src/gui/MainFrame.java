package gui;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    static JLabel statusLabel;
    public static String selectedFilePath = " ";
    private int openTrue = 0;

    public MainFrame() {
        // set flow layout for the frame
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(new Dimension(700, 550));
        Dimension frameSize = this.getSize();

        // Customized colors
        Color color = new Color(235, 219, 196);
        Color color1 = new Color(232, 228, 178);
        Color color2 = new Color(163, 14, 20);

        // Button to open open FileDialog
        JButton openButton = new JButton("Deschideti");
        openButton.setBounds(30, 300, 110, 30);
        openButton.setFont(new Font(openButton.getName(), Font.BOLD, 14));
        openButton.setBackground(color);
        add(openButton);

        // Button to open a frame with the file's content
        JButton showButton = new JButton("Vizualizati");
        showButton.setBounds(30, 350, 110, 30);
        showButton.setFont(new Font(showButton.getName(), Font.BOLD, 14));
        showButton.setBackground(color);
        add(showButton);

        // Setting action listeners for buttons
        openButton.addActionListener(this);
        showButton.addActionListener(this);
        openButton.setActionCommand("open");
        showButton.setActionCommand("show");

        statusLabel = new JLabel("niciun fisier selectat");
        statusLabel.setBounds(150, 300, 300, 30);
        add(statusLabel);

        // Button to open the SecondFrame
        JButton continueBtn = new JButton("Continuati");
        continueBtn.setFont(new Font(continueBtn.getName(), Font.BOLD, 14));
        continueBtn.setBackground(color1);
        continueBtn.setBounds(140, 350, 120, 30);
        add(continueBtn);

        // Label for selected file
        JLabel selectedFileLabel = new JLabel("Selectati un fisier:");
        selectedFileLabel.setFont(new Font(selectedFileLabel.getName(), Font.BOLD, 20));
        selectedFileLabel.setForeground(color2);
        selectedFileLabel.setBounds(20, 250, 350, 50);
        add(selectedFileLabel);

        continueBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text1 = "";
                if (openTrue == 1) {
                    switch (JOptionPane.showConfirmDialog(null, "Ati ales fisierul " + selectedFilePath + ".\n Doriti sa continuati?", "Mesaj", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE)) {
                        case 0:
                            new SecondFrame();
                            break;
                        case 1:
                            break;
                    }
                } else {
                    text1 = "Nu ati selectat niciun fisier!";
                    JOptionPane.showMessageDialog(null, text1, "Mesaj", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        // Adding a becakground
        ImageIcon background = new ImageIcon("img\\bg.jpg");
        Image img = background.getImage();
        Image temp = img.getScaledInstance(frameSize.width, frameSize.height, Image.SCALE_SMOOTH);
        background = new ImageIcon(temp);
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setLayout(null);
        backgroundLabel.setBounds(0, 0, frameSize.width, frameSize.height);
        add(backgroundLabel);
        backgroundLabel.setLayout(new FlowLayout());
    }


    public void actionPerformed(ActionEvent evt) {

        String com = evt.getActionCommand();

        // If the user presses the open dialog show the open dialog
        if (com.equals("open")) {
            openTrue = 1;

            // Creating an object of JFileChooser class
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            // Invoke the showsOpenDialog function to show the save dialog
            int result = fileChooser.showOpenDialog(null);

            // If the user selects a file
            if (result == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                statusLabel.setText(selectedFilePath);
            } else
                statusLabel.setText("operatiune anulata");

        } else if (com.equals("show")) {
            new InputFrame();
        }
    }


    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new MainFrame();
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

