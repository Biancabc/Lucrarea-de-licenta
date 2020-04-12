package problem;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import drawing.RelationshipEdge;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JFrame implements ActionListener {
    private JGraphXAdapter jgxAdapter;
    private static final Dimension DEFAULT_SIZE = new Dimension( 400, 500 );
    private static final long serialVersionUID = 1L;

    public Panel() {

        // set flow layout for the frame
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(new Dimension(700, 550));
        Dimension frameSize = this.getSize();

        ImageIcon icon = new ImageIcon("D:\\llicenta\\lucrare-licenta\\img\\btn.png");
        JButton button = new JButton(icon);
        button.setBounds(250,300,100,40);
        add(button);
        button.setLayout(new FlowLayout());
        //set action listeners for buttons
        button.addActionListener(this);
        button.setSize(100,50);

        // define a custom short action command for the button
        button.setActionCommand("Solution");


        JButton button1 = new JButton("Display");
        button1.setBounds(50,300,100,40);
        add(button1);
        button1.setLayout(new FlowLayout());

        //set action listeners for buttons
        button1.addActionListener(this);
        button1.setSize(100,50);

        // define a custom short action command for the button
        button1.setActionCommand("Display");

        ImageIcon background=new ImageIcon("D:\\llicenta\\lucrare-licenta\\img\\bg.jpg");
        Image img=background.getImage();
        Image temp=img.getScaledInstance(frameSize.width,frameSize.height,Image.SCALE_SMOOTH);
        background =new ImageIcon(temp);
        JLabel back=new JLabel(background);
        back.setLayout(null);
        back.setBounds(0,0,frameSize.width,frameSize.height);
        add(back);
        back.setLayout(new FlowLayout());

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if (action.equals("Solution")) {
            JFrame frame =new JFrame();
            frame.setTitle("Solution");
            frame.setSize(DEFAULT_SIZE);
            frame.validate();

            ListenableGraph g;
            g = new ListenableDirectedGraph( DefaultEdge.class );
            jgxAdapter = new JGraphXAdapter<>(g);

            // create a visualization using JGraph, via an adapter
            JGraphXAdapter<String, RelationshipEdge> jgxAdapter = new JGraphXAdapter<>(g);

            setPreferredSize(DEFAULT_SIZE);
            mxGraphComponent component = new mxGraphComponent(jgxAdapter);
            component.setConnectable(false);
            component.getGraph().setAllowDanglingEdges(false);
            getContentPane().add(component);
            frame.getContentPane().add(component, BorderLayout.CENTER);
            frame.setVisible(true);
            Warehouse pb=new Warehouse();
            pb.execute();

//            int [] K ={ 1, 4, 2, 1, 3, 3, 1};
//                    // P
//            int P[][]={{ 20, 24, 11, 25, 30, 15, 23},
//                    {28, 27, 82, 83, 74, 24, 11},
//                    {74, 97, 71, 96, 70, 82, 27},
//                    {2, 55, 73, 69, 61, 10, 96},
//                    {46, 96, 59, 83, 4, 36, 58},
//                    {42, 22, 29, 67, 59, 64, 23},
//                    {1, 5, 73, 59, 56, 48, 13},
//                    {10, 73, 13, 43, 96, 1, 82},
//                    {93, 35, 63, 85, 46, 99, 17},
//                    {47, 65, 55, 71, 95, 25, 35},
//                    {67, 59, 42, 22, 2, 46, 96},
//                    {56, 1, 5, 73, 5, 42, 22},
//                    {43, 96, 10, 73, 1, 1, 5},
//                    {85, 46, 93, 35, 6, 10, 73}};
//
//
//            Warehouse pb=new Warehouse(7,14,30, K, P);
//            pb.execute();

            int [] sup = pb.Supplier;

            for (int i = 0; i < pb.W; i++) {
                String vi = "Warehouse".concat(String.valueOf(i + 1));
                g.addVertex(vi);
                for (int j = 0; j < pb.S; j++) {
                    if (sup[j] == (i + 1)) {
                        String vj = "Store".concat(String.valueOf(j + 1));
                        g.addVertex(vj);
                        g.addEdge(vi, vj, new RelationshipEdge("Supplies"));

                    }
                }
            }

            mxHierarchicalLayout layout = new  mxHierarchicalLayout(jgxAdapter);

            layout.setOrientation(SwingConstants.WEST);
            layout.setInterHierarchySpacing(20);

            layout.execute(jgxAdapter.getDefaultParent());

        }
        if(action.equals("Display")){
            JFrame frame = new AnotherFrame();

            frame.validate();
            frame.setVisible(true);
        }

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

        //Schedule a job for the event-dispatching thread:

        //creating and showing this application's GUI.

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                createAndShowGUI();

            }

        });
    }

}

