public class NBodyNode{

	//this function primarily organizes the information from the
	//text so that it can be printed by NBodyList
	private String name = "";
	private double mass, x, y, x_vel, y_vel;
	private int size;

	public NBodyNode(String name, String mass, String x, String y, 
		String x_vel, String y_vel, String size){

		this.name = name; 
		this.mass = Double.parseDouble(mass);
		this.x = Double.parseDouble(x);
		this.y = Double.parseDouble(y);
		this.x_vel = Double.parseDouble(x_vel);
		this.y_vel = Double.parseDouble(y_vel);
		this.size = Integer.parseInt(size);
	}

	public String name(){
		return this.name;
	}

	public double getMass(){
		return this.mass;
	}

	public double getX(){
		return this.x;
	}

	public void setX(double x){
		this.x = x;
	}

	public double getY(){
		return this.y;
	}

	public void setY(double y){
		this.y = y;
	}

	public double getXVel(){
		return this.x_vel;
	}

	public void setXVel(double x_vel){
		this.x_vel = x_vel;
	}

	public double getYVel(){
		return this.y_vel;
	}

	public void setYVel(double y_vel){
		this.y_vel = y_vel;
	}

	public int size(){
		return this.size;
	}

	public String toString(){
		String output = "";
		output = output + "\n\tName: " + this.name;
		output = output + "\n\tMass: " + this.mass;
		output = output + "\n\tInitial X Coordinate: " + this.x;
		output = output + "\n\tInitial Y Coordinate: " + this.y;
		output = output + "\n\tX Velocity: " + this.x_vel;
		output = output + "\n\tY Velocity: " + this.y_vel;
		output = output + "\n\tSize: " + this.size;

		return output;
	}
}