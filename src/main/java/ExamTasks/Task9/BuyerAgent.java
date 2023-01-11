package ExamTasks.Task9;

import jade.core.Agent;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class BuyerAgent extends Agent {
    BooksCFG cfg;
    @Override
    protected void setup() {
        try {
            JAXBContext context = JAXBContext.newInstance(BooksCFG.class);
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
            cfg = (BooksCFG) jaxbUnmarshaller.unmarshal(new File("src/main/java/ExamTasks/Task9/Books.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        addBehaviour(new BuyerWakerBehaviour(this, 500, cfg));
    }
}
