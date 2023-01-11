package ExamTasks.Task7;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.leap.Properties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("gui","false");
        properties.setProperty("agents","Buyer1:ExamTasks.Task7.BuyerAgent;" +
                "Seller1:ExamTasks.Task7.SellerAgent;Seller2:ExamTasks.Task7.SellerAgent;Seller3:ExamTasks.Task7.SellerAgent");
        properties.setProperty("services", "jade.core.messaging.TopicManagementService");
        ProfileImpl p = new ProfileImpl(properties);
        Runtime.instance().setCloseVM(true);
        Runtime.instance().createMainContainer(p);
    }
}