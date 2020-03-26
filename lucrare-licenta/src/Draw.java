import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import drawing.RelationshipEdge;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import problem.Warehouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Draw  extends JApplet{

    public final static int constant_GUI_X = 100, constant_GUI_Y = 150;
    public final static int constant_GUI_X_border = 1450, constant_GUI_Y_difference = 110, constant_GUI_X_2ndborder = 2150;
    public final static int button_width = 500;
    private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
    private static final Dimension DEFAULT_SIZE = new Dimension( 530, 320 );

    private JButton m_btnSolution = new JButton("Soluton");
    private JPanel m_panel;

    public Draw() {
        m_panel = new JPanel(new GridLayout(1, 0));
    }

    public Draw(GridLayout layout) {
        m_panel = new JPanel(layout);
    }

//    public JGraphXAdapter jgxAdapter;

    public static void main(String[] args) {


        Toolkit.getDefaultToolkit().setDynamicLayout(true);
        System.setProperty("sun.awt.noerasebackground", "true");
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setTitle("Warehouse");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new Draw(new GridLayout(0, 1))
                        .createGUI());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    @Override
    public void init() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                add(new Draw().createGUI());
            }
        });
    }

    private JPanel createGUI() {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            m_panel.add(m_btnSolution);
//            m_panel.add(m_btnNW);
//            m_panel.add(m_btnSW);
//            m_panel.add(m_btnFourRussians);
//            m_panel.add(m_btnNussinov);

            m_btnSolution.addActionListener(new AlgoButtonListener());
//            m_btnNW.addActionListener(new AlgoButtonListener());
//            m_btnSW.addActionListener(new AlgoButtonListener());
//            m_btnFourRussians.addActionListener(new AlgoButtonListener());
//            m_btnNussinov.addActionListener(new AlgoButtonListener());

            return m_panel;
        }

    protected class AlgoButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            JButton selButton = (JButton) event.getSource();


////
////         JPanel contentPane = (JPanel) frame.getContentPane();
//            JGraphAdapterDemo algo = new JGraphAdapterDemo();

            if (selButton == m_btnSolution) {
                ListenableGraph g = new ListenableDirectedGraph( DefaultEdge.class );

                // create a visualization using JGraph, via an adapter
                JGraphXAdapter<String, RelationshipEdge> jgxAdapter = new JGraphXAdapter<>(g);

                setPreferredSize(DEFAULT_SIZE);
                mxGraphComponent component = new mxGraphComponent(jgxAdapter);
                component.setConnectable(false);
                component.getGraph().setAllowDanglingEdges(false);
                getContentPane().add(component);
                resize(DEFAULT_SIZE);

                    int[]K={1,4,2,1,3,};
                    int P[][]={{20,24,11,25,30},
                            {28,27,82,83,74},
                            {74,97,71,96,70},
                            {2,55,73,69,61},
                            {46,96,59,83,4},
                            {42,22,29,67,59},
                            {1,5,73,59,56},
                            {10,73,13,43,96},
                            {93,35,63,85,46},
                            {47,65,55,71,95}};

                    Warehouse pb=new Warehouse(5,10,30,K,P);
                    pb.execute();

                    int [] wo = pb.getWo();

                    for (int i = 0; i < 5; i++) {
                        String vi = "Warehouse".concat(String.valueOf(i + 1));
                        g.addVertex(vi);
                        for (int j = 0; j < 10; j++) {
                            if (wo[j] == (i + 1)) {
                                String vj = "Store".concat(String.valueOf(j + 1));
                                g.addVertex(vj);
                                g.addEdge(vi, vj, new RelationshipEdge("Supplies"));

                            }
                        }
                    }

                    mxCircleLayout layout = new mxCircleLayout(jgxAdapter);

                    // center the circle
                    int radius = 100;
                    layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
                    layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
                    layout.setRadius(radius);
                    layout.setMoveCircle(true);

                    layout.execute(jgxAdapter.getDefaultParent());
                }
            }

        private void startNew(JFrame frame) {
            frame.setSize(new Dimension(700, 550));
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
}

//        JFrame frame = new JFrame("Facility Location Problem");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JLabel emptyLabel = new JLabel("");
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        emptyLabel.setPreferredSize(new Dimension(700, 500));
//        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
////        frame.setSize(400, 400);
//        frame.setLocation(0, 0);
//
//
//
//        JPanel panel = new JPanel();
//        JPanel panel2 = new JPanel();
////        panel.setLayout(null);
//
//        JLabel text = new JLabel();
//        text.setText("Warehouse Location Problem");
//        text.setFont(new Font("Arial", Font.PLAIN, 40));
//        text.setBounds(constant_GUI_X_border, constant_GUI_Y_difference*4, 500, 150);
//        panel.add(text);
//        frame.pack();
//        frame.setVisible(true);
//        frame.add(panel);

