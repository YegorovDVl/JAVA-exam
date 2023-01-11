package ExamTasks.Task8;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.messaging.TopicManagementHelper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class InformationAgent extends Agent {
    PolynomCFG cfg;
    @Override
    protected void setup() {
        String topic_name = "Solving";
        TopicManagementHelper topicHelper;
        AID jadeTopic = null;
        try {
            topicHelper = (TopicManagementHelper)
                    this.getHelper(TopicManagementHelper.SERVICE_NAME);
            jadeTopic = topicHelper.createTopic(topic_name);
            topicHelper.register(jadeTopic);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        try {
            JAXBContext context = JAXBContext.newInstance(PolynomCFG.class);
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
            cfg = (PolynomCFG) jaxbUnmarshaller.unmarshal(new File("src/main/java/ExamTasks/Task8/Polynom.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        addBehaviour(new InformationAgentBehaviour(cfg, jadeTopic));
    }
}
