import java.util.Comparator;


public class intersectionComparator implements Comparator<IntersectionI> {
	
	private double[] distTo;
	private IntersectionI[] index;
	
	public intersectionComparator(double[] distTo, IntersectionI[] index){
		this.distTo = distTo;
		this.index = index;
	}
	
	private int findIndex(IntersectionI x){
		for (int i = 0; i < index.length; i++){
			if(index[i].getLocation().equals(x.getLocation())){
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public int compare(IntersectionI p, IntersectionI q) {
		if(distTo[findIndex(p)] < distTo[findIndex(q)]){
			return -1;
		}
		if(distTo[findIndex(p)] > distTo[findIndex(q)]){
			return 1;
		}
		return 0;
	}

}
