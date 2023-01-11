package ExamTasks.Task6;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientBehaviour extends Behaviour {
    int workTime = 5;
    AID topic;
    boolean end = false;
    public ClientBehaviour(AID topic){
        this.topic = topic;
    }
    @Override
    public void onStart() {
        log.info("Got work request for "+workTime+" seconds");
        ACLMessage request =  new ACLMessage(ACLMessage.REQUEST);
        request.setContent(String.valueOf(workTime));
        request.addReceiver(topic);
        getAgent().send(request);
    }

    @Override
    public void action() {
        ACLMessage progress = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
        if (progress != null){
            log.info("Work progress: "+progress.getContent());
            if(progress.getContent().equals("100%")){
                end = true;
            }
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        if(end){
            ACLMessage endWork =  new ACLMessage(ACLMessage.CONFIRM);
            endWork.setContent("0");
            endWork.addReceiver(topic);
            getAgent().send(endWork);
        }
        return end;
    }
}
