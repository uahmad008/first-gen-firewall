/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Asim Ejaz
 */
@Entity
@Table(name = "policy rule", catalog = "firewall", schema = "")
@NamedQueries({
    @NamedQuery(name = "PolicyRule.findAll", query = "SELECT p FROM PolicyRule p")
    , @NamedQuery(name = "PolicyRule.findByPolicyNumber", query = "SELECT p FROM PolicyRule p WHERE p.policyNumber = :policyNumber")
    , @NamedQuery(name = "PolicyRule.findByUpperSourceIp", query = "SELECT p FROM PolicyRule p WHERE p.upperSourceIp = :upperSourceIp")
    , @NamedQuery(name = "PolicyRule.findByLowerSourceIp", query = "SELECT p FROM PolicyRule p WHERE p.lowerSourceIp = :lowerSourceIp")
    , @NamedQuery(name = "PolicyRule.findByDestinationIp", query = "SELECT p FROM PolicyRule p WHERE p.destinationIp = :destinationIp")
    , @NamedQuery(name = "PolicyRule.findByUpperSourcePort", query = "SELECT p FROM PolicyRule p WHERE p.upperSourcePort = :upperSourcePort")
    , @NamedQuery(name = "PolicyRule.findByLowerSourcePort", query = "SELECT p FROM PolicyRule p WHERE p.lowerSourcePort = :lowerSourcePort")
    , @NamedQuery(name = "PolicyRule.findByUpperDestinationPort", query = "SELECT p FROM PolicyRule p WHERE p.upperDestinationPort = :upperDestinationPort")
    , @NamedQuery(name = "PolicyRule.findByLowerDestinationPort", query = "SELECT p FROM PolicyRule p WHERE p.lowerDestinationPort = :lowerDestinationPort")
    , @NamedQuery(name = "PolicyRule.findByProtocol", query = "SELECT p FROM PolicyRule p WHERE p.protocol = :protocol")
    , @NamedQuery(name = "PolicyRule.findByAction", query = "SELECT p FROM PolicyRule p WHERE p.action = :action")})
public class PolicyRule implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "policy_number")
    private Integer policyNumber;
    @Basic(optional = false)
    @Column(name = "upper_source_ip")
    private String upperSourceIp;
    @Basic(optional = false)
    @Column(name = "lower_source_ip")
    private String lowerSourceIp;
    @Basic(optional = false)
    @Column(name = "destination_ip")
    private String destinationIp;
    @Basic(optional = false)
    @Column(name = "upper_source_port")
    private String upperSourcePort;
    @Basic(optional = false)
    @Column(name = "lower_source_port")
    private int lowerSourcePort;
    @Basic(optional = false)
    @Column(name = "upper_destination_port")
    private String upperDestinationPort;
    @Basic(optional = false)
    @Column(name = "lower_destination_port")
    private int lowerDestinationPort;
    @Basic(optional = false)
    @Column(name = "protocol")
    private String protocol;
    @Basic(optional = false)
    @Column(name = "action")
    private int action;

    public PolicyRule() {
    }

    public PolicyRule(Integer policyNumber) {
        this.policyNumber = policyNumber;
    }

    public PolicyRule(Integer policyNumber, String upperSourceIp, String lowerSourceIp, String destinationIp, String upperSourcePort, int lowerSourcePort, String upperDestinationPort, int lowerDestinationPort, String protocol, int action) {
        this.policyNumber = policyNumber;
        this.upperSourceIp = upperSourceIp;
        this.lowerSourceIp = lowerSourceIp;
        this.destinationIp = destinationIp;
        this.upperSourcePort = upperSourcePort;
        this.lowerSourcePort = lowerSourcePort;
        this.upperDestinationPort = upperDestinationPort;
        this.lowerDestinationPort = lowerDestinationPort;
        this.protocol = protocol;
        this.action = action;
    }

    public Integer getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(Integer policyNumber) {
        Integer oldPolicyNumber = this.policyNumber;
        this.policyNumber = policyNumber;
        changeSupport.firePropertyChange("policyNumber", oldPolicyNumber, policyNumber);
    }

    public String getUpperSourceIp() {
        return upperSourceIp;
    }

    public void setUpperSourceIp(String upperSourceIp) {
        String oldUpperSourceIp = this.upperSourceIp;
        this.upperSourceIp = upperSourceIp;
        changeSupport.firePropertyChange("upperSourceIp", oldUpperSourceIp, upperSourceIp);
    }

    public String getLowerSourceIp() {
        return lowerSourceIp;
    }

    public void setLowerSourceIp(String lowerSourceIp) {
        String oldLowerSourceIp = this.lowerSourceIp;
        this.lowerSourceIp = lowerSourceIp;
        changeSupport.firePropertyChange("lowerSourceIp", oldLowerSourceIp, lowerSourceIp);
    }

    public String getDestinationIp() {
        return destinationIp;
    }

    public void setDestinationIp(String destinationIp) {
        String oldDestinationIp = this.destinationIp;
        this.destinationIp = destinationIp;
        changeSupport.firePropertyChange("destinationIp", oldDestinationIp, destinationIp);
    }

    public String getUpperSourcePort() {
        return upperSourcePort;
    }

    public void setUpperSourcePort(String upperSourcePort) {
        String oldUpperSourcePort = this.upperSourcePort;
        this.upperSourcePort = upperSourcePort;
        changeSupport.firePropertyChange("upperSourcePort", oldUpperSourcePort, upperSourcePort);
    }

    public int getLowerSourcePort() {
        return lowerSourcePort;
    }

    public void setLowerSourcePort(int lowerSourcePort) {
        int oldLowerSourcePort = this.lowerSourcePort;
        this.lowerSourcePort = lowerSourcePort;
        changeSupport.firePropertyChange("lowerSourcePort", oldLowerSourcePort, lowerSourcePort);
    }

    public String getUpperDestinationPort() {
        return upperDestinationPort;
    }

    public void setUpperDestinationPort(String upperDestinationPort) {
        String oldUpperDestinationPort = this.upperDestinationPort;
        this.upperDestinationPort = upperDestinationPort;
        changeSupport.firePropertyChange("upperDestinationPort", oldUpperDestinationPort, upperDestinationPort);
    }

    public int getLowerDestinationPort() {
        return lowerDestinationPort;
    }

    public void setLowerDestinationPort(int lowerDestinationPort) {
        int oldLowerDestinationPort = this.lowerDestinationPort;
        this.lowerDestinationPort = lowerDestinationPort;
        changeSupport.firePropertyChange("lowerDestinationPort", oldLowerDestinationPort, lowerDestinationPort);
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        String oldProtocol = this.protocol;
        this.protocol = protocol;
        changeSupport.firePropertyChange("protocol", oldProtocol, protocol);
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        int oldAction = this.action;
        this.action = action;
        changeSupport.firePropertyChange("action", oldAction, action);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (policyNumber != null ? policyNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PolicyRule)) {
            return false;
        }
        PolicyRule other = (PolicyRule) object;
        if ((this.policyNumber == null && other.policyNumber != null) || (this.policyNumber != null && !this.policyNumber.equals(other.policyNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Gui.PolicyRule[ policyNumber=" + policyNumber + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
