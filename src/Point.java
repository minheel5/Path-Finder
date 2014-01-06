
/**
 * A self explanatory class.
 * For this assignment you can assume that every point is essentially
 * an intersection. More precisely, the location of an intersection
 * is a point, and all points will be represented by an intersection, as
 * we have chosen a closed set of points (a closed graph).
 *
 * @author Min Hee Lee
 *
 */


public class Point
{
	private double x;
	private double y;
	private boolean marked;
	
	public boolean isMarked(){
		return marked;
	}
	
	public void mark(boolean b){
		marked = b;
	}

	public double getx()
	{
		return this.x;
	}

	public void setx(double x)
	{
		this.x = x;
	}

	public double gety()
	{
		return this.y;
	}

	public void sety(double y)
	{
		this.y = y;
	}

	@Override	
	public String toString(){
		//TODO: Implement toString
		return "*";
	}

	//These two methods override the normal java object functions
	//and may be implemented by the student
	
	public boolean equals(Point p){
		if(this.x == p.getx() && this.y == p.gety()){
			return true;
		}
		else{
			return false;
		}
	}
}

