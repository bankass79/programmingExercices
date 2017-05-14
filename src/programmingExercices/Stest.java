package programmingExercices;

/**
 * @author amadou
 * Rules for factory method:
 *  Every factory method must be public method.
 *  Every factory method must be similar to name of the class where it present
 *  A Singleton class is one which allows us to create only one object for JVM.
 * this program which illustrates the concept of factory method and Singleton process
 */
public class Stest {
	private static Stest st;

    private Stest() {
        System.out.println("OBJECT CREATED FIRST TIME");
    }

    public static Stest create() {
        if (st == null) {
            st = new Stest();

        } else {
            System.out.println("OBJECT ALREADY CREATED");
        }

        return (st);
    }
}
