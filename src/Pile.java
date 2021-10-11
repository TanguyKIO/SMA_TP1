import java.util.Stack;

public class Pile {
    public Stack<Agent> stack;
    public Emplacement emplacement;

    public void setEmplacement(Emplacement emplacement) {
        this.emplacement = emplacement;
    }

    public Pile(Stack initPile, Emplacement emplacement){
        this.emplacement = emplacement;
        stack = initPile;
    }

}
