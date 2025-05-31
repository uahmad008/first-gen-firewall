package voila.firewall;

/**
 *
 * @author M-Umair
 */
public class PolicyRule {

    String upper_source_ip;
    String lower_source_ip;
    String destination_ip;
    int upper_source_port;
    int upper_destination_port;
    int lower_source_port;
    int lower_destination_port;
    String protocol;
    int action;

    public PolicyRule() 
    {
        this.upper_source_ip="";
        this.lower_source_ip="";
        this.destination_ip="";
        this.upper_source_port=0;
        this.upper_destination_port=0;
        this.lower_source_port=0;
        this.lower_destination_port=0;
        this.protocol="";
        this.action=0;
    }

     public void setUpperSourceIP(String sourceip)
    {
        this.upper_source_ip=sourceip;
    }
    public void setLowerSourceIP(String lower_source_ip) 
    {
        this.lower_source_ip = lower_source_ip;
    }
    public void setDestinationIP(String destinationip)
    {
        this.destination_ip=destinationip;
    }
    public void setUpperSourcePort(int upper_sourceport)
    {
        this.upper_source_port=upper_sourceport;
    }
    public void setLowerSourcePort(int lower_sourceport)
    {
        this.lower_source_port=lower_sourceport;
    }
    public void setUpperDestinationPort(int upper_destinationport)
    {
        this.upper_destination_port=upper_destinationport;
    }
     public void setLowerDestinationPort(int lower_destinationport)
    {
        this.lower_destination_port=lower_destinationport;
    }
    public void setProtocol(String protocol)
    {
        this.protocol=protocol;
    }
    public void setAction(int action)
    {
        this.action=action;
    }
    
    
    public String getUpperSourceIP()
    {
        return this.upper_source_ip;
    }
    public String getLowerSourceIP()
    {
        return this.lower_source_ip;
    }
    public String getDestinationIP()
    {
      return this.destination_ip;  
    }
    public int getUpperSourcePort()
    {
        return this.upper_source_port;  
    }
      public int getLowerSourcePort()
    {
        return this.lower_source_port;  
    }
    public int getUpperDestinationPort()
    {
        return this.upper_destination_port;  
    }
    public int getLowerDestinationPort()
    {
        return this.lower_destination_port;  
    }
    public String getProtocol()
    {
        return this.protocol;
    }
    public int getAction()
    {
        return this.action;
    }
        
    public void applyPolicyRule(String src , String dst)
    {
        System.out.println(src);
        System.out.println(dst);
        
        
    }
}