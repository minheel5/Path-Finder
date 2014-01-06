import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class MinTurns {
	
	private BreadthFirstSearch b;
	
	public MinTurns(String filename){
		GraphMaker g = new GraphMaker(filename);
		b = new BreadthFirstSearch(g);
	}
	
	
	public int minTurnsBetween(IntersectionI source, IntersectionI destination){
		HashMap<IntersectionI, List<IntersectionI>> bfs = b.bfsTraverse(source);
		LinkedList<IntersectionI> levelQueue = new LinkedList<IntersectionI>();
		int index = 0;
		if(source.getLocation().equals(destination.getLocation())){
			return index;
		}
		levelQueue.add(source);
		while(!levelQueue.isEmpty()){
			List<IntersectionI> list = bfs.get(levelQueue.poll());
			levelQueue.addAll(list);
			index++;
			while(!levelQueue.isEmpty()){
				if(levelQueue.poll().getLocation().equals(destination.getLocation())){
					return index;
				}
			}
		}
		
		return -1;
	}
}
