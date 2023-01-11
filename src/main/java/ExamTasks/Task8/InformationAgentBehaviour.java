package ExamTasks.Task8;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class InformationAgentBehaviour extends Behaviour {
    PolynomCFG cfg;
    int counter = 0;
    AID topic;
    Set<Float> roots =  new HashSet<>();
    public InformationAgentBehaviour(PolynomCFG cfg, AID topic){
        this.topic = topic;
        this.cfg = cfg;
    }

    @Override
    public void onStart() {
        float a = cfg.getA();
        float b = cfg.getB();
        float c = cfg.getC();
        log.info("I've got cfs: a - "+a+"; b - "+b+"; c - "+c);
        ACLMessage polynom = new ACLMessage(ACLMessage.INFORM);
        polynom.setContent(a+";"+b+";"+c);
        polynom.addReceiver(topic);
        getAgent().send(polynom);
    }

    @Override
    public void action() {
        ACLMessage root = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.AGREE));
        if (root != null){
            counter ++;
            roots.add(Float.parseFloat(root.getContent()));
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        if (counter == 2){
            log.info("Roots: "+roots.toString());
        }
        return counter == 2;
    }
}
