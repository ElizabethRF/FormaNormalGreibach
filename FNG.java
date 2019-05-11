import java.util.ArrayList;
import java.util.HashMap;

/*
    Paso 1: Ordenar no terminales
    Paso 2: Cumplir condición Ai > Ajœ,j>i
        2.1 Eliminar recursividad a la izquierda
        Para el nuevo x se ejecuta el paso 2 
    Paso 3: Cumplir forma A > aœ , œ € N*, a € T, A € N 
*/

public class FNG{
    private Grammar grammar; 
    private HashMap<Character,Integer> nonTerminalMap;

    public FNG(){
        grammar = new Grammar(); 
        nonTerminalMap = new HashMap<Character,Integer>();
    }

    public FNG(HashMap<Character,String[]> initialGrammar){
        grammar = new Grammar();
        nonTerminalMap = new HashMap<Character,Integer>();
        orderNonTerminals(initialGrammar);
        System.out.println("original grammar");
        System.out.println(grammar);
        fngCondition();
    }

    public void orderNonTerminals(HashMap<Character,String[]> initialGrammar){
        // Add non terminals to grammar and store order in FNG 
        grammar.addNonTerminal('S', 0);
        nonTerminalMap.put('S', 0);
        int i  = 1; 
        for (char key : initialGrammar.keySet()) {
            if(key != 'S'){
                grammar.addNonTerminal(key, i);
                nonTerminalMap.put(key, i);
                i++;
            }
        }

        // Add productions in grammar in the position of the non terminal that generates them 
        addProduction(initialGrammar.get('S'));
        for (char key : initialGrammar.keySet()) {
            if(key != 'S'){
                addProduction(initialGrammar.get(key));
            }
        }
    }

    public void addProduction(String[] production){
        Production newProduction = new Production();
        for(int i = 0 ; i < production.length; i++){
            Word newWord = new Word(); 
            for(int j = 0; j < production[i].length(); j++){
                char nt = production[i].charAt(j);
                if(nt >= 'A' && nt <= 'Z'){
                    newWord.addElement(nonTerminalMap.get(nt));
                }
                else{
                    newWord.addElement(nt);
                }
            }
            newProduction.addWord(newWord);
        }
        grammar.addProduction(newProduction);
    }

    public Grammar getGrammar(){
        return grammar;
    }

    public void fngCondition(){
        for(int i = 0; i < nonTerminalMap.size(); i++){
            
            for(int j = 0 ; j < grammar.getProduction(i).getProduction().size();j++ ){
                // for each non terminal validate i <= j 
                Object firstElement = grammar.getProduction(i).getWord(j).getElement(0); 
                if(firstElement instanceof Integer){
                    if(i > (Integer)firstElement){
                        System.out.println("Replace first Element i , j , (Integer)firstElement " + i+ " " +j+ " "+(Integer)firstElement ); 
                        replaceFirstElement(i,j,(Integer)firstElement); 
                    }
                }
                // for each productio 
            }
        }
    }

    public void replaceFirstElement(int nTerminal, int wordIndex, int firstElement){
        Word alpha = new Word(grammar.getProduction(nTerminal).getWord(wordIndex));
        alpha.removeFirstElement(); 
        ArrayList<Word> words = grammar.getProduction(firstElement).getProduction(); 
        for(int i = 0; i < words.size(); i++){
            Word newWord = new Word(alpha);
            newWord.addPrefix(words.get(i)); 
            grammar.getProduction(nTerminal).addWord(newWord);
        }
        grammar.getProduction(nTerminal).removeWord(wordIndex);
    }

}