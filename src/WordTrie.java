import java.util.ArrayList;

public class WordTrie {
	String key;
	int freq;
	ArrayList<WordTrie> words = new ArrayList<WordTrie>();
	
	public WordTrie(){
		setKey("");
		setFreq(0);
	}
	
	public WordTrie(String s, int i){
		setKey(s);
		setFreq(i);
	}
	
	public String getKey(){return this.key;}
	public int getFreq(){return this.freq;}
	public String getChildKey(int listPos){return this.words.get(listPos).getKey();}
	public ArrayList<WordTrie> getWordTries(){return this.words;}
	
	public ArrayList<String> getWords(){
		ArrayList<String> strLst = new ArrayList<String>();
		
		for(int i = 0; i < words.size(); i++){
			strLst.add(this.getChildKey(i));
		}
		
		return strLst;
	}
	
	public void setKey(String s){this.key = s;}
	public void setFreq(int i){this.freq = i;}
	
	public void addChild(String s, int freq){
		this.words.add(new WordTrie(s, freq));
	}
	
	public void addExistingChild(WordTrie t){
		this.words.add(t);
	}
	
	// returns boolean for whether a specific child node exists.
	public boolean isChild(String s){
		boolean isChildBool = false;
		
		for(int i = 0; i < words.size(); i++){
			if(getChildKey(i).equals(s)) 
				isChildBool = true;
		}
		
		return isChildBool;
	}
	
	
	// returns position of a specific child node. If it doesn't exist, it returns -1
	public int childPos(String s){
		for(int i = 0; i < words.size(); i++){
			if(getChildKey(i).equals(s)) 
				return i;
		}
		
		return -1;
	}
	
	public WordTrie getChild(int index){return words.get(index);}
	
	// returns boolean for whether a sequence of strings occur in the trie
	// checks children nodes and grand children nodes and great grand children nodes and etc.
	
	public static boolean itExists(WordTrie t, ArrayList<String> s){
		ArrayList<String> copyS = new ArrayList<String>();
		copyS.addAll(s);
		return itExistsHelp(t, copyS);
	}
	
	private static boolean itExistsHelp(WordTrie t, ArrayList<String> s){
		if(s.isEmpty())
			return true;
		
		String first = s.get(0);
		
		int strPosInChild = t.childPos(first);
		
		if(strPosInChild == -1)
			return false;
		else{
			s.remove(0);
			return itExistsHelp(t.getChild(strPosInChild), s);
		}
	}	
	
	// similar to itExists but instead outputs freq, outputs -1 if it doesn't exist
	public static int seqFreq(WordTrie t, ArrayList<String> s){
		ArrayList<String> copyS = new ArrayList<String>();
		copyS.addAll(s);
		return seqFreqHelp(t, copyS);
	}
	
	private static int seqFreqHelp(WordTrie t, ArrayList<String> s){
		if(s.isEmpty())
			return t.getFreq();
		
		String first = s.get(0);
		
		int strPosInChild = t.childPos(first);
		
		if(strPosInChild == -1)
			return -1;
		else{
			s.remove(0);
			return seqFreqHelp(t.getChild(t.childPos(first)), s);
		}
	}
	
	private static boolean containsString(ArrayList<WordTrie> t, String s){
		for(int i = 0; i < t.size(); i++){
			if(t.get(i).getKey().equals(s)){
				return true;
			}
		}
		
		return false;
	}
	
	public static void addSeq(WordTrie t, ArrayList<String> s){
		ArrayList<String> copyS = new ArrayList<String>();
		copyS.addAll(s);
		addSeqHelp(t, copyS);
	}
	
	private static void addSeqHelp(WordTrie t, ArrayList<String> s){
		if(!(s.isEmpty())){
			String first = s.get(0);
		
			if(containsString(t.words, first)){
				s.remove(0);
				addSeqHelp(t.getChild(t.childPos(first)), s);
			} else {
				t.addChild(first, 0);
				addSeqHelp(t, s);
			}
		} else {
			t.setFreq(1 + t.getFreq());
		}
	}

	public ArrayList<Integer> getChildFreqs(ArrayList<String> s){
		ArrayList<String> copyS = new ArrayList<String>();
		copyS.addAll(s);
		
		return getWordsFreqHelp(this, copyS);
	}
	
	private static ArrayList<Integer> getWordsFreqHelp(WordTrie t, ArrayList<String> s){
		if(s.size() == 1){
			//what happens when you get there
			ArrayList<Integer> freqs = new ArrayList<Integer>(); 
			
			for(int i = 0; i < t.words.size(); i++){
				freqs.add(t.words.get(i).freq);
			}
			
			return freqs;
		}
		
		String first = s.get(0);
		
		int strPosInChild = t.childPos(first);
		
		if(strPosInChild == -1){
			System.out.println("error, not found");
			return null; //not found
		}else{
			s.remove(0);
			return getWordsFreqHelp(t.getChild(t.childPos(first)), s);
		}
	}



	
}
