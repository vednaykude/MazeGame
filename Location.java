public class Location{
	private int r;
	private int c;

	public Location(int row, int col){
		r = row;
		c = col;
	}
	public int getR(){
		return r;
	}
	public int getC(){
		return c;
	}
	public void incR(int x){
		r = r + x;
	}
	public void incC(int x){
		c = c + x;
	}
	public void set(int newR, int newC){
		r = newR;
		c = newC;
	}
	public boolean equals(Location other){
		if(this.getR() != other.getR() || this.getC() != other.getC())
			return false;
		else
			return true;
	}
	public static void main(String[] args){

	}
}