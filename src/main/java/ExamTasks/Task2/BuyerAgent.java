package ExamTasks.Task2;

import jade.core.Agent;

public class BuyerAgent extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new BuyerParallelBehaviour(this, 4000));
    }
}
