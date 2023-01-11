package ExamTasks.Task6;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkerStartBehaviour extends Behaviour {
    AID topic;
    boolean end = false;
    public WorkerStartBehaviour(AID topic){
        this.topic = topic;
    }
    @Override
    public void action() {
        ACLMessage request = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (request != null){
            ACLMessage progress =  new ACLMessage(ACLMessage.INFORM);
            progress.setContent("0%");
            progress.addReceiver(topic);
            getAgent().send(progress);
            String workValue = request.getContent();
            int workTime = Integer.parseInt(workValue)*100;
            getAgent().addBehaviour(new WorkerProgressBehaviour(getAgent(), workTime, topic));
        } else {
            block();
        }
        ACLMessage endWork = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM));
        if (endWork != null){
            log.info(getAgent().getLocalName()+" completed work with confirmation from "+endWork.getSender().getLocalName());
            end = true;
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return end;
    }
}
