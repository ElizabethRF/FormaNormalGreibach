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
    private HashMap<NonTerminal,Integer> nonTerminalMap; 

    public FNG(){
        grammar = new Grammar(); 
        nonTerminalMap = new HashMap<NonTerminal,Integer>();
    }

    // Step 1
    public void setGrammar(HashMap<Character,String> initialGrammar){
        orderNonTerminals(initialGrammar);
    }

    public void orderNonTerminals(HashMap<Character,String> initialGrammar){
        int i  = 0; 
        grammar.addNonTerminal('S', i);
        for (char key : initialGrammar.keySet()) {
            if(key != 'S'){
                grammar.addNonTerminal(key, i+1);
                addProduction(initialGrammar.get(key));
            }
            i++;
        }
    }

    public void addProduction(String production){
        Production newProduction = new Production(); 
        String[] words = production.split("|"); 
        for(int i = 0 ; i < words.length; i++){
            words[i].replace(" ", ""); 
            Word newWord = new Word(); 
            for(int j = 0; j<words[i].length(); j++){
                if(words[i].charAt(j) >= 'A' && words[i].charAt(j) <= 'Z'){
                    newWord.addElement(nonTerminalMap);
                }else{
                    newWord.addElement(words[i].charAt(j));
                }
                
            }
            
            newProduction.addWord(newWord);
        }
       

        grammar.addProduction(newProduction);

    }

    






        

}