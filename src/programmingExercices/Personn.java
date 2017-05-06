package programmingExercices;

public class Personn { 
    String name;
    public Personn(String personName){
    name = personName;
    }
    public String greet(String yourName)
     {
        return String.format("Hi %s, my name is %s", yourName,name);
      }
 }