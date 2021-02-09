package Sanjeevani.dao;

import Sanjeevani.dbutil.DBConnection;
import Sanjeevani.pojo.DoctorPojo;
import Sanjeevani.pojo.UserDetailPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DoctorDao {
     public static ArrayList<String> getUserDetails()throws SQLException
    {
     Connection conn=DBConnection.getConnection();
        String qry="select distinct empid  from users where usertype='Doctor' order by empid";
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(qry);
        ArrayList<String> idlist=new ArrayList<>();
        while(rs.next())
        {
        String id=rs.getString(1);
        idlist.add(id);
        }
        return idlist;
    } 
 
    public static ArrayList<String> getFindEmp()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="select Empname from employees where Role='Doctor'";
                //+ " and empid not in (select EMPID from users where usertype='Doctor')";
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(qry);
        ArrayList<String> Empname=new ArrayList<>();
        while(rs.next())
        {
        String name=rs.getString(1);
           // System.out.println("name "+id);
           Empname.add(name);
        }
        return Empname;
     }
    public static String getEmpId(String s)throws SQLException
    {
    String eid=null;
    UserDetailPojo up =new UserDetailPojo();
 PreparedStatement ps=DBConnection.getConnection().prepareStatement("select empid from employees where empname=?");
   ps.setString(1, up.getEmpid());
   ResultSet rs=ps.executeQuery();
   if(rs.next())
   {
   
   eid= rs.getString(1);
   }
 return eid;
    
    }
 public static boolean getRegisterUsers(UserDetailPojo ep)throws SQLException
{
PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into users values(?,?,?,?,?)");
ps.setString(1,ep.getUserId());
ps.setString(2, ep.getUserName());
ps.setString(3, ep.getEmpid());
ps.setString(4, ep.getPassword());
ps.setString(5,ep.getUserType());
 int rs=ps.executeUpdate();
 return rs==1;
}
 
 public static boolean getRegisterDoctor(DoctorPojo dp)throws SQLException
{
PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into doctor values(?,?,?,?,'Y')");
ps.setString(1,dp.getUserid());
ps.setString(2, dp.getDoctorId());
ps.setString(3, dp.getQualification());
ps.setString(4, dp.getSpecialize());
 int rs=ps.executeUpdate();
 return rs==1;
}
public static String getNewId()throws SQLException
{
Connection con=DBConnection.getConnection();
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("select max(doctorid) from doctor where active='Y'");
 int id=1;
   if(rs.next())
   {
    String empid=rs.getString(1);
//System.out.println(empid.substring(1));
 if (empid==null)
           return "D101";
	int eno=Integer.parseInt(empid.substring(1));
	id = id + eno;
        String sr = "D" + id;
          return sr;
        }
        else 
        {
        return "D101";
   
      
   }
   }
public static ArrayList<String> getAllDoctorsId()throws SQLException
    {
        ArrayList<String> docId = new ArrayList<>();
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery("select doctorid from doctor where active='Y'");
        while(rs.next())
        {
            docId.add(rs.getString(1));
        }
        return docId;
    }
 public static boolean RemoveDoctor(String st)throws SQLException
 {
 PreparedStatement ps=DBConnection.getConnection().prepareStatement("update doctor set active='N' where doctorid=?");
ps.setString(1,st);
int rs=ps.executeUpdate();
 return rs==1;
 }
 public static ArrayList<DoctorPojo> getAllDoctors()throws SQLException
{
Connection con=DBConnection.getConnection();
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("select * from Doctor");
ArrayList<DoctorPojo> EmpList=new ArrayList<>();
while(rs.next())
{
    DoctorPojo ep=new DoctorPojo();
ep.setUserid(rs.getString(1));
ep.setDoctorId(rs.getString(2));
ep.setQualification(rs.getString(3));
ep.setSpecialize(rs.getString(4));
EmpList.add(ep);
}
        return EmpList;
}


}
