import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String args[]) {

        //Partie 1
        // à commenter / décommenter selon la partie à tester
        /*Agent a = new Agent("A");
        Agent b = new Agent("B");
        Agent c = new Agent("C");
        Agent d = new Agent("D");
        ArrayList<Agent> agents = new ArrayList();
        agents.add(a);
        agents.add(b);
        agents.add(c);
        agents.add(d);*/

        //Partie 2
        // à commenter / décommenter selon la partie à tester
        Agent a = new AgentCommuniquant("A");
        Agent b = new AgentCommuniquant("B");
        Agent c = new AgentCommuniquant("C");
        Agent d = new AgentCommuniquant("D");
        ArrayList<Agent> agents = new ArrayList();
        agents.add(a);
        agents.add(b);
        agents.add(c);
        agents.add(d);

        //initial stack of agent
        Stack<Agent> initStack = new Stack();
        initStack.push(a);
        initStack.push(b);
        initStack.push(c);
        initStack.push(d);

        //solution stack that we want
        a.setTargetAgent("Table");
        b.setTargetAgent("D");
        c.setTargetAgent("A");
        d.setTargetAgent("C");

        Environnement e = new Environnement(initStack, agents);
        for (Agent agent : agents) agent.setE(e);

        launch(e, agents);

    }

    private static boolean launch(Environnement e, ArrayList<Agent> agents) {
        boolean timeout = false;
        int iteration = 0;
        while (!timeout) {
            for (Agent agent : agents) {
                agent.action();
                iteration++;
                e.showEnv(iteration);
                if (e.verify()) return true;
            }
        }
        return false;
    }
}
