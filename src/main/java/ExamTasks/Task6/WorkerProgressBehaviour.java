package ExamTasks.Task6;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkerProgressBehaviour extends TickerBehaviour {
    int timeCounter = 0;
    AID topic;
    public WorkerProgressBehaviour(Agent a, long period, AID topic){
        super(a, period);
        this.topic = topic;
    }


    @Override
    protected void onTick() {
        timeCounter+=10;
        if(timeCounter <= 100){
            ACLMessage progress =  new ACLMessage(ACLMessage.INFORM);
            progress.setContent(timeCounter+"%");
            progress.addReceiver(topic);
            getAgent().send(progress);
        } else {
            getAgent().removeBehaviour(this);
        }
    }
}
