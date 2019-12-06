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
			while(token.hasNextToken()){
				dic.updateDic(token.getWords());
				token.getNextToken();
			}
			token.resetUpdateTokenizer(i);
		}
		
		
		// outputting
		ArrayList<String> wordHist;
		String inputStr;
		
		System.out.println("input starting prompt, the word count must be greater than or equal to " + depth);
		inputStr = scan.nextLine();
		
		wordHist = new ArrayList<String>(Arrays.asList(inputStr.split(" ")));
		
		while(wordHist.size() > depth)
			wordHist.remove(0);
		
		for(int i = 0; i < wordHist.size(); i++){
			System.out.println(wordHist.get(i));
		}
		
		
<<<<<<< HEAD
=======
		System.out.println(dic.getFreq(lst));
		
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println(scan.next());
		System.out.println(scan.next());
>>>>>>> c1cf957baf4339661fd0b18455f959df8b485b4b
	}

}
