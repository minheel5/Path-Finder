import org.junit.Test;


public class MapMaker {
	
	@Test
	public void graph(){
		GraphMaker g = new GraphMaker("roadSet1Formatted.txt");
		g.outputAdjList();
	}
	@Test
	public void dijkstra(){
		GraphMaker g = new GraphMaker("roadSet1Formatted.txt");
		shortestPath p = new shortestPath(g);
		p.dijkstraPath(g.getIntersections().getFirst(), g.getIntersections().getLast());
	}
	
	@Test
	public void mst(){
		GraphMaker g = new GraphMaker("roadSet1Formatted.txt");
		MinSpanningTree m = new MinSpanningTree(g);
		m.getMST();
	}
	
	@Test
	public void bfs(){
		GraphMaker g = new GraphMaker("roadSet1Formatted.txt");
		BreadthFirstSearch b = new BreadthFirstSearch(g);
		b.bfsTraverse(g.getIntersections().getFirst());
	}
	
	@Test
	public void minturs(){
		GraphMaker g = new GraphMaker("roadSet1Formatted.txt");
		MinTurns b = new MinTurns("roadSet1Formatted.txt");
		b.minTurnsBetween(g.getIntersections().getFirst(),
				g.getIntersections().getLast()));
	}
	
	
	@Test
	public void writeJS(){
		JavaScriptPointsWriter j = new JavaScriptPointsWriter();
		j.print("roadSet1Formatted.txt",3);
		j.print("roadSet1Formatted.txt",4);
		j.print("roadSet1Formatted.txt",5);
	}
}
