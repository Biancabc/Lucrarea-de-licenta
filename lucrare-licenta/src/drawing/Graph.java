package drawing;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import gui.Panel;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import problem.Warehouse;


import javax.swing.*;
import java.awt.*;

public class Graph extends JFrame{

    private JGraphXAdapter jgxAdapter;
    private Dimension DEFAULT_SIZE = new Dimension();
    private static final long serialVersionUID = 1L;
    private  String type = Panel.qual;
    public Graph(){

        JFrame frame = new JFrame();
        frame.setTitle("Solution");
        switch (type) {
            case "small":
                 DEFAULT_SIZE = new Dimension(400, 500);
                break;
            case "medium":
                 DEFAULT_SIZE = new Dimension(400, 700);
                break;
            case "large":
                 DEFAULT_SIZE = new Dimension(400, 700);
                break;
        }
        frame.setSize(DEFAULT_SIZE);
        frame.validate();

        ListenableGraph g;
        g = new ListenableDirectedGraph(DefaultEdge .class);

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
        Warehouse pb = new Warehouse();
        pb.execute();

        int[] sup = pb.Supplier;

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

        mxHierarchicalLayout layout = new mxHierarchicalLayout(jgxAdapter);

        layout.setOrientation(SwingConstants.WEST);
        layout.setInterHierarchySpacing(20);

        layout.execute(jgxAdapter.getDefaultParent());

    }
}

