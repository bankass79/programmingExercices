package programmingExercices;

public class NumericLiteral {

	public static void main(String[] args) {


		//byte b1011 = 0;
		//byte b1= b1011;
		//byte b2= 1011b;
		
		byte b3= 0b1001;
		//byte b4= 0xb001;  il faut le caster avec byte 
		byte b4= (byte) 0xb001;
		
		System.out.println("b3:"+ " " + b3);
		System.out.println("b4" + " " + b4);

	}

}
