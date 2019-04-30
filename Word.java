import java.util.ArrayList;

public class Word{
    private ArrayList<Object> word;

    public Word(){
        word = new ArrayList<Object>();
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

    @Override
    public String toString(){
        String x = ""; 
        for(int i = 0; i<word.size();i++){
            x += word.get(i);
        }

        return x;
    }

}