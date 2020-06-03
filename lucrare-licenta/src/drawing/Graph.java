package drawing;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import problem.Warehouse;

import javax.swing.*;
import java.awt.*;

public class Graph extends JFrame{

    private JGraphXAdapter jgxAdapter;
    private static final long serialVersionUID = 1L;
    public Graph(Warehouse warehouse){

        JFrame frame = new JFrame();
        frame.setTitle("Solutie");
        Dimension DEFAULT_SIZE = new Dimension(400, 700);
        frame.setSize(DEFAULT_SIZE);
        frame.validate();

        ListenableDirectedGraph resultedGraph = new ListenableDirectedGraph(DefaultEdge .class);
        // create a visualization using JGraph, via an adapter
        JGraphXAdapter<String, LabeledEdge> jgxAdapter = new JGraphXAdapter<>(resultedGraph);
        setPreferredSize(DEFAULT_SIZE);
        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        getContentPane().add(component);

        frame.getContentPane().add(component, BorderLayout.CENTER);
        frame.setVisible(true);

        for (int i = 0; i < warehouse.W; i++) {
            String vi = "Depozitul".concat(String.valueOf(i + 1));
            resultedGraph.addVertex(vi);
            for (int j = 0; j < warehouse.S; j++) {
                if (warehouse.Supplier[j] == (i + 1)) {
                    String vj = "Magazinul".concat(String.valueOf(j + 1));
                    resultedGraph.addVertex(vj);
                    resultedGraph.addEdge(vi, vj, new LabeledEdge("Aprovizioneaza"));
                }
            }
        }

        mxHierarchicalLayout layout = new mxHierarchicalLayout(jgxAdapter);
        layout.setOrientation(SwingConstants.WEST);
        layout.setInterHierarchySpacing(20);
        layout.execute(jgxAdapter.getDefaultParent());
    }
}

