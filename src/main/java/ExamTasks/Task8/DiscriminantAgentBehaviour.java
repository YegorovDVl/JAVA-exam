package ExamTasks.Task8;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiscriminantAgentBehaviour extends Behaviour {
    AID topic;
    public DiscriminantAgentBehaviour(AID topic){
        this.topic = topic;
    }
    @Override
    public void action() {
        ACLMessage polynom = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
        if (polynom != null){
            String[] coeffs = polynom.getContent().split(";");
            float a = Float.parseFloat(coeffs[0]);
            float b = Float.parseFloat(coeffs[1]);
            float c = Float.parseFloat(coeffs[2]);
            float rootD = (float) Math.sqrt(Math.pow(b, 2) - 4 * a * c);
            log.info("Root of discriminant: "+rootD);
            ACLMessage discriminant = new ACLMessage(ACLMessage.CONFIRM);
            discriminant.setContent(a +";"+ b +";"+ rootD);
            discriminant.addReceiver(topic);
            getAgent().send(discriminant);
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
