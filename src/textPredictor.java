import java.util.*;


public class textPredictor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Dictionary dic = new Dictionary();
		
		WordTrie t = new WordTrie();
		
		ArrayList<String> lst = new ArrayList<String>();
		lst.add("1st");
		lst.add("2nd");
		lst.add("3rd");
		lst.add("4th");
		/*
		WordTrie.addSeq(t, lst);
		*/
		/*ArrayList<String> lst = new ArrayList<String>();
		lst.add("1st");
		lst.add("2nd");
		lst.add("3rd");

		
		WordTrie.addSeq(t, lst);
		
		
		System.out.println(WordTrie.seqFreq(t, lst));*/
		dic.updateDic(lst);
		
		
		System.out.println(dic.getFreq(lst));
	}

}
