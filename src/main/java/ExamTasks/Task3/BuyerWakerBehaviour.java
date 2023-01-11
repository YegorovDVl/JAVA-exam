package ExamTasks.Task3;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyerWakerBehaviour extends WakerBehaviour {
    public BuyerWakerBehaviour(Agent a, long timeout) {
        super(a, timeout);
    }

    @Override
    protected void onWake() {
        ACLMessage answer = getAgent().receive();
        if (answer != null){
            if(answer.getContent().equals("1")){
                log.info(getAgent().getLocalName()+" got confirmation from "+answer.getSender().getLocalName());
            } else {
                log.info(getAgent().getLocalName()+" got deny from "+answer.getSender().getLocalName());
            }
            getAgent().doDelete();
        } else {
            log.info("Seller ignoring requests of "+getAgent().getLocalName());
            block();
        }
    }
}
