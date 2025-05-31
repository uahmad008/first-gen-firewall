package voila.firewall;

import Gui.MainWindow;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author M-Umair
 */
public class VoilaFirewall {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException  {
//        PolicyRuleHandler prh = new PolicyRuleHandler();
//        FirewallHandler firewallHandler = new FirewallHandler();
 
//        firewallHandler.start();


MainWindow mw=new MainWindow();
mw.setVisible(true); 
    
    }
}