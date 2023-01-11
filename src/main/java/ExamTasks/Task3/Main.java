package ExamTasks.Task3;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.leap.Properties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("gui","false");
        properties.setProperty("agents","Buyer:ExamTasks.Task3.BuyerAgent;" +
                "Seller:ExamTasks.Task3.SellerAgent");
        ProfileImpl p = new ProfileImpl(properties);
        Runtime.instance().setCloseVM(true);
        Runtime.instance().createMainContainer(p);
    }
}
