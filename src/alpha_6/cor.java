//package alpha_6;

public class cor {
	int x, y;
	cor(int x, int y){
		this.x=x;
		this.y=y;
	}
	cor(){
		this.x=0;
		this.y=0;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public void setX(int x) {
		this.x=x;
		return;
	}
	public void setY(int y) {
		this.y=y;
		return;
	}
	public String toString() {
		return String.format("x: %02d, y: %02d", this.x, this.y);
	}
	
}
