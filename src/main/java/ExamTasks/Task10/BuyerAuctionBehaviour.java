package ExamTasks.Task10;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyerAuctionBehaviour extends Behaviour {
    int price;
    int auctionStep = 20;
    int currentBet;
    AID topic;
    boolean end = false;

    public BuyerAuctionBehaviour(AID topic){
        this.topic = topic;
    }
    @Override
    public void onStart() {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAgent().getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Buyer");
        sd.setName(getAgent().getLocalName());
        dfd.addServices(sd);
        try {
            DFService.register(getAgent(), dfd);
        } catch (
                FIPAException e) {
            e.printStackTrace();
        }
        price = (int) (Math.random()*200 + 200);
        currentBet = price/2;
        log.info(getAgent().getLocalName()+" got price "+price);
        ACLMessage betMessage =  new ACLMessage(ACLMessage.REQUEST);
        betMessage.setContent(String.valueOf(currentBet));
        betMessage.addReceiver(topic);
        getAgent().send(betMessage);
    }

    @Override
    public void action() {
        ACLMessage bet = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (bet != null){
            if(!bet.getSender().equals(getAgent().getAID())){
                int receivedBet = Integer.parseInt(bet.getContent());
                if((receivedBet >= currentBet)){
                    currentBet = receivedBet + auctionStep;
                    if (currentBet > price && price > receivedBet){
                        currentBet = price;
                    }
                    if(currentBet <= price){
                        log.info(getAgent().getLocalName()+" got new bet " +currentBet);
                        ACLMessage betMessage =  new ACLMessage(ACLMessage.REQUEST);
                        betMessage.setContent(String.valueOf(currentBet));
                        betMessage.addReceiver(topic);
                        getAgent().send(betMessage);
                    } else {
                        log.info(getAgent().getLocalName()+" can't bet anymore");
                        ACLMessage deny =  new ACLMessage(ACLMessage.DISCONFIRM);
                        deny.setContent("0");
                        deny.addReceiver(topic);
                        getAgent().send(deny);
                        end = true;
                    }
                }
            }

        } else {
            block();
        }
    }

    @Override
    public boolean done() {
            ACLMessage contract = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.AGREE));
            if (contract != null){
                log.info(getAgent().getLocalName() + " got confirmation from " + contract.getSender().getLocalName()+" with price "+currentBet);
                end = true;
            }
        return end;
    }
}
