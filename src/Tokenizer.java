import java.util.*;
import java.io.*;

public class Tokenizer {
	int n;
	String filePath;
	File dataFile;
	Scanner scan;
	ArrayList<String> words = new ArrayList<String>();
	
	public Tokenizer(String path, int depth){
		this.filePath = path;
		this.dataFile = new File(this.filePath);
		try {
			scan = new Scanner(dataFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
