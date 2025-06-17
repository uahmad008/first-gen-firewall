/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.sniffer;

import GUI.MainWindow;
import jpcap.PacketReceiver;
import jpcap.packet.Packet;

/**
 *
 * @author Asim
 */
public class PacketPrinter implements PacketReceiver {
    MainWindow mw;
    @Override
    public void receivePacket(Packet packet) {
    //just print out a captured packet
    
    mw.packetTextArea.setText(packet.toString());
  }
    
}
