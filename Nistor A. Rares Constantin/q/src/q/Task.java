package q;

public class Task {
	private int nr;
	private int pTime;
	private int aTime;
	
	public Task(int nr, int pTime,int aTime) {
		this.nr = nr;
		this.pTime = pTime;
		this.aTime = aTime;
	}
	public int getnr() {
		return nr;
	}

	public int getpTime() {
		return pTime;
	}
	
	public int getaTime() {
		return aTime;
	}
}
