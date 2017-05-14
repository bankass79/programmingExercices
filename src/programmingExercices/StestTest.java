package programmingExercices;

import static org.junit.Assert.*;

import org.junit.Test;

public class StestTest {
	public static void main(String[] args) {
        Stest st1 = Stest.create();
        Stest st2 = Stest.create();
        Stest st3 = Stest.create();
        if ((st1 == st2) && (st2 == st3) && (st3 == st1)) {
            System.out.println("ALL OBJECTS ARE SAME");

        } else {
            System.out.println("ALL OBJECTS ARE NOT SAME");
        }
    }

}
