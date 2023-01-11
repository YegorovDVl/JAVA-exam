package ExamTasks.Task3;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyerOneShotBehaviour extends OneShotBehaviour {
    @Override
    public void action() {
        log.info(getAgent().getLocalName()+" sending request");
        ACLMessage request =  new ACLMessage(ACLMessage.REQUEST);
        request.setContent("Request");
        request.addReceiver(new AID("Seller", false));
        getAgent().send(request);
    }
}
