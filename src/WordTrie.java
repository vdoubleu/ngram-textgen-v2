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
			if(getChildKey(i) == s) 
				isChildBool = true;
		}
		
		return isChildBool;
	}
	
	
	// returns position of a specific child node. If it doesn't exist, it returns -1
	public int childPos(String s){
		for(int i = 0; i < words.size(); i++){
			if(getChildKey(i) == s) 
				return i;
		}
		
		return -1;
	}
	
	public WordTrie getChild(int index){return words.get(index);}
	
	// returns boolean for whether a sequence of strings occur in the trie
	// checks children nodes and grand children nodes and great grand children nodes and etc.
	public static boolean itExists(WordTrie t, ArrayList<String> s){
		if(s.isEmpty())
			return true;
		
		String first = s.get(0);
		
		int strPosInChild = t.childPos(first);
		
		if(strPosInChild == -1)
			return false;
		else{
			s.remove(0);
			return itExists(t.getChild(strPosInChild), s);
		}
	}	
	
	// similar to itExists but instead outputs freq, outputs -1 if it doesn't exist
	public static int seqFreq(WordTrie t, ArrayList<String> s){
		if(s.isEmpty())
			return t.getFreq();
		
		String first = s.get(0);
		
		int strPosInChild = t.childPos(first);
		
		if(strPosInChild == -1)
			return -1;
		else{
			s.remove(0);
			return seqFreq(t.getChild(t.childPos(first)), s);
		}
	}
	
	private static boolean containsString(ArrayList<WordTrie> t, String s){
		for(int i = 0; i < t.size(); i++){
			if(t.get(i).getKey() == s)
				return true;
		}
		
		return false;
	}
	
	public static void addSeq(WordTrie t, ArrayList<String> s){
		if(!(s.isEmpty())){
			String first = s.get(0);
		
			if(containsString(t.words, first)){
				s.remove(0);
				addSeq(t.getChild(t.childPos(first)), s);
			} else {
				t.addChild(first, 0);
				addSeq(t, s);
			}
		} else {
			t.setKey(1 + t.getKey());
		}
	}
	

	
}
