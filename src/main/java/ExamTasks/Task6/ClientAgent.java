package ExamTasks.Task6;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.messaging.TopicManagementHelper;

public class ClientAgent extends Agent {
    @Override
    protected void setup() {
        String topic_name = "Work";
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
        addBehaviour(new ClientBehaviour(jadeTopic));
    }
}
