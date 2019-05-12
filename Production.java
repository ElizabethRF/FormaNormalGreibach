import java.util.ArrayList;

public class Production{
    private ArrayList<Word> production;

    public Production(){
        production = new ArrayList<Word>();
    }

    public Production(Production words){
        production = new ArrayList<Word>(); 
        for(int i = 0; i < words.getProduction().size(); i++){
            production.add(words.getProduction().get(i)); 
        }
    }

    public ArrayList<Word> getProduction(){
        return production; 
    }

    public void addWord(Word newWord){
        production.add(newWord); 
    }

    public Word getWord(int number){
        return production.get(number); 
    }

    public Word removeWord(int index){
        return production.remove(index);
    }

    
}