import java.util.*;

public class AgentCommuniquant extends Agent {

    public AgentCommuniquant(String tag) {
        super(tag);
        currentStep = "Table";
    }

    @Override
    public void moveOrPush() {
        if (!e.canMove(this)) {
            push();
        } else {
            move();
        }
    }

    @Override
    public void move() {
        e.move(this, currentStep);
    }

    public boolean askIfTargetBlocked() {
        List<Agent> agents = e.getAgents();
        for (Agent a : agents) {
            if (a.perception() == currentStep) {
                a.action();
                return true;
            }
        }
        return false;
    }

    public void askAgent(){
        List<Agent> agents = e.getAgents();
        for (Agent a : agents) {
            if (a.targetAgent == currentStep) {
                a.action();
            }
        }
    }

    public boolean action() {
        if(perception() == targetAgent && targetAgent == currentStep) {
            communicateNewStep();
        } else if (currentStep == perception()) {
            moveOrPush();
        } else if (askIfTargetBlocked());
        else if (currentStep == targetAgent) {
            moveOrPush();
            if (perception() == targetAgent) {
                communicateNewStep();
            }
        } else askAgent();
        return true;
    }

    private void communicateNewStep() {
        List<Agent> agents = e.getAgents();
        for (Agent a : agents) {
            a.setCurrentStep(tag);
        }
    }
}
