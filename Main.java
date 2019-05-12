import java.util.*; 

public class Main{
    public static void main(String[] args){
        HashMap<Character,String[]> rules = new HashMap<>();

		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
            String rule = sc.nextLine().replaceAll("\\s", "");
            char leftNonTerminal = rule.charAt(0);
            String[] productionWords = rule.substring(2).split("\\|");
            
            rules.put(leftNonTerminal, productionWords);
        }
        FNG fng = new FNG(rules);
        System.out.println("\n\nGrammar after execution");
        System.out.println(fng.getGrammar());

    }

}