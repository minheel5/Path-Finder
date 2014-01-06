import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;


//Using modified code from Sedgewick

public class shortestPath {
	private LinkedList<IntersectionI> intersections;
	private PriorityQueue<IntersectionI> pq;
	private IntersectionI[] index;
	private double[] distTo;
	private LinkedList<List<IntersectionI>> edgeTo;

	public shortestPath(GraphMaker g){
		this.intersections = g.getIntersections();
	    
	    Iterator<IntersectionI> it = intersections.iterator();
	    index = new Intersection[intersections.size()];
	    distTo = new double[intersections.size()];
	    edgeTo = new LinkedList<List<IntersectionI>>();
	    for(int i = 0; i < index.length; i++){
	    	index[i] = it.next();
	    	distTo[i] = Double.MAX_VALUE;
	    	edgeTo.add(i, null);
	    }
	    Comparator<IntersectionI> c = new intersectionComparator(distTo, index);
	    pq = new PriorityQueue<IntersectionI> (intersections.size(), c);
	}
	
	private int findIndex(IntersectionI x){
		for (int i = 0; i < index.length; i++){
			if(index[i].getLocation().equals(x.getLocation())){
				return i;
			}
		}
		return -1;
	}
	
	private IntersectionI findIntersection (Point p){
		Iterator<IntersectionI> it = intersections.iterator();
		while(it.hasNext()){
			IntersectionI ret = it.next();
			if(ret.getLocation().equals(p)){
				return ret;
			}
		}
		System.out.println("Cannot find i");
		return null;
	}
	
	private void relax(StreetI s, IntersectionI i){
		Point start = s.getSecondPoint();
		Point end = s.getFirstPoint();
		if (s.getFirstPoint().equals(i.getLocation())){
			start = s.getFirstPoint();
			end = s.getSecondPoint();
		}
		
		IntersectionI startI = new Intersection();
		startI = findIntersection(start);
		IntersectionI endI = new Intersection();
		endI = findIntersection(end);
		
	    if (distTo[findIndex(endI)] > distTo[findIndex(startI)] + s.getDistance()){
	    	distTo[findIndex(endI)] =  distTo[findIndex(startI)] + s.getDistance();
		    List<IntersectionI> edges = edgeTo.get(findIndex(endI));
		    if(edges == null){
		    	edges = new LinkedList<IntersectionI>();
		    	edges.add(startI);
		    }
		    edges.add(endI);
		    edgeTo.add(findIndex(endI), edges);
		  
		    if (!pq.contains(endI)){
		    	pq.add(endI);
		    }
	    }
	}
	
	public List<IntersectionI>  dijkstraPath(IntersectionI start, IntersectionI end){
		IntersectionI small = start;
		distTo[findIndex(start)] = 0.0;
	    pq.add(start);
	    while (!pq.isEmpty())
	    {

	    	small = pq.poll();

	    	//Relax all the streets in the adjacency list.
	    	List<StreetI> adj = small.getStreetList();
	    	Iterator<StreetI> it = adj.iterator();
	    	while(it.hasNext()){
	    		StreetI s = it.next();
	    		relax(s, small);
	    	}
	    }
	    
		return edgeTo.get(findIndex(end));
		
	}
}
//End code from Sedgewick

