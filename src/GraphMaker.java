import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;



public class GraphMaker {
	LinkedList<Point> points;
	LinkedList<StreetI> streets;
	LinkedList<IntersectionI> intersections;
	
	public GraphMaker(String filename){
		points =  new LinkedList<Point>();
		streets = new LinkedList<StreetI>();
		intersections = new LinkedList<IntersectionI>();
		
		BufferedReader br;
		String line;
		try {
			br = new BufferedReader(new FileReader(filename));
			line = br.readLine();
			line = br.readLine();
			while(line != null){
				char[] charArr = line.toCharArray();
				int i = 0;
				int id = 0;
				Point p1 = new Point();
				Point p2 = new Point();
				String streetName = "";
				int count = 0;
				while(i < charArr.length){
					String temp = "";
					while(i < charArr.length && charArr[i] != ','){
						temp = temp + charArr[i];
						i++;
					}
					i++;
					switch(count){
					case 0: id = Integer.parseInt(temp); break;
					case 1: p1.setx(Double.parseDouble(temp)); break;
					case 2: p1.sety(Double.parseDouble(temp)); break;
					case 3: p2.setx(Double.parseDouble(temp)); break;
					case 4: p2.sety(Double.parseDouble(temp)); break;
					case 5: streetName = temp;
					}
					count++;
					
				}
				
				Iterator<Point> it = points.iterator();
				boolean contains = false;
				while(it.hasNext()){
					if(it.next().equals(p1)){
						contains = true;
					}
				}
				if(!contains){
					points.add(p1);
				}
				it = points.iterator();
				contains = false;
				while(it.hasNext()){
					if(it.next().equals(p2)){
						contains = true;
					}
				}
				if(!contains){
					points.add(p2);
				}
				
				
				Street str = new Street();
				str.setIdNumber(id);
				str.setName(streetName);
				str.setPoints(p1, p2);
				streets.add(str);
				line = br.readLine();
			}
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Creates Intersection list
		ListIterator<Point> it = points.listIterator();
		LinkedList<Point> bfsQueue = new LinkedList<Point>();
		
		//Breath-first search on G(V,E) = G(Point, Street) 
		
		it.next().mark(true);
		it.previous();
		while(it.hasNext()){
			Point pt = it.next();
			if(!pt.isMarked()){
				bfsQueue.add(pt);
				while(!bfsQueue.isEmpty()){
					Point pt2 = bfsQueue.poll();
					ListIterator<StreetI> it2 = streets.listIterator();
					IntersectionI i = new Intersection();
					i.setPointOfIntersection(pt2);
					LinkedList<StreetI> istreet = new LinkedList<StreetI>(); 
					while(it2.hasNext()){
						StreetI str = it2.next();
						if(str.getFirstPoint().equals(pt2)){ 
							if(!str.getSecondPoint().isMarked()){
								bfsQueue.add(str.getSecondPoint());
							}
							pt2.mark(true);
							istreet.add(str);
						}
						
						else if(str.getSecondPoint().equals(pt2)){
							if(!str.getFirstPoint().isMarked()){
								bfsQueue.add(str.getFirstPoint());
							}
							pt2.mark(true);
							istreet.add(str);
						}
					}	
					i.setStreetList(istreet);
					Iterator<IntersectionI> it3 = intersections.iterator();
					boolean contains = false;
					while(it3.hasNext()){
						if(it3.next().getLocation().equals(i.getLocation())){
							contains = true;
							break;
						}
					}
					if(!contains){
						intersections.add(i);
					}
					
				}
			}
		}
	}
	
	
	public void outputAdjList(){
		ListIterator<IntersectionI> it = intersections.listIterator();
		File file = new File("adjacencyList.txt");
		 
		try{
			if (!file.exists()) {
				file.createNewFile();
			}
	
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			while(it.hasNext()){
				IntersectionI i = it.next();
				Point pt = i.getLocation();
				String temp = "";
				temp = "(" + pt.getx() + "," + pt.gety() + ") Adjacent to:";
				bw.write(temp);
				java.util.List<StreetI> strs = i.getStreetList();
				ListIterator<StreetI> it2 = strs.listIterator();
				boolean lastElement = false;
				while(it2.hasNext()){
					StreetI st = it2.next();
					if(!it2.hasNext()){
						lastElement = true;
					}
					Point pt1 = st.getFirstPoint();
					if(pt1.equals(pt)){
						pt1 = st.getSecondPoint();
					}
					if(lastElement){
						temp = "";
						temp = "(" + pt1.getx() + "," + pt1.gety() + "," + st.getName() + ","
						+ st.getDistance() + "," + st.getIdNumber() + ")";
						bw.write(temp);
					}
					else{
						temp = "";
						temp = "(" + pt1.getx() + "," + pt1.gety() + "," + st.getName() + ","
						+ st.getDistance() + "," + st.getIdNumber() + ")" + ",";
						bw.write(temp);
					}
				}
				bw.newLine();
				
				
			}
			bw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public LinkedList<Point> getPoints(){
		return points;
	}
	public LinkedList<StreetI> getStreets(){
		return streets;
	}
	public LinkedList<IntersectionI> getIntersections(){
		return intersections;
	}
	
}
