import java.util.ArrayList;

public class Grammar{

    private ArrayList<Production> grammar; 
    public Grammar(){
        grammar = new ArrayList<Production>(); 
    }

    public Grammar getGrammar(){
        return grammar; 
    }

    public void addProduction(Production newProduction){
        grammar.add(newProduction); 
    }

    public Production getProduction(int number){
        return grammar.get(number); 
    }
}