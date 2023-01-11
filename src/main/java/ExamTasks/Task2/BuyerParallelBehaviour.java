package ExamTasks.Task2;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;

public class BuyerParallelBehaviour extends ParallelBehaviour {
    public BuyerParallelBehaviour(Agent a, long timeout){
        super(a, WHEN_ALL);
        addSubBehaviour(new BuyerTickerBehaviour(a, timeout));
        addSubBehaviour(new BuyerReceiveBehaviour());
    }
}
