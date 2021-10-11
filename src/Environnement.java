import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Environnement {
    private Stack<Agent> solutionStack;
    private ArrayList<Pile> piles;
    private ArrayList<Agent> agents;

    public Environnement(Stack initStack, Stack solutionStack, ArrayList<Agent> agents) {
        this.solutionStack = solutionStack;
        this.agents = agents;
        piles.add(new Pile(initStack, new Emplacement(1)));
        piles.add(new Pile(new Stack(), new Emplacement(2)));
        piles.add(new Pile(new Stack(), new Emplacement(3)));
    }

    public boolean canMove(Agent agent) {
        Agent a;
        for (Pile pile : piles) {
            a = pile.stack.peek();
            if (a.equals(agent)) {
                return true;
            }
        }
        return false;
    }

    public Agent whoUnder(Agent agent) {
        boolean next = false;
        Agent previousAgent = null;
        for (Pile pile : piles) {
            for (Agent a : pile.stack) {
                if (a.equals(agent)) {
                    return previousAgent;
                }
                previousAgent = a;
            }
        }
        return null;
    }

    public void push(Agent agent) {
        boolean next = false;
        Agent previousAgent = null;
        for (Pile pile : piles) {
            for (Agent a : pile.stack) {
                if (next) {
                    a.push();
                }
                if (a.equals(agent)) {
                    next = true;
                }
            }
        }
    }

    public List<Agent> getFreePlaces(Agent a) {
        ArrayList<Agent> availableAgent = null;
        for (Pile pile : piles) {
            if (pile.stack.empty()) availableAgent.add(null);
            else availableAgent.add(pile.stack.peek());
        }
        return availableAgent;
    }

    public void moveTo(Agent a, Agent b) {
        boolean next = false;
        Agent previousAgent = null;
        ArrayList<Pile> pilesCopy = (ArrayList<Pile>) piles.clone();
        for (Pile pile : pilesCopy) {
            if (pile.stack.contains(a)) piles.get(pilesCopy.indexOf(pile)).stack.remove(a);
            if (pile.stack.peek().equals(b)) piles.get(pilesCopy.indexOf(pile)).stack.push(a);
        }
    }

    public boolean verify(){
        for(Pile pile: piles) {
            if (pile.stack.equals(solutionStack)) return true;
        }
        return false;
    }
}
