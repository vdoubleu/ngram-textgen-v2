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
	
	public void setKey(String s){this.key = s;}
	public void setFreq(int i){this.freq = i;}
	
	public void addChild(String s){
		this.words.add(new WordTrie(s, 1));
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
	public static boolean itExists(WordTrie t, ArrayList<String> s){
		if(s.isEmpty())
			return true;
		
		String first = s.get(0);
		
		int strPosInChild = t.childPos(first);
		
		if(strPosInChild == -1)
			return false;
		else{
			s.remove(0);
			return itExists(t.getChild(t.childPos(first)), s);
		}
	}
	
}
