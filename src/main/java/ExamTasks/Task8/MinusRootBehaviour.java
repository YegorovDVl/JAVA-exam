package ExamTasks.Task8;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MinusRootBehaviour extends Behaviour {
    AID topic;

    public MinusRootBehaviour(AID topic){
        this.topic = topic;
    }
    @Override
    public void action() {
        ACLMessage discriminant = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM));
        if (discriminant != null){
            String[] coeffs = discriminant.getContent().split(";");
            float a = Float.parseFloat(coeffs[0]);
            float b = Float.parseFloat(coeffs[1]);
            float rootD = Float.parseFloat(coeffs[2]);
            float minusRoot = (-b - rootD) / (2 * a);
            log.info(getAgent().getLocalName()+": "+minusRoot);
            ACLMessage root = new ACLMessage(ACLMessage.AGREE);
            root.setContent(String.valueOf(minusRoot));
            root.addReceiver(topic);
            getAgent().send(root);
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
