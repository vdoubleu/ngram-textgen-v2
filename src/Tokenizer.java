import java.util.*;
import java.io.*;

public class Tokenizer {
	int n;
	String filePath;
	File dataFile;
	Scanner scan;
	ArrayList<String> words = new ArrayList<String>();
	
	public Tokenizer(String path, int depth){
<<<<<<< HEAD
		this.n = depth + 1;
=======
>>>>>>> c1cf957baf4339661fd0b18455f959df8b485b4b
		this.filePath = path;
		this.dataFile = new File(this.filePath);
		try {
			scan = new Scanner(dataFile);
<<<<<<< HEAD
		
			for(int i = 0; i < n; i++)
				this.words.add(scan.next());
		} catch (FileNotFoundException e) {
=======
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
>>>>>>> c1cf957baf4339661fd0b18455f959df8b485b4b
			e.printStackTrace();
		}
	}
	
<<<<<<< HEAD
	public boolean hasNextToken(){
		return scan.hasNext();
	}
	
	public void getNextToken(){
			this.words.remove(0);
			this.words.add(scan.next());
	}
	
	public ArrayList<String> getWords(){
		return words;
	}
	
	public void resetUpdateTokenizer(int depth){
		this.n = depth + 1;
		
		try {
			scan = new Scanner(dataFile);
		
			for(int i = 0; i < n; i++)
				this.words.add(scan.next());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
=======
>>>>>>> c1cf957baf4339661fd0b18455f959df8b485b4b
}
