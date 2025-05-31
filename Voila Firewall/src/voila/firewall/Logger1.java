package voila.firewall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import jpcap.JpcapCaptor;
import jpcap.packet.Packet;

/**
 *
 * @author M-Umair
 */
public class Logger1 {

    BufferedWriter bw; 
    File file;
    FileWriter fw;
    PolicyRuleHandler handle;
    public Logger1()
    {
        
    }

    void createlogs(Packet packet) throws FileNotFoundException, IOException 
    {
        file = new File("LogFile.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println(" File created in the current directory");
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Logger1.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        String data = packet.toString();

        try {
            bw = new BufferedWriter(new FileWriter(file, true));

            bw.write(data);
            bw.newLine();
        } 
        catch (IOException ioe) 
        {
            System.err.println("IOException: " + ioe.getMessage());
        }

        bw.close();
    }

    public void readFile(String fileName) throws FileNotFoundException, IOException {
        int count1 = 0;
        String[] p_log_data;
        Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            line = line.replaceAll("[/;>-]", " ");
            p_log_data = line.split("\\s+");

            for (String count : p_log_data) 
            {
                count1++;
            }
            if (count1 > 10) {

                System.out.println(p_log_data[0]);
                System.out.println(p_log_data[1]);
                System.out.println(p_log_data[2]);
                System.out.println(p_log_data[8]);
                System.out.println(p_log_data[9]);
                System.out.println(p_log_data[10]);

            } 
            else if (count1 == 10) 
            {
                System.out.println(p_log_data[0]);
                System.out.println(p_log_data[1]);
                System.out.println(p_log_data[2]);
                System.out.println(p_log_data[7]);
                System.out.println(p_log_data[8]);
                System.out.println(p_log_data[9]);
            }
            count1 = 0;

        }

    }

    public void dumpFileReader(String file)
    {
        try {
            //open a file to read saved packets
 
                JpcapCaptor captor=JpcapCaptor.openFile(file);
                while(true)
                {
                //read a packet from the opened file
                Packet packet=captor.getPacket();
                //if some error occurred or EOF has reached, break the loop
                if(packet==null || packet==Packet.EOF)
                        break;
                //System.out.println(packet);
               
                parseDumpfile(packet);
                             
            }
            captor.close();
        } 
        catch (IOException ex) 
        {
            java.util.logging.Logger.getLogger(Logger1.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    void parseDumpfile(Packet packet)
    {
        String temp[];
        int count1 = 0;
        String info = packet.toString();              
                if(info.charAt(0) == 'A')
                {
                    //System.out.println(info);
                    info = info.replaceAll("[)(/>-]"," ");
                    temp = info.split("\\s+");
                    
                    System.out.println(temp[0]+ " " + temp[1]+ " " + temp[2]+ " "+temp[3]+ " "+temp[4]+ " "+temp[5]);
                    
                }
        
        info = info.replaceAll("[/;>-]", " ");
        temp = info.split("\\s+");
                              
                for (String count : temp) 
                {
                count1++;
                }
                 
                if (count1 > 10) 
                {
                System.out.println(temp[0] + " "+ temp[1]+ " "+temp[2]+ " "+temp[3]+ " "+temp[4]+ " "+temp[5]+ " "+temp[6]+ " "+temp[7]+ " "+temp[8]+ " "+temp[9]+ " "+temp[10]);
                
                } 
                else if (count1 == 10) 
                {
                System.out.println(temp[0]+ " " +temp[1]+ " "+temp[2]+ " "+temp[3]+ " "+temp[4]+ " "+temp[5]+ " "+temp[6]+ " "+temp[7]+ " "+temp[8]+ " "+temp[9]);
                }

                 
    }
    
}