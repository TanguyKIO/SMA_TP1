public class Agent {
    protected Environnement e;
    protected String tag;
    protected String targetAgent;

    //argument utile seulement pour la deuxième partie avec les agents communiquant
    protected String currentStep;

    public Agent(String tag) {
        this.tag = tag;
    }

    public void moveOrPush() {
        if (!e.canMove(this)) {
            push();
        } else {
            move();
        }
    }

    public void push() {
        e.push(this);
    }

    public void move() {
        e.move(this);
    }

    public String getTargetAgent() {
        return targetAgent;
    }

    public void setTargetAgent(String targetAgent) {
        this.targetAgent = targetAgent;
    }

    public void setE(Environnement e) {
        this.e = e;
    }

    public String getTag() {
        return tag;
    }

    public String perception() {
        return e.whoUnder(this);
    }

    public boolean action() {
        String under = perception();
        if (under.equals(targetAgent)) return false;
        else {
            moveOrPush();
            return true;
        }
    }

    public void setCurrentStep(String currentStep) {
        this.currentStep = currentStep;
    }
}
