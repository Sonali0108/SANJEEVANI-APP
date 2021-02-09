package Sanjeevani.dao;

import Sanjeevani.dbutil.DBConnection;
import Sanjeevani.pojo.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class UserDao {
    public static String validateUser(Users user) throws SQLException
    {
        Connection conn= DBConnection.getConnection();
        String qry="Select username from Users where userId=? and password=? and userType=?";
        PreparedStatement ps =conn.prepareStatement(qry);
        //System.out.println(user);
        ps.setString(1,user.getUserId());
        ps.setString(2,user.getPassword());
       ps.setString(3,user.getUserType());
       ResultSet rs=ps.executeQuery();
       String username=null;
       if(rs.next())
       {
        username=rs.getString(1);
      }
       return username;
      }
   // public static boolean changePassword(String userId,String password)throws SQLException{
      
    //   PreparedStatement ps=DBConnection.getConnection().PreparedStatement("update users set password=")
      // ps.setString(1,password);
       //ps.setString(1,userId);
       //return(ps.executeUpdate()!==0)
    
   // }
    public static HashMap <String,String>getReceptionistList() throws SQLException
    {
     HashMap<String,String>receptionistList=new HashMap<>();
     Statement st=DBConnection.getConnection().createStatement();
     ResultSet rs=st.executeQuery("select userid,username from users where usertype='Receptionist'");
     while(rs.next())
     {
      receptionistList.put(rs.getString(1),rs.getString(2));
     }
    return receptionistList;
    }
    
}
