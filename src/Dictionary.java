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
	
	//don't need, useless
	public int getFreq(ArrayList<String> s){
		ArrayList<String> copyS = new ArrayList<String>();
		copyS.addAll(s);
		
		int freq = 0;
		String first = copyS.get(0);
		copyS.remove(0);
		
		//while(freq == 0){
		if (copyS.size() == 0 && first == null){
			System.out.println("input length zero, no word found (dictionary)");
			return 0;
		}
		
		if(dictionary.containsKey(first)){
			freq = WordTrie.seqFreq(dictionary.get(first), copyS);
		} else {
			System.out.println("first word not found in dic");
				//System.out.println("word not found, reducing level to:" + s.size());
				//first = s.get(0);
				//s.remove(0);
		}
		//}
		
		return freq;
	}
	
	public ArrayList<Pair<Integer, String>> getAllNextFreq(ArrayList<String> s){
		ArrayList<String> copyS = new ArrayList<String>();
		copyS.addAll(s);
		
		ArrayList<Pair<Integer, String>> nextFreqs = new ArrayList<Pair<Integer, String>>();
		String first = copyS.get(0);
		
		if(dictionary.containsKey(first))
			nextFreqs = dictionary.get(first).seqChildFreqs(copyS);
		else
			System.out.println("first word not found in dic");
		/*
		for(int i = 0; i < nextFreqs.size(); i++){
			System.out.print(nextFreqs.get(i).getFirst() + "=" + nextFreqs.get(i).getSec() + " ");
		}
		System.out.println();
		*/
		return nextFreqs;
	}
	
	public int sumNextFreq(ArrayList<String> s){
		ArrayList<Pair<Integer, String>> childFreqs = getAllNextFreq(s);
		int sum = 0;
		
		for(int i = 0; i < childFreqs.size(); i++){
			sum += childFreqs.get(i).getFirst();
		}
		
		return sum;
	}
	
	
}
