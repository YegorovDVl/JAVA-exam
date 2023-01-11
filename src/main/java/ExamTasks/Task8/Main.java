package ExamTasks.Task8;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.leap.Properties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("gui", "false");
        properties.setProperty("agents", "Informator:ExamTasks.Task8.InformationAgent;Root1:ExamTasks.Task8.MinusRootAgent;" +
                "Discriminant:ExamTasks.Task8.DiscriminantAgent;Root2:ExamTasks.Task8.PlusRootAgent");
        properties.setProperty("services", "jade.core.messaging.TopicManagementService");
        ProfileImpl p = new ProfileImpl(properties);
        Runtime.instance().setCloseVM(true);
        Runtime.instance().createMainContainer(p);
    }
}
