import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;


public class JavaScriptPointsWriter {
	
	public JavaScriptPointsWriter(){
		
	}
	
	public double[] problem3 (String filename){
		GraphMaker g = new GraphMaker(filename);
		shortestPath s = new shortestPath(g);
		List<IntersectionI> list = 
				s.dijkstraPath(g.getIntersections().getFirst(), g.getIntersections().getLast());
		double[] result = new double[list.size() * 2];
		ListIterator<IntersectionI> it = list.listIterator();
		for(int i = 0; i < result.length && it.hasNext(); i = i+2){
				IntersectionI x = it.next();
				result[i] = x.getLocation().getx();
				result[i+1] = x.getLocation().gety();
			
		}
		return result;
	}
	
	public double[] problem4 (String filename){
		MinSpanningTree s = new MinSpanningTree(new GraphMaker(filename));
		Set<StreetI> list = s.getMST();
		double[] result = new double[list.size() * 4];
		Iterator<StreetI> it = list.iterator();
		 
		for(int i = 0; i < result.length && it.hasNext(); i = i+4){
				StreetI x = it.next();
				result[i] = x.getFirstPoint().getx();
				result[i+1] = x.getFirstPoint().gety(); 
				result[i+2] = x.getSecondPoint().getx();
				result[i+3] = x.getSecondPoint().gety();
		}
		return result;
	}
	
	public double[] problem5 (String filename){
		GraphMaker g = new GraphMaker(filename);
		BreadthFirstSearch b = new BreadthFirstSearch(g);
		HashMap<IntersectionI, List<IntersectionI>> map = 
				b.bfsTraverse(g.getIntersections().getFirst());
		Collection<List<IntersectionI>> list = map.values();
		Iterator<List<IntersectionI>> it = list.iterator();
		LinkedList<IntersectionI> bfs = new LinkedList<IntersectionI>();
		while(it.hasNext()){
			List<IntersectionI> temp = it.next();
			bfs.addAll(temp);
		}
		
		double[] result = new double[bfs.size() * 2];
		Iterator<IntersectionI> it1 = bfs.iterator();
		 
		for(int i = 0; i < result.length && it1.hasNext(); i = i + 2){
				IntersectionI x = it1.next();
				result[i] = x.getLocation().getx();
				result[i+1] = x.getLocation().gety(); 
		}
		return result;
	}
	
	public void print(String filename, int problem){
		File file = new File("points1.js");
		double[] result = problem3(filename);
		String arrayName = arrayName = "points1_" + filename;
		switch(problem){
		case 3:
			break;
		case 4:
			result = problem4(filename);
			file = new File("points2.js");
			arrayName = "points2_" + filename;
			break;
		case 5:
			result = problem5(filename);
			file = new File("points3.js");
			arrayName = "points3_" + filename;
			break;
		}
		
		try{
			if (!file.exists()) {
				file.createNewFile();
			}
	
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("var " + arrayName + " = new Array();");
			bw.newLine();
			for(int i = 0; i < result.length; i++)
			{
				bw.write(arrayName + "["+ i + "] =" + result[i] + ";");
				bw.newLine();
			}
			bw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
