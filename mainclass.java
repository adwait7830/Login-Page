import java.sql.*;
import java.util.Random;

class sqlException extends Exception{}
class fieldEntryException extends Exception{}
class mobileNoException extends Exception{}
public class mainclass {

    protected static String userName;
    protected static int rID;
	public static void main(String[] args) {
    	
        new loginWindow();

    }
	
	private static String getpass(char[] a) {
		
		StringBuilder stb = new StringBuilder();
        for(char i : a)stb.append(i);
        return stb.toString();
		
	}

    protected static boolean checkforauth(int userID,char[] b) throws Exception{
    	
    	try {
    		
    		final Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfo?useSSl=false","root","rootpass"); 		
    		
    		final String sql = "select * from users where UserID = '"+userID+"' and Password = '"+getpass(b)+"'";
    		final String sql2 = "select UserName from users where UserID = '"+userID+"'";
    		final PreparedStatement stm = con1.prepareStatement(sql);
    		if(stm.executeQuery(sql).next()) {
    			
    			final ResultSet rs = stm.executeQuery(sql2);
    			if(rs.next())userName = rs.getString("UserName");
    			con1.close();
    			return true;
    			
    		}
    		else {
    			
    			con1.close();
    			return false;
    			
    		}
    		
		} catch (Exception e) {throw new sqlException();}
    	
    }
    
    protected static boolean addnewuser(String name,char[] p,char[] cp,String mobileNo) throws Exception {
    	
    	if(getpass(p).equals(getpass(cp))) {
    		
    		if(name.length() == 0 | (getpass(cp).length() == 0 & getpass(p).length() == 0))throw new fieldEntryException();
    		if(mobileNo.length() != 10)throw new mobileNoException();
			final Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfo?useSSl=false","root","rootpass");
    			int r;
    				do {
        				Random random = new Random();
            			rID = random.nextInt(888888) + 111111;
            			final String sql3 = "insert into users values(?,?,?,?)";
            			final PreparedStatement stm2 = con2.prepareStatement(sql3);
            			stm2.setInt(1, rID);
            			stm2.setString(2,getpass(p));
            			stm2.setString(3, name);
            			stm2.setString(4, mobileNo);
            			r = stm2.executeUpdate();
        			}while(r == 0);
    			con2.close();
    			return true;
    		}else return false;
	
    }
}

