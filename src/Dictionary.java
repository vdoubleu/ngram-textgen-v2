import java.util.*;

public class Dictionary {
	HashMap<String, WordTrie> dictionary = new HashMap<String, WordTrie>();
	
	public void updateDic(ArrayList<String> s){
		String first = s.get(0);
		WordTrie t;
		
		if(dictionary.containsKey(first))
			t = dictionary.get(first);
		else 
			t = new WordTrie(first, 1);
		
		
		s.remove(0);
		WordTrie.addSeq(t, s);

		dictionary.replace(first, t);
	}
	
	public int getFreq(ArrayList<String> s){
		int freq = 1;
		String first = s.get(0);
		s.remove(0);
		
		while(freq != -1){
			if (s.size() == 0){
				System.out.println("no word found");
				return -1;
				}
			
			if(dictionary.containsKey(first))
				freq = WordTrie.seqFreq(dictionary.get(first), s);
			else {
				System.out.println("word not found, reducing level to:" + s.size());
				first = s.get(0);
				s.remove(0);
			}
		}
		
		return freq;
	}
	
}
