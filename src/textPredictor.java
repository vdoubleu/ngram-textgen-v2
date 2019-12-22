import java.util.*;


public class textPredictor {

	public static void main(String[] args) {
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
		
		// outputting
		ArrayList<String> wordHist;
		String inputStr;
		int outLength = 10;
		
		System.out.println("input starting prompt, the word count must be greater than or equal to " + (depth - 1));
		inputStr = scan.nextLine();
		
		System.out.println("how many words do you wish to ouput?");
		outLength = scan.nextInt();
		
		//prepping input
		wordHist = new ArrayList<String>(Arrays.asList(inputStr.split(" ")));
		
		while(wordHist.size() >= depth)
			wordHist.remove(0);
		
		
		System.out.print(inputStr + " -||- ");
		for(int i = 0; i < outLength; i++){
			ArrayList<String> wordHistCopy = copyLst(wordHist);
			
			String nxtWord = nextWord(wordHistCopy, dic, depth);
			System.out.print(nxtWord + " ");
			
			wordHist.remove(0);
			wordHist.add(nxtWord);
		}
		
	}
	
	public static String nextWord(ArrayList<String> inSeq, Dictionary d, int depth){
		ArrayList<String> inSeqCopy = copyLst(inSeq);
		Pair<Double, String> largestProb = highestProb(calcProbs(d.getAllNextFreq(inSeqCopy), d.sumNextFreq(inSeq)));
		
		for(int i = 1; i < depth - 1; i++){
			inSeq.remove(0);
			
			Pair<Double, String> curr = highestProb(calcProbs(d.getAllNextFreq(inSeq), d.sumNextFreq(inSeq)));
			
			if(curr.getFirst() > largestProb.getFirst())
				largestProb = curr;
		}
		
		return largestProb.getSec();
	}
	
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
		
		for(int i = 0; i < p.size(); i++){
			outProb.add(new Pair<Double, String>((double)(p.get(i).getFirst())/mainFreq, p.get(i).getSec()));
		}
			
		return outProb;
	}
	
	public static ArrayList<String> copyLst(ArrayList<String> lst){
		ArrayList<String> copy = new ArrayList<String>();
		copy.addAll(lst);
		
		return copy;
	}

}
