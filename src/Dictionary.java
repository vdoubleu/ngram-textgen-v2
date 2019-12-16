import java.util.*;

public class Dictionary {
	HashMap<String, WordTrie> dictionary = new HashMap<String, WordTrie>();
	
	public void updateDic(ArrayList<String> s){
		ArrayList<String> copyS = new ArrayList<String>();
		copyS.addAll(s);
		String first = copyS.get(0);
		WordTrie t;
		
		if(dictionary.containsKey(first)){
			t = dictionary.get(first);
			copyS.remove(0);
			WordTrie.addSeq(t, copyS);
			dictionary.replace(first, t);
		}
		else {
			t = new WordTrie(first, 1);
			copyS.remove(0);
			WordTrie.addSeq(t, copyS);
			dictionary.put(first, t);
		}
	}
	
	public int getFreq(ArrayList<String> s){
		int freq = 0;
		String first = s.get(0);
		s.remove(0);
		
		//while(freq == 0){
			if (s.size() == 0){
				System.out.println("input length zero, no word found");
				return 0;
			}
			
			if(dictionary.containsKey(first)){
				freq = WordTrie.seqFreq(dictionary.get(first), s);
			} else {
				System.out.println("first word not found in dic");
				//System.out.println("word not found, reducing level to:" + s.size());
				//first = s.get(0);
				//s.remove(0);
			}
		//}
		
		return freq;
	}
	
	public ArrayList<Integer> getAllNextFreq(ArrayList<String> s){
		ArrayList<Integer> nextFreqs = new ArrayList<Integer>();
		String first = s.get(0);
		s.remove(0);
		
		if(s.size() == 0){
			System.out.println("input length zero, no word found");
			return nextFreqs;
		}
		
		if(dictionary.containsKey(first))
			WordTrie.seqChildFreq(dictionary.get(first), s, nextFreqs);
		else
			System.out.println("first word not found in dic");
			
		
		return nextFreqs;
		
		
	}
	
	
}
