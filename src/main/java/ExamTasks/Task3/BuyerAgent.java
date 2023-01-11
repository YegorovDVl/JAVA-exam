package ExamTasks.Task3;

import jade.core.Agent;

public class BuyerAgent extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new BuyerFSMBehaviour(this, 3000));
    }
}
