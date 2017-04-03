package programmingExercices;

public class Counter {
	
	
	private int value;
	
	
	
	public Counter(int  initialValue) {
		super();
		this.value = initialValue;
	}

	public void count(){
	
		value +=1;
	}

	public int getValue() {
		return value;
	}

}
