import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;


public class MinSpanningTree {
	private LinkedList<StreetI> streets;
	private LinkedList<Point> points;
	private PriorityQueue<StreetI> pq;
	private LinkedList<LinkedList<Point>> id;
	
	
	public MinSpanningTree(GraphMaker g){
		streets = g.getStreets();
		points = g.getPoints();
		Comparator<StreetI> c = new streetComparator();
		pq = new PriorityQueue<StreetI>(streets.size(), c);
		Iterator<StreetI> it = streets.iterator();
		while(it.hasNext()){
			pq.add(it.next());
		}
		
		id = new LinkedList<LinkedList<Point>>();
		Iterator<Point> it2 = points.iterator();
		int index = 0;
		while(it2.hasNext()){
			LinkedList<Point> temp = new LinkedList<Point>();
			temp.add(it2.next());
			id.add(index, temp);
			index++;
		}
	}
	
	//Using code from Sedgewick
	private int find(Point p){
		Iterator<LinkedList<Point>> it = id.iterator();
		while(it.hasNext()){
			LinkedList<Point> temp = it.next();
			Iterator<Point> it2 = temp.iterator();
			while(it2.hasNext()){
				Point t = it2.next();
				if(t.getx() == p.getx() && t.gety() == p.gety()){
					return id.indexOf(temp); 
				}
			}
		}
		System.out.println("Cannot find point");
		return -1;
	}
	
	private void union(Point p, Point q){
		int pID = find(p);
		int qID = find(q);
		if(pID == qID) return;
		
		LinkedList<Point> temp = id.remove(pID);
		temp.add(q);
		id.addLast(null);
		id.add(pID, temp);
		
		LinkedList<Point> temp2 = id.remove(qID);
		LinkedList<Point> temp3 = new LinkedList<Point>();
		
		Iterator<Point> it = temp2.iterator();
		while(it.hasNext()){
			Point tempp = it.next();
			if(!(tempp.getx() == q.getx() && tempp.gety() == q.gety())){
				temp3.add(tempp);
			}
		}
		id.add(pID, temp3);
	}
	
	public Set<StreetI> getMST(){
		Set<StreetI> ret = new HashSet<StreetI>();
		
		while(!pq.isEmpty() && ret.size() < points.size() - 1){
			StreetI s = pq.poll();
			if(find(s.getFirstPoint()) != find(s.getSecondPoint())){
				ret.add(s);
				union(s.getFirstPoint(), s.getSecondPoint());
			}
			
		}
			
		return ret;
	}
	//End code from Sedgewick
}
