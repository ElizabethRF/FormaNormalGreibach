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
    //private HashMap<NonTerminal,Integer> nonTerminalMap;
    private HashMap<Character,Integer> nonTerminalMap;

    public FNG(){
        grammar = new Grammar(); 
        nonTerminalMap = new HashMap<Character,Integer>();
        //nonTerminalMap = new HashMap<NonTerminal,Integer>();
    }

    public FNG(HashMap<Character,String[]> initialGrammar){
        grammar = new Grammar();
        nonTerminalMap = new HashMap<Character,Integer>();
        //nonTerminalMap = new HashMap<NonTerminal,Integer>();
        orderNonTerminals(initialGrammar);
    }

    public void orderNonTerminals(HashMap<Character,String[]> initialGrammar){
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





        

}