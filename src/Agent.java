import java.util.List;
import java.util.Random;

public class Agent extends Thread{
    private Environnement e;
    private Agent targetAgent;

    public void run() {
        System.out.println("Yo");
    }

    public void push(){
        if(!e.canMove(this)){
            e.push(this);
        } else {
            moveTo();
        }
    }

    public void moveTo(){
        List<Agent> availableAgent = e.getFreePlaces(this);
        int rand = 0;
        if(availableAgent.contains(targetAgent)){
            e.moveTo(this,targetAgent);
        } else {
            if(availableAgent.contains(null)){
                e.moveTo(this,null);
            } else {
                rand = new Random().nextInt(availableAgent.size());
                e.moveTo(this,availableAgent.get(rand));
            }
        }
    }

    public boolean isGood(){
        if (e.whoUnder(this).equals(targetAgent)) return true;
        else return false;
    }

    public void setTargetAgent(Agent targetAgent) {
        this.targetAgent = targetAgent;
    }
}
