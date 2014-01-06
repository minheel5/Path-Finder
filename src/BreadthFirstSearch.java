import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

//Modified code from Sedgewick
public class BreadthFirstSearch {
	private boolean[] marked;
	private LinkedList<IntersectionI> intersections;
	private IntersectionI[] index;
	
	
	public BreadthFirstSearch(GraphMaker g){
		intersections = g.getIntersections();
		Iterator<IntersectionI> it = intersections.iterator();
		index = new IntersectionI[intersections.size()];
		marked = new boolean[intersections.size()];
		int i = 0;
		while(it.hasNext()){
			index[i] = it.next();
			marked[i] = false;
			i++;
		}
	}
	
	private int findIndex(IntersectionI x){
		for (int i = 0; i < index.length; i++){
			if(index[i].getLocation().equals(x.getLocation())){
				return i;
			}
		}
		System.out.println("cannot find intersection");
		return -1;
	}
	
	public IntersectionI getIntersection(Point p){
		Iterator<IntersectionI> it = intersections.iterator();
		while(it.hasNext()){
			IntersectionI i = it.next();
			if(i.getLocation().equals(p)){
				return i;
			}
		}
		return null;
	}
	
	
	
	public HashMap<IntersectionI, List<IntersectionI>> bfsTraverse(IntersectionI source){
		HashMap<IntersectionI, List<IntersectionI>> ret = 
				new HashMap<IntersectionI, List<IntersectionI>>();
		
		LinkedList<IntersectionI> q = new LinkedList<IntersectionI>();
		q.add(source);
		
		while(!q.isEmpty()){
			IntersectionI i = q.poll();
			marked[findIndex(i)] = true;
			List<IntersectionI> list = new LinkedList<IntersectionI>();
			List<StreetI> strs = i.getStreetList();
			Iterator<StreetI> it = strs.iterator();
			while(it.hasNext()){
				StreetI s = it.next();
				IntersectionI x = getIntersection(s.getFirstPoint());
				if(x.getLocation().equals(i.getLocation())){
					x = getIntersection(s.getSecondPoint());
				}
				list.add(x);
				if(!marked[findIndex(x)]){
					q.add(x);
					marked[findIndex(x)] = true;
				}
			}
			ret.put(i, list);
		}
		return ret;
	}	
	
}
