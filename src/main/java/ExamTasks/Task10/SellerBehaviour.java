package ExamTasks.Task10;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SellerBehaviour extends Behaviour {
    List<AID> agents;
    AID topic;

    public SellerBehaviour(AID topic){
        this.topic = topic;
    }
    @SneakyThrows
    @Override
    public void onStart() {
        agents = new ArrayList<>();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Buyer");
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.addServices(sd);
        try {
            DFAgentDescription[] result = DFService.search(getAgent(), dfd);
            for (DFAgentDescription res : result) {
                agents.add(res.getName());
            }
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void action() {
        ACLMessage deny = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.DISCONFIRM));
        if (deny != null){
            agents.remove(deny.getSender());
        } else {
            block();
        }
        if (agents.size() == 1){
            log.info("Auction winner: "+agents.get(0).getLocalName());
            ACLMessage agree =  new ACLMessage(ACLMessage.AGREE);
            agree.setContent("0");
            agree.addReceiver(topic);
            getAgent().send(agree);
        }
    }

    @Override
    public boolean done() {
        return agents.size() == 1;
    }
}
