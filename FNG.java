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
    private HashMap<Character,Integer> entryNonTerminalMap;

    public FNG(){
        grammar = new Grammar(); 
        entryNonTerminalMap = new HashMap<Character,Integer>();
    }

    public FNG(HashMap<Character,String[]> initialGrammar){
        grammar = new Grammar();
        entryNonTerminalMap = new HashMap<Character,Integer>();
        orderNonTerminals(initialGrammar);
        System.out.println("grammar after step 1 ");
        System.out.println(grammar);
        fngCondition();
        System.out.println("\n\ngrammar after step 2 ");
        System.out.println(grammar);
    }

    public void orderNonTerminals(HashMap<Character,String[]> initialGrammar){
        // Add non terminals to grammar and store order in FNG 
        grammar.addNonTerminal('S', 0);
        entryNonTerminalMap.put('S', 0);
        int i  = 1; 
        for (char key : initialGrammar.keySet()) {
            if(key != 'S'){
                grammar.addNonTerminal(key, i);
                entryNonTerminalMap.put(key, i);
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
                    newWord.addElement(entryNonTerminalMap.get(nt));
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
        for(int i = 0; i < grammar.getNonTerminals().size(); i++){
            // for each non terminal validate i <= j 
            for(int j = 0 ; j < grammar.getProduction(i).getProduction().size();j++ ){
                Object firstElement = grammar.getProduction(i).getWord(j).getElement(0); 
                if(firstElement instanceof Integer){
                    if(i > (Integer)firstElement){
                        replaceFirstElement(i,j,(Integer)firstElement);
                        j--; // if there is a replacement, we should evaluate the same index
                        
                    }
                }
            }
            
            deleteLeftRecursion(i);
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

    public void deleteLeftRecursion(int nTerminal){
        Production[] wordGroups = identifyRecursion(nTerminal);
        if(wordGroups[1].getProduction().size() > 0){
            int newIndex = addNonTerminal(wordGroups[0], wordGroups[1]);
            alterPreviousProduction(nTerminal,wordGroups[0], newIndex); 
        }
    }

    public Production[] identifyRecursion(int nTerminal){
        Production nonRecursive = new Production(); 
        Production recursive = new Production();
        for(int i = 0 ; i < grammar.getProduction(nTerminal).getProduction().size(); i++ ){
            Word currentWord =  new Word(grammar.getProduction(nTerminal).getWord(i)); 
            Object firstElement = currentWord.getElement(0); 
            if(firstElement instanceof Integer){
                if(nTerminal == (Integer)firstElement){
                    currentWord.removeFirstElement(); 
                    recursive.addWord(currentWord);
                }
                else{
                    nonRecursive.addWord(currentWord);
                }
            }else{
                nonRecursive.addWord(currentWord);
            }
        }
        Production[] result = {nonRecursive, recursive}; 
        return result; 
    }

    public int addNonTerminal(Production nonRecursive, Production recursive){
        int newIndex = grammar.getNonTerminals().size();
        grammar.addNonTerminal('X',newIndex ); 
        Production newProduction = new Production(recursive);
        for(int i = 0; i < recursive.getProduction().size(); i++){
            Word newWord = new Word(recursive.getWord(i)); 
            newWord.addElement(newIndex);
            newProduction.addWord(newWord);
        }
        grammar.addProduction(newProduction);
        return newIndex; 
    }

    public void alterPreviousProduction(int nTerminal, Production nonRecursive, int index){
        // remove unnecesary words
        for(int i = grammar.getProduction(nTerminal).getProduction().size() - 1; i >= 0 ; i-- ){
            Word currentWord = grammar.getProduction(nTerminal).getWord(i); 
            Object firstElement = currentWord.getElement(0); 
            if(firstElement instanceof Integer){
                if(nTerminal == (Integer)firstElement){
                    grammar.getProduction(nTerminal).removeWord(i); 
                }
            }
        }

        // add new words 
        for(int i = 0; i < nonRecursive.getProduction().size(); i++ ){
            Word newWord = new Word(nonRecursive.getProduction().get(i));
            newWord.addElement(index);
            grammar.getProduction(nTerminal).addWord(newWord);
        }

    }

}