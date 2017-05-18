package programmingExercices;

public class ArrayFib {
	public static int fib(int n){
		int [] F = new int[n];
		switch(n){
		case 0: return 0;
		case 1: return 1;
		default:
			F[0] = 0;
			F[1] = 1;
			for (int i = 2; i < n; i++){
				F[i] = F[i - 1] + F[i - 2];
			}
			return F[n - 1] + F[n - 2];
		}
	}
}
