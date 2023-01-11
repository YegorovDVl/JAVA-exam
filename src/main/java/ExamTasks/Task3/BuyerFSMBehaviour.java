package ExamTasks.Task3;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;

public class BuyerFSMBehaviour extends FSMBehaviour {
    public BuyerFSMBehaviour(Agent a, long timeout){
        registerFirstState(new BuyerOneShotBehaviour(), "Request");
        registerLastState(new BuyerWakerBehaviour(a, timeout), "Receive");
        registerDefaultTransition("Request","Receive");
    }
}
