package voila.firewall;

import Gui.MainWindow;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author M-Umair
 */
public class FirewallHandler {

    MainWindow mw;
    PacketCapturer packetCapturer;

    public FirewallHandler() throws ClassNotFoundException, SQLException 
    {
        
        mw = new MainWindow();
        packetCapturer = new PacketCapturer();
    }

    void start() throws IOException, InterruptedException {
        packetCapturer.getInterfaces();

        packetCapturer.startCapturing();
//            
//        Process process = java.lang.Runtime.getRuntime().exec("ping www.google.com"); 
//        int x = process.waitFor(); 
//        if (x == 0) { 
//            
//      
//        
//        } 
//        else { 
//            System.out.println("Internet Not Connected"); 
//        } 
        //logger.readFile("test1.txt");       
    }

}
