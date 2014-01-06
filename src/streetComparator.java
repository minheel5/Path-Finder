import java.util.Comparator;


public class streetComparator implements Comparator<StreetI> {

	@Override
	public int compare(StreetI s1, StreetI s2) {
		if(s1.getDistance() < s2.getDistance()){
			return -1;
		}
		else if(s1.getDistance() > s2.getDistance()){
			return 1;
		}
		return 0;
	}

}
