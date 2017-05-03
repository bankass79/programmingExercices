package programmingExercices;


import static org.junit.Assert.*;

import org.junit.Test;

public class ChallengeTest {
	  
	  //Challenge challenge= new Challenge();
	
	  @Test
	  public void shouldSayHello() {
	    assertEquals("Hello, Qualified!", Challenge.sayHello("Qualified"));
	  }
	  
	  @Test
	  public void shouldSayHello2() {
	    assertEquals("Hello there!", Challenge.sayHello());
	  }
	}
