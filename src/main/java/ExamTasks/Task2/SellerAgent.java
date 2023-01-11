package ExamTasks.Task2;

import jade.core.Agent;

public class SellerAgent extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new SellerAgentBehaviour());
    }
}
