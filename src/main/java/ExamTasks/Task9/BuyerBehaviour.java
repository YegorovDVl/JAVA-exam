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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class BuyerBehaviour extends Behaviour {
    private final BooksCFG cfg;
    private boolean end = false;
    private int booksToBuy;
    private int counter = 0;
    private int answers = 0;
    private List<AID> agents;
    private ArrayList<String> Books = new ArrayList<>();
    private final HashMap<String, Integer> BooksPrice = new HashMap<>();
    private final HashMap<String, String> BooksSeller =  new HashMap<>();
    public BuyerBehaviour(BooksCFG cfg){
        this.cfg = cfg;
    }
    @Override
    public void onStart() {
        agents = new ArrayList<>();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Seller");
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
        Books = cfg.getBooksToBuy();
        for (String book: Books) {
            BooksPrice.put(book, Integer.MAX_VALUE);
            BooksSeller.put(book, "-");
        }
        log.info(getAgent().getLocalName()+" want to buy books: "+Books.toString());
        booksToBuy = Books.size();
        answers = agents.size();
    }

    @Override
    public void action() {
        if(counter < booksToBuy){
            if(answers == agents.size()){
                ACLMessage bookRequest =  new ACLMessage(ACLMessage.REQUEST);
                bookRequest.setContent(Books.get(counter));
                for (AID agent: agents) {
                    bookRequest.addReceiver(agent);
                }
                getAgent().send(bookRequest);
                answers = 0;
            }
            ACLMessage bookAnswer = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM));
            if (bookAnswer != null){
                log.info("Got answer from "+bookAnswer.getSender().getLocalName());
                int price = Integer.parseInt(bookAnswer.getContent());
                if((price < BooksPrice.get(Books.get(counter)))&& price != 0){
                    BooksPrice.put(Books.get(counter), price);
                    BooksSeller.put(Books.get(counter), bookAnswer.getSender().getLocalName());
                }
                answers++;
                if(answers == agents.size()){
                    counter++;
                }
            } else {
                block();
            }
        } else{
            end = true;
        }
    }

    @Override
    public boolean done() {
        if(end){
            for (String book: Books) {
                if(!BooksSeller.get(book).equals("-")){
                    log.info("Book "+book+" with price "+BooksPrice.get(book)+" from seller "+BooksSeller.get(book));
                } else{
                    log.info("Can't buy book "+book+ " from any seller");
                }
            }
        }
        return end;
    }
}
