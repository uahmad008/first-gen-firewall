    package voila.firewall;

import java.io.IOException;
import java.sql.SQLException;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.packet.Packet;

/**
 *
 * @author M-Umair
 */
public class PacketCapturer {

    JpcapCaptor captor;
    //MainWindow mw;
    Logger1 logger;
    static NetworkInterface[] devices;

    public PacketCapturer() throws ClassNotFoundException, SQLException {
        //mw=new MainWindow();
        logger=new Logger1();
    }
    public void start() throws IOException
    {
        this.getInterfaces();
        this.openNetworkDevice(1);
        //mw.setVisible(true);
        this.startCapturing();
    }

    public void getInterfaces() {
        devices = JpcapCaptor.getDeviceList();
    }

    public void openNetworkDevice(int num) throws IOException {
        captor = JpcapCaptor.openDevice(devices[num], 65535, false, 20);

    }

    public void startCapturing() throws IOException {
//        Logger createlog = new Logger();

        Packet packet = null;

        String captured_packet;
        while (true) {
            packet = captor.getPacket();
            

            if (packet != null) {
                captured_packet = packet.toString();
                char ch = captured_packet.charAt(0);

                if (ch != 'A') {

//                    File file = new File("test.txt");
//                    file.createNewFile();
//                    FileWriter writer = new FileWriter("test.txt");
//                    BufferedWriter buffer = new BufferedWriter(writer);
//                    buffer.write("Hello");
//                    buffer.close();
//                mw=new MainWindow();
//for(int i=0;i<1000;i++)
//{
//    mw.allowedArea.setText("Hello");
//    mw.blockedArea.setText("Bye");
//}

                 System.out.println(captured_packet);
                  logger.createlogs(packet);
                }

            }

        }

    }

}