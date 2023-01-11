package ExamTasks.Task10;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;

import java.util.Date;

public class SellerWakerBehaviour extends WakerBehaviour {
    AID topic;
    public SellerWakerBehaviour(Agent a, long timeout, AID topic) {
        super(a, timeout);
        this.topic = topic;
    }

    @Override
    protected void onWake() {
        getAgent().addBehaviour(new SellerBehaviour(topic));
    }
}
