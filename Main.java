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
        System.out.println(fng.getGrammar());



        /*Word test = new Word();
        Word test2 = new Word(); 

        test.addElement(1);
        test.addElement("abc");
        test.addElement(2);
        test.addElement("xx");

        test2.addElement(9);
        test2.addElement("oswaldo");
        test2.addElement(0);
        test2.addElement("rafa");


        System.out.println("Palabra1: " + test);
        System.out.println("Palabra2: " + test2);

        test.removeFirstElement(); 
        System.out.println("Remove first element");
        System.out.println("Palabra1: " + test);
        System.out.println("Palabra2: " + test2 + "\n");

        test.changeElement(test2); 
        System.out.println("add new prefix");
        System.out.println("Palabra1: " + test);
        System.out.println("Palabra2: " + test2 + "\n");*/

    }

}