package ExamTasks.Task7;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;

public class BuyerWakerBehaviour extends WakerBehaviour {
    AID topic;
    public BuyerWakerBehaviour(Agent a, long timeout, AID topic) {
        super(a, timeout);
        this.topic = topic;
    }

    @Override
    protected void onWake() {
        getAgent().addBehaviour(new BuyerBehaviour(topic));
    }
}