//        JButton button_random = new JButton("Random Solution");
//        button_random.setBounds(constant_GUI_X_border, constant_GUI_Y_difference*2, button_width, 100);
//        button_random.setFont(new Font("Arial", Font.PLAIN, 40));
//        button_random.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JGraphXAdapter jgxAdapter;
//                ListenableGraph g = new ListenableDirectedGraph( DefaultEdge.class );
//                jgxAdapter = new JGraphXAdapter<>(g);
//
//                // create a visualization using JGraph, via an adapter
////                JGraphXAdapter<String, RelationshipEdge> jgxAdapter = new JGraphXAdapter<>(g);
////
////                setPreferredSize(DEFAULT_SIZE);
//
//                mxGraphComponent component = new mxGraphComponent(jgxAdapter);
//                component.setConnectable(false);
//                component.getGraph().setAllowDanglingEdges(false);
//                panel2.add(component);
//
////                resize(DEFAULT_SIZE);
//                int [] wo = new int[10];
//                wo[0]= 3;
//                wo[1] = 4;
//                wo[2] = 3;
//                wo[3] = 2;
//                wo[4] =2;
//                wo[5] =2;
//                wo[6] =2;
//                wo[7] =5;
//                wo[8] =5;
//                wo[9] = 1;
//
//                for (int i = 0; i < 5; i++) {
//                    String vi = "Warehouse".concat(String.valueOf(i + 1));
//                    g.addVertex(vi);
//                    for (int j = 0; j < 10; j++) {
//                        if (wo[j] == (i + 1)) {
//                            String vj = "Store".concat(String.valueOf(j + 1));
//                            g.addVertex(vj);
//                            g.addEdge(vi, vj, new RelationshipEdge("Supplies"));
//
//                        }
//                    }
//                }
//
//                mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
//
//                // center the circle
//                int radius = 100;
//                layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
//                layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
//                layout.setRadius(radius);
//                layout.setMoveCircle(true);
//
//                layout.execute(jgxAdapter.getDefaultParent());
//
//            }
//        });
//        panel.add(button_random);

//        JGraphAdapterDemo applet = new JGraphAdapterDemo();
//        applet.init();

//        JLabel text_amount_warehouse = new JLabel();
//        text_amount_warehouse.setText("No. Warehouses: NA");
//        text_amount_warehouse.setFont(new Font("Arial", Font.PLAIN, 40));
//        text_amount_warehouse.setBounds(constant_GUI_X_2ndborder, 50+constant_GUI_Y_difference*13, 500, 100);
//        panel.add(text_amount_warehouse);
//
//        JLabel text_total_costs = new JLabel();
//        text_total_costs.setText("Total Costs/Period: NA");
//        text_total_costs.setFont(new Font("Arial", Font.PLAIN, 40));
//        text_total_costs.setBounds(constant_GUI_X_border, 50+constant_GUI_Y_difference*13, 600, 100);
//        panel.add(text_total_costs);
//
//        JLabel text_total_demand = new JLabel();
//        text_total_demand.setText("Total demand: NA");
//        text_total_demand.setFont(new Font("Arial", Font.PLAIN, 40));
//        text_total_demand.setBounds(constant_GUI_X_2ndborder, 50+constant_GUI_Y_difference*12, 700, 100);
//        panel.add(text_total_demand);
//
//        JLabel text_transportation_costs = new JLabel();
//        text_transportation_costs.setText("Transportation Costs: NA");
//        text_transportation_costs.setFont(new Font("Arial", Font.PLAIN, 40));
//        text_transportation_costs.setBounds(constant_GUI_X_border, 50+constant_GUI_Y_difference*12, 700, 100);
//        panel.add(text_transportation_costs);
//
//        JLabel text_choose_amount = new JLabel();
//        text_choose_amount.setText("Choose Warehouses:");
//        text_choose_amount.setFont(new Font("Arial", Font.PLAIN, 40));
//        text_choose_amount.setBounds(constant_GUI_X_border, constant_GUI_Y_difference*1, 500, 100);
//        panel.add(text_choose_amount);


//        frame.add(panel2);
//        int [] K = {1, 4, 2, 1, 3,};
//        int P[][]= {{20, 24, 11, 25, 30},
//                {28, 27, 82, 83, 74},
//                {74, 97, 71, 96, 70},
//                {2, 55, 73, 69, 61},
//                {46, 96, 59, 83, 4},
//                {42, 22, 29, 67, 59},
//                {1, 5, 73, 59, 56},
//                {10, 73, 13, 43, 96},
//                {93, 35, 63, 85, 46},
//                {47, 65, 55, 71, 95}};
//
//        Warehouse pb = new Warehouse(5, 10, 30, K, P);
//        pb.execute(args);
//
//        int [] Wo = pb.getWo();
//
//        for (int i =0; i< 10 ; i++)
//        System.out.println(Wo[i]);


//}
//}
