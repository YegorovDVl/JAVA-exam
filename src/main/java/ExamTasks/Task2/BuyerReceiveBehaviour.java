package ExamTasks.Task2;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyerReceiveBehaviour extends Behaviour {
    @Override
    public void action() {
        ACLMessage answer = getAgent().receive();
        if (answer != null){
            if(answer.getContent().equals("1")){
                log.info(getAgent().getLocalName()+" got confirmation from "+answer.getSender().getLocalName());
            } else {
                log.info(getAgent().getLocalName()+" got deny from "+answer.getSender().getLocalName());
            }
            getAgent().doDelete();
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
