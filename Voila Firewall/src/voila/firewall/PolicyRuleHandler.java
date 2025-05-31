package voila.firewall;

import java.sql.SQLException;

/**
 *
 * @author M-Umair
 */

public class PolicyRuleHandler 
{
        PolicyRule policyRule;
        DatabaseHandler dbHandler;
        
        public PolicyRuleHandler() throws ClassNotFoundException, SQLException
        {
            dbHandler = new DatabaseHandler();
            policyRule = new PolicyRule();
        }
        
    public boolean addPolicyRule(PolicyRule policyRule) throws SQLException, ClassNotFoundException
    {
        return dbHandler.addPolicyRule(policyRule);
    }
    
      public boolean editPolicyRule(int id, PolicyRule policyRule) throws SQLException, ClassNotFoundException
    { 
        return dbHandler.editPolicyRule(id, policyRule);
    }
      
        public boolean deletePolicyRule(int id) throws SQLException, ClassNotFoundException
    {
        return dbHandler.deletePolicyRule(id);    
    }
      
        public void applyPolicyRule(String src, String dst)
    {
         policyRule.applyPolicyRule(src,dst);
    }
}