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

    public FNG(){
        grammar = new Grammar(); 
    }

    public void orderNonTerminals(HashMap<Character,String> initialGrammar){
        int i  = 0; 
        for (char key : initialGrammar.keySet()) {
            grammar.addNonTerminal(key, i+1);
            i++;
        }
    }






        

}