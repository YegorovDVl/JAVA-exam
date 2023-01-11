package ExamTasks.Task2;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyerTickerBehaviour extends TickerBehaviour {
    public BuyerTickerBehaviour(Agent a, long timeout){
        super(a, timeout);
    }

    @Override
    public void onStart() {
    }

    @Override
    protected void onTick() {
        log.info(getAgent().getLocalName()+" sending request");
        ACLMessage request =  new ACLMessage(ACLMessage.REQUEST);
        request.setContent("Request");
        request.addReceiver(new AID("Seller", false));
        getAgent().send(request);
    }
}
