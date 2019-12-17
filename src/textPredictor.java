import java.util.*;


public class textPredictor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String path = "./src/inputData.txt";

		Dictionary dic = new Dictionary();
		Scanner scan = new Scanner(System.in);
		
		int depth;
		
		System.out.println("What is your desired depth of search?");
		depth = scan.nextInt();
		scan.nextLine();
		
		Tokenizer token = new Tokenizer(path, 1);

		
		//fill dictionary with words
		for(int i = 2; i < depth + 1; i++){
			while(true){
				System.out.println(Arrays.toString(token.getWords().toArray()));
				dic.updateDic(token.getWords());
				
				if(token.hasNextToken())
					token.getNextToken();
				else break;
			}
			token.resetUpdateTokenizer(i);
		}
		/*
		ArrayList<String> test = new ArrayList<String>();
		test.add("testing");
		test.add("testing");
		//test.add("hi");
		
		//dic.updateDic(test);
		
		ArrayList<String> test1 = new ArrayList<String>();
		test1.add("testing");
		//test1.add("testing");
		//test1.add("testing");
		//test1.add("hi");
		
		//dic.updateDic(test1);
		
		System.out.println("---");
		System.out.println(dic.getFreq(test));
		System.out.println("---");
		System.out.println(dic.getAllNextFreq(test).toString());
		*/
		
		
		// outputting
		ArrayList<String> wordHist;
		String inputStr;
		
		System.out.println("input starting prompt, the word count must be greater than or equal to " + depth);
		inputStr = scan.nextLine();
		
		wordHist = new ArrayList<String>(Arrays.asList(inputStr.split(" ")));
		
		while(wordHist.size() > depth)
			wordHist.remove(0);
		
		/*
		for(int i = 0; i < wordHist.size(); i++){
			System.out.println(wordHist.get(i));
		}*/
		
		
		
	}
	/*
	public static String nextWord(ArrayList<String> w, Dictionary d){
		int currentSeqFreq = d.getFreq(w);
		
		
	}*/
	
	public static Pair<Double, String> highestProb(ArrayList<Pair<Double, String>> lst){
		Pair<Double, String> largest = lst.get(0);
		
		for(int i = 1; i < lst.size(); i++){
			if(lst.get(i).getFirst() > largest.getFirst())
				largest = lst.get(i);
		}
		
		return largest;
	}
	
	public static ArrayList<Pair<Double, String>> calcProbs(ArrayList<Pair<Integer, String>> p, int mainFreq){
		ArrayList<Pair<Double, String>> outProb = new ArrayList<Pair<Double, String>>();
		
		for(int i = 0; i < p.size(); i++)
			outProb.add(new Pair<Double, String>((double)(p.get(i).getFirst()/mainFreq), p.get(i).getSec()));
		
		return outProb;
	}

}
