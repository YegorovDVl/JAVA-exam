package ExamTasks.Task9;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;

public class BuyerWakerBehaviour extends WakerBehaviour {
    private final BooksCFG cfg;
    public BuyerWakerBehaviour(Agent a, long timeout, BooksCFG cfg) {
        super(a, timeout);
        this.cfg = cfg;
    }

    @Override
    protected void onWake() {
        getAgent().addBehaviour(new BuyerBehaviour(cfg));
    }
}
