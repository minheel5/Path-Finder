import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class Intersection implements IntersectionI {
	private Point pt;
	private List<StreetI> streets;
	

	@Override
	public Point getLocation() {
		return this.pt;
	}

	@Override
	public List<StreetI> getStreetList() {
		return this.streets;
	}

	@Override
	public void setPointOfIntersection(Point p) {
		pt = new Point();
		pt.setx(p.getx());
		pt.sety(p.gety());
		
	}

	@Override
	public void setStreetList(List<StreetI> list) {
		streets = new LinkedList<StreetI>();
		ListIterator<StreetI> it = list.listIterator();
		while(it.hasNext()){
			streets.add(it.next());
		}

	}

	@Override
	public StreetI isConnected(IntersectionI intersection) {
		List<StreetI> streets2 = intersection.getStreetList();
		ListIterator<StreetI> it = streets2.listIterator();
		boolean isConnected = false;
		StreetI ret = null;
		
		while (it.hasNext()){
			ret = it.next();
			if(streets.contains(ret)){
				isConnected = true;
				break;
			}
		}
		
		if(isConnected){
			return ret;
		}
		else{
			return null;
		}
	}

}
