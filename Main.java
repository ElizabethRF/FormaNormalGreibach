import java.util.HashMap; 

public class Main{
    public static void main(String[] args){
        Word test = new Word();

        HashMap<String,String>  holi = new HashMap<String,String>(); 

        holi.put("A","hola");
        holi.put("B","holaa");
        holi.put("C","holaaa"); 

        orderNonTerminals(holi);
    }



    public static void orderNonTerminals(HashMap<String,String> initialGrammar){
        for (String key : initialGrammar.keySet()) {
            System.out.println(key);
        }
    }
}