import java.util.ArrayList;

public class Grammar{

    private ArrayList<NonTerminal> nonTerminals;
    private NonTerminal initialSymbol; 
    private ArrayList<Production> productions; 
    
    public Grammar(){
        nonTerminals = new ArrayList<NonTerminal>(); 
        initialSymbol = new NonTerminal('S',0);
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

    public ArrayList<NonTerminal> getNonTerminals(){
        return nonTerminals; 
    }

    @Override
    public String toString(){
        String grammar = "";
        grammar += nonTerminalsToString(); 
        grammar += initialToString();
        grammar += productionsToString(); 
        return grammar;
    }


    public String nonTerminalsToString(){
        String nTerminals = "N = { "; 
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


    public String initialToString(){
        String initial = "S: " + nonTerminals.get(0).getId(); 
        initial += nonTerminals.get(0).getIndex() + "\n\n";
        return initial; 
    }

    public String productionsToString(){
        String prod = "P: \n"; 
        for(int i = 0; i < productions.size(); i++){
            prod += nonTerminals.get(i).getId();
            prod += nonTerminals.get(i).getIndex();
            prod += " -> ";
            ArrayList<Word> words = productions.get(i).getProduction(); 
            for(int j = 0; j < words.size(); j++){
                ArrayList<Object> letters = words.get(j).getWord(); 
                for(int y = 0; y < letters.size(); y++ ){
                    if(letters.get(y) instanceof Character){
                        prod += letters.get(y);
                    }
                    else{
                        NonTerminal temp = nonTerminals.get((Integer)letters.get(y));
                        prod +=  " '"+ temp.getId();
                        prod +=  temp.getIndex() + "' "; 
                    }
                }
                prod += " | ";
            }
            prod = prod.substring(0, prod.length() - 2); 
            prod += "\n";
        }
        return prod; 
    }
}