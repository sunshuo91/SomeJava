package cs4102hw5;

public class edge {
	private String start;
	private String end;
	private boolean direction;
	private int compacity;
	private int currentCap;
	
	public edge(String s, String e, int cap) {
		this.start = s;
		this.end = e;
		this.direction = true;
		this.compacity = cap;
		this.currentCap = 0;
	}

	public void changeDir() {
		this.direction = !direction;
	}
	
	public void checkCap() {
		if ((this.currentCap++) == this.compacity) {
			changeDir();
			this.currentCap = 0;
		}
	}
	
	public String getEnd() {
		return this.end;
	}
}
