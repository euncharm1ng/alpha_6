public class cor {
	int i, j;
	cor(int i, int j){
		this.i=i;
		this.j=j;
	}
	cor(){
		this.i=-1;
		this.j=-1;
	}
	public int getI() {
		return this.i;
	}
	public int getJ() {
		return this.j;
	}
	public void setI(int i) {
		this.i=i;
	}
	public void setJ(int j) {
		this.j=j;
	}
	public String toString() {
		return String.format("i: %02d, j: %02d", this.i, this.j);
	}
	
}
