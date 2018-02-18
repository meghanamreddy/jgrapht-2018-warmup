package jgraph_v1;

import java.io.File;
import org.jgrapht.io.GraphImporter;
import org.jgrapht.io.DOTImporter;
import org.jgrapht.io.VertexProvider;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.ImportException;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.alg.NaiveLcaFinder;

public class warmup_gsoc_ancestor {
	
	/**
	 * Given a dot file, and two names, the function prints all the
	 * common ancestors of the two names.
	 * 
	 * @param ip_filename - the input DOT file which contains the graph
	 * @param first_name - the first element to find LCA for
	 * @param second_name - the second element to find LCA for
	 * @throws ImportException
	 */
	
	public void FindCommonAncestor(String ip_filename, String first_name, String second_name ) throws ImportException{
		File ip_file = new File(ip_filename);
		VertexProvider<String> vp = (a, b) -> a;
        EdgeProvider<String, DefaultEdge> ep = (f, t, l, a) -> new DefaultEdge();
        GraphImporter<String, DefaultEdge> importer = new DOTImporter<String, DefaultEdge>(vp, ep);
        
        DefaultDirectedGraph<String, DefaultEdge> graph =
            new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        importer.importGraph(graph, ip_file);
        
        NaiveLcaFinder<String, DefaultEdge> finder = new NaiveLcaFinder<>(graph);
        System.out.println(finder.findLca(first_name, second_name));
        System.out.println(finder.findLcas(first_name, second_name));
	}
	
	
	public static void main(String args[])
    {
		if (args.length < 3) {
			System.out.println("Requires three inputs: dotfile name_one name_two");
			System.exit(1);
		}
		
		warmup_gsoc_ancestor ancestor = new warmup_gsoc_ancestor();
		try  {
			ancestor.FindCommonAncestor(args[0], args[1], args[2]);
		}
		catch(ImportException e) {
			System.out.println("Input file not in expected format");
		}
	
    }
}
