/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.sniffer;

import GUI.MainWindow;
import java.io.IOException;
import javax.swing.JOptionPane;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;

/**
 *
 * @author Asim
 */
public class NetworkDevice {
    JpcapCaptor captor;
    NetworkInterface[] devices;
    MainWindow mw;

    public void getNetworkDevices() throws IOException {
        
        devices = JpcapCaptor.getDeviceList();

//for each network interface
        for (int i = 0; i < devices.length; i++) {
            //print out its name and description
            System.out.println(i + ": " + devices[i].name + "(" + devices[i].description + ")");

            //print out its datalink name and description
            System.out.println(" datalink: " + devices[i].datalink_name + "(" + devices[i].datalink_description + ")");

            //print out its MAC address
            System.out.print(" MAC address:");
            for (byte b : devices[i].mac_address) {
                System.out.print(Integer.toHexString(b & 0xff) + ":");
            }
            System.out.println();

            //print out its IP address, subnet mask and broadcast address
            for (NetworkInterfaceAddress a : devices[i].addresses) {
                System.out.println(" address:" + a.address + " " + a.subnet + " " + a.broadcast);
            }
            
            

        }
        for (NetworkInterface device : devices) {
            mw.networkDevices.addItem(device.name);
        }

    }
    
    public void openNetworkDevice(int device_num) throws IOException
    {
        devices = JpcapCaptor.getDeviceList();
        JpcapCaptor.openDevice(devices[device_num],1028,false, 20);
        JOptionPane.showMessageDialog(null,"Opened!\n"+devices[device_num].name+" has been opened.\n For capturing, press start","Network Device", JOptionPane.INFORMATION_MESSAGE);
   
    
    }
    


}
