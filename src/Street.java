
public class Street implements StreetI {

	private int id;
	private String name;
	private Point first;
	private Point second;
	@Override
	public void setIdNumber(int id) {
		this.id = id;
	}

	@Override
	public void setName(String name) {
		this.name = name;

	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setPoints(Point firstPoint, Point secondPoint) {
		first = new Point();
		second = new Point();
		first.setx(firstPoint.getx());
		first.sety(firstPoint.gety());
		first.mark(firstPoint.isMarked());
		second.mark(secondPoint.isMarked());
		second.setx(secondPoint.getx());
		second.sety(secondPoint.gety());

	}

	@Override
	public Point getFirstPoint() {
		return this.first;
	}

	@Override
	public Point getSecondPoint() {
		return this.second;
	}

	@Override
	public int getIdNumber() {
		return this.id;
	}

	@Override
	public Double getDistance() {
		double dx = Math.abs(first.getx() - second.getx());
		double dy = Math.abs(first.gety() - second.gety());
		dx = dx * dx;
		dy = dy * dy;		
		return Math.sqrt(dx + dy);
	}

}
