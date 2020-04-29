//package problem;
//
//import drawing.RelationshipEdge;
//import org.jgrapht.Graph;
//import org.jgrapht.alg.flow.mincost.CapacityScalingMinimumCostFlow;
//import org.jgrapht.alg.flow.mincost.MinimumCostFlowProblem;
//import org.jgrapht.graph.DefaultDirectedGraph;
//import org.jgrapht.graph.DefaultEdge;
//import org.jgrapht.graph.SimpleGraph;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
///**
// * A simple introduction to using JGraphT.
// *
// * @author Barak Naveh
// * @since Jul 27, 2003
// */
//public final class HelloJGraphT
//{
//    private HelloJGraphT()
//    {
//    } // ensure non-instantiability.
//
//    /**
//     * The starting point for the demo.
//     *
//     * @param args ignored.
//     */
//    public static void main(String[] args)
//    {
//        Graph<String, DefaultEdge> stringGraph = createStringGraph();
//
//        // note undirected edges are printed as: {<v1>,<v2>}
//        System.out.println(stringGraph.toString());
//
//        // create a graph based on URL objects
//        Graph<URL, DefaultEdge> hrefGraph = createHrefGraph();
//
//        // note directed edges are printed as: (<v1>,<v2>)
//        System.out.println(hrefGraph.toString());
//
//        DefaultDirectedGraph<String, DefaultEdge> dg = createDiGraph();
//        CapacityScalingMinimumCostFlow<String, DefaultEdge> dp = new CapacityScalingMinimumCostFlow<>();
//        MinimumCostFlowProblem<String, DefaultEdge> veMinimumCostFlowProblem  = CapacityScalingMinimumCostFlow<>() ;
//        dp.getMinimumCostFlow(veMinimumCostFlowProblem);
//        System.out.println(dp.getFlowMap());
//    }
//
//    /**
//     * Creates a toy directed graph based on URL objects that represents link structure.
//     *
//     * @return a graph based on URL objects.
//     */
//    private static Graph<URL, DefaultEdge> createHrefGraph()
//    {
//        Graph<URL, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);
//
//        try {
//            URL amazon = new URL("http://www.amazon.com");
//            URL yahoo = new URL("http://www.yahoo.com");
//            URL ebay = new URL("http://www.ebay.com");
//
//            // add the vertices
//            g.addVertex(amazon);
//            g.addVertex(yahoo);
//            g.addVertex(ebay);
//
//            // add edges to create linking structure
//            g.addEdge(yahoo, amazon);
//            g.addEdge(yahoo, ebay);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        return g;
//    }
//
//    /**
//     * Create a toy graph based on String objects.
//     *
//     * @return a graph based on String objects.
//     */
//    private static Graph<String, DefaultEdge> createStringGraph()
//    {
//        Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
//
//        String v1 = "v1";
//        String v2 = "v2";
//        String v3 = "v3";
//        String v4 = "v4";
//
//        // add the vertices
//        g.addVertex(v1);
//        g.addVertex(v2);
//        g.addVertex(v3);
//        g.addVertex(v4);
//
//        // add edges to create a circuit
//        g.addEdge(v1, v2);
//        g.addEdge(v2, v3);
//        g.addEdge(v3, v4);
//        g.addEdge(v4, v1);
//
//        return g;
//    }
//
//    private static CapacityScalingMinimumCostFlow<String, DefaultEdge> create()
//    {
//        CapacityScalingMinimumCostFlow<String, DefaultEdge> g = new CapacityScalingMinimumCostFlow<>();
//
//        String v1 = "v1";
//        String v2 = "v2";
//        String v3 = "v3";
//        String v4 = "v4";
//
//        Warehouse pb = new Warehouse();
//        pb.execute();
//
//        int[] sup = pb.Supplier;
//
//        for (int i = 0; i < pb.W; i++) {
//            String vi = "Warehouse".concat(String.valueOf(i + 1));
//            g.init(vi);
//            for (int j = 0; j < pb.S; j++) {
//                if (sup[j] == (i + 1)) {
//                    String vj = "Store".concat(String.valueOf(j + 1));
//                    g.init(vj);
//                    g.addEdge(vi, vj, new RelationshipEdge("Supplies"));
//                }
//            }
//        }
//        // add the vertices
//        g.init(v1);
//        g.init(v2);
//        g.init(v3);
//        g.init(v4);
//
//        // add edges to create a circuit
//        g.addEdge(v1, v2);
//        g.addEdge(v2, v3);
//        g.addEdge(v3, v4);
//        g.addEdge(v4, v1);
//        g.addEdge(v2, v4);
//
//
//        return g;
//    }
//}
//
