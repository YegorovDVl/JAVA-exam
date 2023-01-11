package ExamTasks.Task3;

import ExamTasks.Task2.SellerAgentBehaviour;
import jade.core.Agent;

public class SellerAgent extends Agent{
    @Override
    protected void setup() {
        addBehaviour(new SellerAgentBehaviour());
    }
}
