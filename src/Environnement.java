import java.util.*;

public class Environnement {
    private ArrayList<Stack<Agent>> piles = new ArrayList<>();
    private ArrayList<Agent> agents;

    public Environnement(Stack<Agent> initStack, ArrayList<Agent> agents) {
        this.agents = agents;
        piles.add(initStack);
        piles.add(new Stack());
        piles.add(new Stack());
    }

    public boolean canMove(Agent agent) {
        Agent a;
        for (Stack<Agent> pile : piles) {
            if (!pile.isEmpty()) {
                a = pile.peek();
                if (a.equals(agent)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String whoUnder(Agent agent) {
        Agent previousAgent = null;
        for (Stack<Agent> pile : piles) {
            for (Agent a : pile) {
                if (a.equals(agent)) {
                    if (previousAgent == null) return "Table";
                    else {
                        return previousAgent.getTag();
                    }
                }
                previousAgent = a;
            }
            previousAgent = null;
        }
        return "Table";
    }

    public boolean push(Agent agent) {
        boolean next = false;
        Agent lastAgent = null;
        for (Stack<Agent> pile : piles) {
            for (Agent a : pile) {
                if (next) {
                    a.moveOrPush();
                    return true;
                }
                if (a.equals(agent)) {
                    next = true;
                    lastAgent = a;
                }
            }
        }
        lastAgent.moveOrPush();
        return false;
    }

    public void move(Agent a) {
        Stack<Agent> previousStack = null;
        for (Stack<Agent> pile : piles) {
            if (pile.contains(a)) {
                piles.get(piles.indexOf(pile)).remove(a);
                previousStack = pile;
            }
        }
        while (true) {
            int rand = new Random().nextInt(piles.size());
            if (!(previousStack == piles.get(rand))) {
                piles.get(rand).push(a);
                break;
            }
        }
    }

    public void move(Agent a, String step) {
        Stack<Agent> previousStack = null;
        ArrayList<String> tops = new ArrayList<>();
        for (Stack<Agent> pile : piles) {
            if (pile.contains(a)) {
                piles.get(piles.indexOf(pile)).remove(a);
                previousStack = pile;
            }
            if (pile.isEmpty()) tops.add("Table");
            else tops.add(pile.peek().getTag());
        }
        String target = a.getTargetAgent();
        while (true) {
            int rand = new Random().nextInt(piles.size());
            String destination;
            if (piles.get(rand).size() == 0) destination = "Table";
            else destination = piles.get(rand).peek().getTag();
            if (target == step) {
                if (!(previousStack == piles.get(rand)) && !tops.contains(step)) {
                    piles.get(rand).push(a);
                    break;
                } else if (!(previousStack == piles.get(rand)) && destination == target) {
                    piles.get(rand).push(a);
                    break;
                }
            } else {
                if (!(previousStack == piles.get(rand)) && (destination != step || previousStack.size() == 3)) {
                    piles.get(rand).push(a);
                    break;
                }
            }
        }
    }

    public boolean verify() {
        boolean verified = false;
        for (Agent a : agents) {
            if (whoUnder(a).equals(a.getTargetAgent())) verified = true;
            else {
                verified = false;
                break;
            }
        }
        return verified;
    }

    public void showEnv(int iteration) {
        System.out.println("Action n°" + iteration);
        for (Stack<Agent> pile : piles) {
            for (Agent a : pile) {
                System.out.print("|" + a.getTag() + "| ");
            }
            System.out.println();
        }
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }
}
