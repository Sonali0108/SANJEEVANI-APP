package Sanjeevani.dao;

import Sanjeevani.dbutil.DBConnection;
import Sanjeevani.pojo.EmpPojo;
import Sanjeevani.pojo.UserDetailPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class ReceptionistDao {
    public static boolean addReceptionist(UserDetailPojo udp)throws SQLException
    {
    PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into users values(?,?,?,?,?)");
    ps.setString(1, udp.getUserId());
    ps.setString(2, udp.getUserName());
    ps.setString(3, udp.getEmpid());
    ps.setString(4, udp.getPassword());
    ps.setString(5, udp.getUserType());
    int result= ps.executeUpdate();
    return result==1;
    
    }
    public static ArrayList<EmpPojo> getAllReceptionist()throws SQLException
{
Connection con=DBConnection.getConnection();
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("select * from employees where role='Receptionist' and empid not in (select empid from users where usertype='Receptionist')");
ArrayList<EmpPojo> EmpList=new ArrayList<>();
while(rs.next())
{ EmpPojo ep=new EmpPojo();
ep.setEmpid(rs.getString(1));
ep.setEmpname(rs.getString(2));
ep.setJob(rs.getString(3));
ep.setSal(rs.getInt(4));
EmpList.add(ep);
}
        return EmpList;
}

    public static HashMap<String,String> getNonRegisteredReceptionistList()throws SQLException
    {
    Statement st=DBConnection.getConnection().createStatement();
    ResultSet rs= st.executeQuery("select empid,empname from employees where Role ='Receptionist' and empid not in(select empid from users where usertype='Receptionist')");
    HashMap<String,String>receptionist = new HashMap();
    while(rs.next())
    {
      String id = rs.getString(1);
      String name=rs.getString(2);
      receptionist.put(id,name);
     }
    return receptionist;
    }
    public static boolean getUpdatePassword(UserDetailPojo ep)throws SQLException
{
PreparedStatement ps=DBConnection.getConnection().prepareStatement("update users set password=? where userid=? and username=? and usertype=?");
ps.setString(1,ep.getPassword());
ps.setString(2, ep.getUserId());
ps.setString(3, ep.getUserName());
ps.setString(4, ep.getUserType());
 int rs=ps.executeUpdate();
 return rs==1;
}
public static ArrayList<String> getAllRcptId()throws SQLException
{
Connection con=DBConnection.getConnection();
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("select userid from users where usertype='Receptionist'");
ArrayList<String> EmpList=new ArrayList<>();
while(rs.next())
{ 
EmpList.add(rs.getString(1));
}
return EmpList;
}
 public static boolean RemoveRcpt(String id)throws SQLException
 {
 PreparedStatement ps=DBConnection.getConnection().prepareStatement("delete users where userid=?");
ps.setString(1,id);
int rs=ps.executeUpdate();
 return rs==1;
 }
  public static HashMap<String,String> getRegisteredRecieptionistList()throws SQLException
{
        Connection conn=DBConnection.getConnection();
        String qry="select userid,username from users where usertype='Receptionist'";
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(qry);
        HashMap<String,String> receptionist=new HashMap<>();
        while(rs.next())
        {
        String id=rs.getString(1);
        String name=rs.getString(2);
           // System.out.println("name "+id);
           receptionist.put(id, name);
        }
        return receptionist;
 }

 
    
}
