package ExamTasks.Task2;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SellerAgentBehaviour extends Behaviour {
    @Override
    public void action() {
        ACLMessage request = getAgent().receive();
        if (request != null){
            int toAnswer = (int) (Math.random()*2);
            if (toAnswer == 1){
                log.info(getAgent().getLocalName()+" answering");
                int confirmation = (int) (Math.random()*2);
                ACLMessage answer =  new ACLMessage(ACLMessage.CONFIRM);
                answer.addReceiver(request.getSender());
                if(confirmation == 1){
                    answer.setContent("1");
                } else {
                    answer.setContent("0");
                }
                getAgent().send(answer);
            } else {
                log.info(getAgent().getLocalName()+" ignoring");
            }
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
