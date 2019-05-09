import java.util.ArrayList;

public class Production{
    private ArrayList<Word> production;

    public Production(){
        production = new ArrayList<Word>();
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


}