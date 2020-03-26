import com.mxgraph.layout.mxCircleLayout;
import drawing.RelationshipEdge;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import problem.Warehouse;

import javax.swing.*;
import java.awt.*;


public class JGraphAdapterDemo extends JApplet {
    private static final Dimension DEFAULT_SIZE = new Dimension( 530, 320 );

    //
    private JGraphXAdapter jgxAdapter;
    public int [] wo;
    /**
     * @see java.applet.Applet#init().
     */
    public void init(  ) {
        // create a JGraphT graph
        ListenableGraph g = new ListenableDirectedGraph( DefaultEdge.class );

        jgxAdapter = new JGraphXAdapter<>(g);

        // create a visualization using JGraph, via an adapter
//        JGraphXAdapter<String, RelationshipEdge> jgxAdapter = new JGraphXAdapter<>(g);

//        setPreferredSize(DEFAULT_SIZE);
//        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
//        component.setConnectable(false);
//        component.getGraph().setAllowDanglingEdges(false);
//        getContentPane().add(component);
//        resize(DEFAULT_SIZE);

        wo = setWo();

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

    public int[] setWo(){
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

        int [] Wo = pb.getWo();
        return Wo;
    }

    public String getAlgorithmName() {
        return "Print Solution";
    }
//
//    private void adjustDisplaySettings( JGraph jg ) {
//        jg.setPreferredSize( DEFAULT_SIZE );
//
//        Color  c        = DEFAULT_BG_COLOR;
//        String colorStr = null;
//
//        try {
//            colorStr = getParameter( "bgcolor" );
//        }
//        catch( Exception e ) {}
//
//        if( colorStr != null ) {
//            c = Color.decode( colorStr );
//        }
//
//        jg.setBackground( c );
//    }

//
//    private void positionVertexAt( Object vertex, int x, int y ) {
//        DefaultGraphCell cell = jgxAdapter.getVertexCell( vertex );
//        Map attr = cell.getAttributes(  );
//        Rectangle2D b = GraphConstants.getBounds(attr);
//
//        GraphConstants.setBounds( attr, new Rectangle( x, y, b.OUT_BOTTOM, b.OUT_TOP ) );
//
//        Map cellAttr = new HashMap(  );
//        cellAttr.put( cell, attr );
//        m_jgAdapter.edit( cellAttr, null, null, null);
//    }
}