import java.util.ArrayList;

public class Grammar{

    private ArrayList<NonTerminal> nonTerminals; 
    private ArrayList<Character> terminals; 
    private NonTerminal initialSymbol; 
    private ArrayList<Production> productions; 
    
    public Grammar(){
        nonTerminals = new ArrayList<NonTerminal>(); 
        terminals = new ArrayList<Character>(); 
        initialSymbol = new NonTerminal('S',1); 
        productions = new ArrayList<Production>(); 

    }

    public ArrayList<Production> getGrammar(){
        return productions; 
    }

    public void addProduction(Production newProduction){
        productions.add(newProduction); 
    }

    public Production getProduction(int number){
        return productions.get(number); 
    }


    public void addNonTerminal(char id, int index){
        NonTerminal nonTerminal = new NonTerminal(id, index); 
        nonTerminals.add(nonTerminal);
    }

    public void addTerminal(char x){
        if(!terminals.contains(x)){
            terminals.add(x);
        }
        
    }

    @Override
    public String toString(){
        String grammar = "G = (N,T,S,P)\n\n";

        grammar += nonTerminalsToString(); 
        grammar += terminalsToString(); 
        grammar += initialToString();
        
        grammar += productionsToString(); 
        return grammar;
    }


    public String nonTerminalsToString(){
        String nTerminals = "N = {"; 
        for(int i = 0; i < nonTerminals.size() - 1; i++){
            nTerminals += nonTerminals.get(i).getId(); 
            nTerminals += nonTerminals.get(i).getIndex();
            nTerminals += " , ";
        } 
        nTerminals += nonTerminals.get(nonTerminals.size() - 1).getId(); 
        nTerminals += nonTerminals.get(nonTerminals.size() - 1).getIndex();
        nTerminals += " } \n\n";
        return nTerminals; 
    }


    public String terminalsToString(){
        String term = "T = {"; 
        for(int i = 0; i < terminals.size() - 1; i++){
            term += terminals.get(i); 
            term += " , ";
        } 
        term += terminals.get(terminals.size() - 1); 
        term += " } \n\n";
        return term; 
    }

    public String initialToString(){
        String initial = "S " + initialSymbol.getId() + initialSymbol.getIndex() + "\n\n";
        return initial; 
    }

    public String productionsToString(){
        String prod = "P:"; 
        for(int i = 0; i<productions.size(); i++){
            prod += "\n";
            ArrayList<Word> words = productions.get(i).getProduction(); 
            for(int j = 0; j < words.size(); j++){
                ArrayList<Object> letters = words.get(j).getWord(); 
                for(int y = 0; y < letters.size(); y++ ){
                    if(letters.get(y) instanceof String){
                        prod += letters.get(y);
                    }else{
                        NonTerminal temp = NonTerminal.class.cast(letters.get(y));
                        prod +=  " '"+ temp.getId(); 
                        prod +=  temp.getIndex() + "' "; 
                    }
                }
                prod += " | ";
            }
        }
        return prod; 
    }
}