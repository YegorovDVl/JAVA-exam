package ExamTasks.Task9;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class SellerBehaviour extends Behaviour {
    private final BooksCFG cfg;
    private final HashMap<String, Integer> Books = new HashMap<>();
    public SellerBehaviour(BooksCFG cfg){
        this.cfg = cfg;
    }

    @Override
    public void onStart() {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAgent().getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Seller");
        sd.setName(getAgent().getLocalName());
        dfd.addServices(sd);
        try {
            DFService.register(getAgent(), dfd);
        } catch (
                FIPAException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < 3; i++){
            int price = (int) (Math.random()*200 + 200);
            Books.put(cfg.getBook(i),price);
        }
        if(cfg.getStatus()){
            int index = (int) (Math.random()*3);
            Books.remove(cfg.getBook(index));
        }
        log.info(getAgent().getLocalName()+" got books: "+Books.toString());
    }

    @Override
    public void action() {
        ACLMessage bookRequest = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (bookRequest != null){
            log.info("Got request from "+bookRequest.getSender().getLocalName());
            ACLMessage bookAnswer =  new ACLMessage(ACLMessage.CONFIRM);
            if(Books.containsKey(bookRequest.getContent())){
                log.info(getAgent().getLocalName()+" have got book "+bookRequest.getContent());
                bookAnswer.setContent(String.valueOf(Books.get(bookRequest.getContent())));
            } else{
                log.info(getAgent().getLocalName()+" haven't got book "+bookRequest.getContent());
                bookAnswer.setContent("0");
            }
            bookAnswer.addReceiver(bookRequest.getSender());
            getAgent().send(bookAnswer);
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
