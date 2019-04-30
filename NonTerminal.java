public class NonTerminal {
    private int index;
    private char id;

    public NonTerminal(char id, int index) {
        this.index = index;
        this.id = id; 
    }

    public char getId() {
        return id;
    }

    public void setId(char id) {
        this.id = id;
    }


    public int getIndex() {
        return index;
    }


    public void setIndex(int index) {
        this.index = index;
    }

}