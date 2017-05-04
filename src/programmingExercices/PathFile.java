package programmingExercices;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class PathFile {


	public static void main(String[] args) {
		Path sPath = Paths.get("test1.txt");
		Path dPath = Paths.get("test2.txt");
		try {
			Files.move(sPath, dPath, StandardCopyOption.ATOMIC_MOVE);
		} catch (IOException ex) {
			System.err.println("Exception!");
		}
	}


}
