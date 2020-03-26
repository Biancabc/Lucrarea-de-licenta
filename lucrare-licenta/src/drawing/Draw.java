package drawing;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.demo.JGraphXAdapterDemo;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultListenableGraph;

import javax.swing.*;
import java.awt.*;

/**
 * A demo applet that shows how to use JGraphX to visualize JGraphT graphs. Applet based on
 * JGraphAdapterDemo.
 *
 */
public class Draw extends JApplet
{
    private static final long serialVersionUID = 8127374778187708896L;

    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

    private JGraphXAdapter<String, RelationshipEdge> jgxAdapter;

    public String label;

    /**
     * An alternative starting point for this demo, to also allow running this applet as an
     * application.
     *
     * @param args command line arguments
     */

    public static void  main(String[] args) {

    JGraphXAdapterDemo applet = new JGraphXAdapterDemo();
    applet.init();

    JFrame frame = new JFrame();
    frame.getContentPane().add(applet);
    frame.setTitle("Ceva");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);

}
    @Override
    public void init()
    {
        // create a JGraphT graph
        DefaultListenableGraph<String, RelationshipEdge> g =
                new DefaultListenableGraph<>(new DefaultDirectedGraph<>(RelationshipEdge.class));

        jgxAdapter = new JGraphXAdapter<>(g);

        // create a visualization using JGraph, via an adapter
        JGraphXAdapter<String, RelationshipEdge> jgxAdapter = new JGraphXAdapter<>(g);
        setPreferredSize(DEFAULT_SIZE);
        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        getContentPane().add(component);
        resize(DEFAULT_SIZE);

        int [] wo = new int[10];
        wo[0]= 3;
        wo[1] = 4;
        wo[2] = 3;
        wo[3] = 2;
        wo[4] =2;
        wo[5] =2;
        wo[6] =2;
        wo[7] =5;
        wo[8] =5;
        wo[9] = 1;

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
        // positioning via jgraphx layouts
        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);

        // center the circle
        int radius = 100;
        layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
        layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
        layout.setRadius(radius);
        layout.setMoveCircle(true);

        layout.execute(jgxAdapter.getDefaultParent());
        // that's all there is to it!...
    }


}
