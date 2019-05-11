import java.util.ArrayList;

public class Word {
    private ArrayList<Object> word;

    public Word(){
        word = new ArrayList<Object>();
    }

    public Word(Word oldWord){
        word = new ArrayList<Object>();
        ArrayList<Object> old = oldWord.getWord(); 
        for(int i = 0; i < old.size(); i++){
            word.add(old.get(i)); 
        }
    }

    public void addElement(Object element){
        word.add(element); 
    }

    public ArrayList<Object> getWord(){
        return word;
    }

    public Object getElement(int number){
        if(number < word.size()){
            return word.get(number); 
        }
        return "Index out of bounds"; 
    }

    public ArrayList<Object> removeFirstElement(){
        word.remove(0); 
        return word; 
    }

    public ArrayList<Object> changeElement(Word newPrefix){
        removeFirstElement(); 
        word.addAll(0,newPrefix.getWord()); 
        return word;
    }

    public ArrayList<Object> addPrefix(Word newPrefix){
        word.addAll(0,newPrefix.getWord()); 
        return word;
    }

    @Override
    public String toString(){
        String x = "";
        for(int i = 0 ; i<word.size(); i++){
            x += word.get(i) + " "; 
        }
        return x; 
    }
  

}