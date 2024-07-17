package pe.edu.unfv.besttraveludemy.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class BestTravelUtil {

    private static final Random randon = new Random();

    public static LocalDateTime getRandomSoon(){
        var randomHours = randon.nextInt(5 - 2) + 2;
        var now = LocalDateTime.now();
        return now.plusHours(randomHours);
    }

    public static LocalDateTime getRandomLatter(){
        var randomHours = randon.nextInt(12 -6) + 6;
        var now = LocalDateTime.now();
        return now.plusHours(randomHours);
    }
    
    public static void writeNotification(String text, String path) throws IOException{
    	var fileWriter = new FileWriter(path, true);
    	var bufferedWriter = new BufferedWriter(fileWriter);
    	try(fileWriter; bufferedWriter) {
			bufferedWriter.write(text);
			bufferedWriter.newLine();
		}
    } 
}
