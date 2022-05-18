package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataReader {
	
	
	public static int[] retrievePlayerData(int i) {
		// Data: Active,Level,Floor
		
		int[] result = new int[3];
		BufferedReader s = null;
		try {
			InputStream is = ClassLoader.getSystemResourceAsStream("data/slot/slot"+(i+1)+"/player.txt");
			s = new BufferedReader(new InputStreamReader(is));
			
			String[] data = s.readLine().split(",");
			result[0] = Integer.parseInt(data[0]);
			result[1] = Integer.parseInt(data[1]);
			result[2] = Integer.parseInt(data[2]);
			
			s.close();
			
		}
		catch (FileNotFoundException e) {
			System.out.println("Cannot find file!");
		}
		catch (Exception e) {
			System.out.println("File Error!");
			System.out.println(e);
		}
		
		return result;
	}
}
