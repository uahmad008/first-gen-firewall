package voila.firewall;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author M-Umair
 */
public class DatabaseHandler {

    Connection connection;
    ResultSet resultset;
    ResultSet resultset_to_check;
    PolicyRule policyRule;

    public DatabaseHandler() throws ClassNotFoundException, SQLException {
        policyRule = new PolicyRule();

        try {
            Class.forName("com.mysql.jdbc.Driver"); //Loading driver
            String url = "jdbc:mysql://localhost:3306/firewall"; //Defining URL with database name
            String username = "root";
            String password = "";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Connection not established!");
        }

    }

    public boolean addPolicyRule(PolicyRule policyRule) throws SQLException, ClassNotFoundException {
        boolean flag = true;

        policyRule.getUpperSourceIP();
        policyRule.getLowerSourceIP();
        policyRule.getDestinationIP();
        policyRule.getUpperSourcePort();
        policyRule.getLowerSourcePort();
        policyRule.getUpperDestinationPort();
        policyRule.getLowerDestinationPort();
        policyRule.getProtocol();
        policyRule.getAction();

        String query_to_check = "SELECT `upper_source_ip`,`lower_source_ip`, `destination_ip`, `upper_source_port`, `lower_source_port`, `upper_destination_port`, `lower_destination_port`, `protocol`, `action` FROM `policy rule` ";
        PreparedStatement perps = connection.prepareStatement(query_to_check);

        resultset_to_check = perps.executeQuery();
        while (resultset_to_check.next()) {
            String src_ip = resultset_to_check.getString("upper_source_ip");
            String lw_src_ip = resultset_to_check.getString("lower_source_ip");
            String dst_ip = resultset_to_check.getString("destination_ip");
            int src_port = resultset_to_check.getInt("upper_source_port");
            int lower_src_port = resultset_to_check.getInt("lower_source_port");
            int dst_port = resultset_to_check.getInt("upper_destination_port");
            int lower_dst_port = resultset_to_check.getInt("lower_destination_port");
            String protocol = resultset_to_check.getString("protocol");
            int action = resultset_to_check.getInt("action");

            String[] up_src_ips = policyRule.getUpperSourceIP().split("\\.");
            String[] ips = src_ip.split("\\.");        //rule upper src ip
            String[] lips = lw_src_ip.split("\\.");    //rule lower src ip
            //rule upper ips split
            int ips0 = Integer.parseInt(ips[0]);
            int ips1 = Integer.parseInt(ips[1]);
            int ips2 = Integer.parseInt(ips[2]);
            int ips3 = Integer.parseInt(ips[3]);
            //rule lower ips split
            int lips0 = Integer.parseInt(lips[0]);
            int lips1 = Integer.parseInt(lips[1]);
            int lips2 = Integer.parseInt(lips[2]);
            int lips3 = Integer.parseInt(lips[3]);

            int uips0 = Integer.parseInt(up_src_ips[0]);
            int uips1 = Integer.parseInt(up_src_ips[1]);
            int uips2 = Integer.parseInt(up_src_ips[2]);
            int uips3 = Integer.parseInt(up_src_ips[3]);

            if (src_ip.equals("0.0.0.0")) {
                if (src_port == 0) {
                    if (dst_port == 0) {
                        if (protocol.equals(policyRule.getProtocol())) {
                            if (action == policyRule.getAction()) {
                                flag = false;
                                break;
                            }
                        }
                    }
                }
            } else if (src_ip.equals(policyRule.getUpperSourceIP())) {
                if (lw_src_ip.equals(policyRule.getLowerSourceIP())) {
                    if (dst_ip.equals(policyRule.getDestinationIP())) {
                        if (src_port == policyRule.getUpperSourcePort()) {
                            if (lower_src_port == policyRule.getLowerSourcePort()) {
                                if (dst_port == policyRule.getUpperDestinationPort()) {
                                    if (lower_dst_port == policyRule.getLowerDestinationPort()) {
                                        if (protocol.equals(policyRule.getProtocol())) {
                                            if (policyRule.getAction() == 1 || policyRule.getAction() == 0) {
                                                flag = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (ips0 == uips0 && ips1 == uips1 && ips2 == uips2) //yahan se start krna h
            {
                if (ips3 <= uips3 && lips3 >= uips3) {
                    if (dst_ip.equals(policyRule.getDestinationIP())) {
                        if (src_port < policyRule.getUpperSourcePort() && lower_src_port > policyRule.getUpperSourcePort()) {

                            if (dst_port < policyRule.getUpperDestinationPort() && lower_dst_port > policyRule.getUpperDestinationPort()) {
                                if (protocol.equals(policyRule.getProtocol())) {
                                    if (action == policyRule.getAction()) {
                                        flag = false;
                                        break;
                                    }

                                }
                            }
                        }
                    }
                }

            }

        }

        if (flag) {
            String query = "INSERT INTO `policy rule`(`upper_source_ip`, `lower_source_ip`, `destination_ip`, `upper_source_port`, `lower_source_port`, `upper_destination_port`, `lower_destination_port`, `protocol`, `action`) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, policyRule.getUpperSourceIP());
            ps.setString(2, policyRule.getLowerSourceIP());
            ps.setString(3, policyRule.getDestinationIP());
            ps.setInt(4, policyRule.getUpperSourcePort());
            ps.setInt(5, policyRule.getLowerSourcePort());
            ps.setInt(6, policyRule.getUpperDestinationPort());
            ps.setInt(7, policyRule.getLowerDestinationPort());
            ps.setString(8, policyRule.getProtocol());
            ps.setInt(9, policyRule.getAction());
            int i = ps.executeUpdate();
            if (i == 1) {

//                connection.close();
                return true;
            } else {

//                connection.close();
                return false;
            }
        } else {
//            connection.close();
            return false;
        }

    }

    public boolean editPolicyRule(int id, PolicyRule policyRule) throws SQLException, ClassNotFoundException {
        boolean flag = true;

        policyRule.getUpperSourceIP();
        policyRule.getLowerSourceIP();
        policyRule.getDestinationIP();
        policyRule.getUpperSourcePort();
        policyRule.getLowerSourcePort();
        policyRule.getUpperDestinationPort();
        policyRule.getLowerDestinationPort();
        policyRule.getProtocol();
        policyRule.getAction();

        String query_to_check = "SELECT `upper_source_ip`,`lower_source_ip`, `destination_ip`, `upper_source_port`, `lower_source_port`, `upper_destination_port`, `lower_destination_port`, `protocol`, `action` FROM `policy rule` ";
        PreparedStatement perps = connection.prepareStatement(query_to_check);

        resultset_to_check = perps.executeQuery();
        while (resultset_to_check.next()) {
            String src_ip = resultset_to_check.getString("upper_source_ip");
            String lw_src_ip = resultset_to_check.getString("lower_source_ip");
            String dst_ip = resultset_to_check.getString("destination_ip");
            int src_port = resultset_to_check.getInt("upper_source_port");
            int lower_src_port = resultset_to_check.getInt("upper_source_port");
            int dst_port = resultset_to_check.getInt("upper_destination_port");
            int lower_dst_port = resultset_to_check.getInt("upper_destination_port");
            String protocol = resultset_to_check.getString("protocol");
            int action = resultset_to_check.getInt("action");

            String[] up_src_ips = policyRule.getUpperSourceIP().split("\\.");
            String[] ips = src_ip.split("\\.");        //rule upper src ip
            String[] lips = lw_src_ip.split("\\.");    //rule lower src ip
            //rule upper ips split
            int ips0 = Integer.parseInt(ips[0]);
            int ips1 = Integer.parseInt(ips[1]);
            int ips2 = Integer.parseInt(ips[2]);
            int ips3 = Integer.parseInt(ips[3]);
            //rule lower ips split
            int lips0 = Integer.parseInt(lips[0]);
            int lips1 = Integer.parseInt(lips[1]);
            int lips2 = Integer.parseInt(lips[2]);
            int lips3 = Integer.parseInt(lips[3]);

            int uips0 = Integer.parseInt(up_src_ips[0]);
            int uips1 = Integer.parseInt(up_src_ips[1]);
            int uips2 = Integer.parseInt(up_src_ips[2]);
            int uips3 = Integer.parseInt(up_src_ips[3]);

            if (src_ip.equals("0.0.0.0")) {
                if (src_port == 0) {
                    if (dst_port == 0) {
                        if (protocol.equals(policyRule.getProtocol())) {
                            if (action == policyRule.getAction()) {
                                flag = false;
                                break;
                            }
                        }
                    }
                }
            } else if (src_ip.equals(policyRule.getUpperSourceIP())) {
                if (lw_src_ip.equals(policyRule.getLowerSourceIP())) {
                    if (dst_ip.equals(policyRule.getDestinationIP())) {
                        if (src_port == policyRule.getUpperSourcePort()) {
                            if (lower_src_port == policyRule.getLowerSourcePort()) {
                                if (dst_port == policyRule.getUpperDestinationPort()) {
                                    if (lower_dst_port == policyRule.getLowerDestinationPort()) {
                                        if (protocol.equals(policyRule.getProtocol())) {
                                            if (policyRule.getAction() == 1 || policyRule.getAction() == 0) {
                                                flag = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (ips0 == uips0 && ips1 == uips1 && ips2 == uips2) //yahan se start krna h
            {
                if (ips3 <= uips3 && lips3 >= uips3) {
                    if (dst_ip.equals(policyRule.getDestinationIP())) {
                        if (src_port < policyRule.getUpperSourcePort() && lower_src_port > policyRule.getUpperSourcePort()) {

                            if (dst_port < policyRule.getUpperDestinationPort() && lower_dst_port > policyRule.getUpperDestinationPort()) {
                                if (protocol.equals(policyRule.getProtocol())) {
                                    if (action == policyRule.getAction()) {
                                        flag = false;
                                        break;
                                    }

                                }
                            }
                        }
                    }
                }

            }

        }

        if (flag) {
            String query = "UPDATE  `policy rule` set upper_source_ip = ? ,lower_source_ip=?, destination_ip = ? , upper_source_port=?, lower_source_port=? , upper_destination_port = ?, lower_destination_port = ? , protocol = ?, action = ? where policy_number = ? ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, policyRule.getUpperSourceIP());
            ps.setString(2, policyRule.getLowerSourceIP());
            ps.setString(3, policyRule.getDestinationIP());
            ps.setInt(4, policyRule.getUpperSourcePort());
            ps.setInt(5, policyRule.getLowerSourcePort());
            ps.setInt(6, policyRule.getUpperDestinationPort());
            ps.setInt(7, policyRule.getLowerDestinationPort());
            ps.setString(8, policyRule.getProtocol());
            ps.setInt(9, policyRule.getAction());
            ps.setInt(10, id);
            int i = ps.executeUpdate();
            if (i == 1) {

                //connection.close();
                return true;
            } else {

                //connection.close();
                return false;
            }

        } else {
            //connection.close();
            return false;
        }

    }

    public boolean deletePolicyRule(int id) throws ClassNotFoundException, SQLException {
        String query = "DELETE FROM `policy rule` WHERE `policy rule`.`policy_number` = ? ";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        int i = ps.executeUpdate();
        if (i == 1) {
//            connection.close();
            return true;
        } else {

//            connection.close();
            return false;
        }

    }

    public void insertPakcets() throws FileNotFoundException, SQLException {
        int count1 = 0;
        String[] p_log_data;
        Scanner sc = new Scanner(new BufferedReader(new FileReader("packets.txt")));
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            line = line.replaceAll("[/;>-]", " ");
            p_log_data = line.split("\\s+");

            for (String count : p_log_data) {
                count1++;
            }
            if (count1 > 10) {

                String query = "INSERT INTO `packet`(`source_ip`, `destination_ip`, `source_port`, `destination_port`, `protocol`,`timestamp` ) VALUES(?,?,?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, p_log_data[1]);
                ps.setString(2, p_log_data[2]);
                ps.setInt(3, Integer.parseInt(p_log_data[9]));
                ps.setInt(4, Integer.parseInt(p_log_data[10]));
                ps.setString(5, p_log_data[8]);
                ps.setString(6, p_log_data[0]);
                int i = ps.executeUpdate();

            } else if (count1 == 10) {

                String query = "INSERT INTO `packets`(`source_ip`, `destination_ip`, `source_port`, `destination_port`, `protocol`,`timestamp` ) VALUES(?,?,?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, p_log_data[1]);
                ps.setString(2, p_log_data[2]);
                ps.setInt(3, Integer.parseInt(p_log_data[8]));
                ps.setInt(4, Integer.parseInt(p_log_data[9]));
                ps.setString(5, p_log_data[7]);
                ps.setString(6, p_log_data[0]);
                int i = ps.executeUpdate();

            }
            count1 = 0;

        }
        connection.close();
    }

    public PolicyRule search(int id1) throws SQLException {
        String query = "SELECT `upper_source_ip`,`lower_source_ip`, `destination_ip`, `upper_source_port`, `lower_source_port`, `upper_destination_port`, `lower_source_port`, `protocol`, `action` FROM `policy rule` WHERE `policy_number`=? ";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id1);
        resultset = ps.executeQuery();
        while (resultset.next()) {
            String src_ip = resultset.getString("upper_source_ip");
            String lw_src_ip = resultset.getString("lower_source_ip");
            String dst_ip = resultset.getString("destination_ip");
            int src_port = resultset.getInt("upper_source_port");
            int lower_src_port = resultset.getInt("upper_source_port");
            int dst_port = resultset.getInt("upper_destination_port");
            int lower_dst_port = resultset.getInt("upper_destination_port");
            String protocol = resultset.getString("protocol");
            int action = resultset.getInt("action");
            policyRule.setUpperSourceIP(src_ip);
            policyRule.setLowerSourceIP(lw_src_ip);
            policyRule.setDestinationIP(dst_ip);
            policyRule.setUpperSourcePort(src_port);
            policyRule.setLowerSourcePort(lower_src_port);
            policyRule.setUpperDestinationPort(dst_port);
            policyRule.setLowerDestinationPort(lower_dst_port);
            policyRule.setProtocol(protocol);
            policyRule.setAction(action);
            connection.close();
            return policyRule;
        }
        return null;

    }

}
