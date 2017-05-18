package programmingExercices;

class MemoFib{
	static boolean [] done;
	static int [] memo;
	public static int fib(int n){
		done = new boolean[n];
		memo = new int[n];
		return memofib(n);
	}
	static int memofib(int n){
		if (done[n])
			return memo[n];
		else {
			int result;
			switch(n){
			case 0: result = 0;
			case 1: result = 1;
			default:
				result = memofib(n-1) + memofib(n-2);
			}
			done[n] = true;
			memo[n] = result;
			return result;
		}
	}
}