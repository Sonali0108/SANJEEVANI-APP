package Sanjeevani.dao;

import Sanjeevani.dbutil.DBConnection;
import Sanjeevani.pojo.EmpPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmpDao {
    public static String getNewId() throws SQLException
    {
    Connection conn =DBConnection.getConnection();
    Statement st = conn.createStatement();
    ResultSet rs=st.executeQuery("select max(EMPID) from Employees");
    int id=1;
    if(rs.next())
    {
        String empid=rs.getString(1);
        int eno=Integer.parseInt(empid.substring(1));
    id=id+eno;
    }
    String sr="E"+id;
    System.out.println(sr);
    return sr;
    }
    public static boolean addEmployee(EmpPojo ep) throws SQLException
    {
    PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into Employees values(?,?,?,?) ");
    ps.setString(1,ep.getEmpid());
     ps.setString(2,ep.getEmpname());
    ps.setString(3,ep.getJob());
     ps.setDouble(4,ep.getSal());
     int rs=ps.executeUpdate();
     return rs==1;
    
    }

   public static ArrayList<EmpPojo> getAllEmp() throws SQLException
           {
           Connection conn =DBConnection.getConnection();
           Statement st=conn.createStatement();
           ResultSet rs=st.executeQuery("Select * from Employees");
           ArrayList<EmpPojo> emplist=new ArrayList<>();
           while(rs.next())
           {
           EmpPojo e=new EmpPojo();
           e.setEmpid(rs.getString(1));
           e.setEmpname(rs.getString(2));
           e.setJob(rs.getString(3));
           e.setSal(rs.getInt(4));
           emplist.add(e);
           }
              return emplist;
           }
     public static EmpPojo findEmpById(int eno)throws SQLException
      {
      EmpPojo e= null;
   PreparedStatement ps=DBConnection.getConnection().prepareStatement("select * from Employees where empno=?");
   ps.setInt(1, eno);
   ResultSet rs= ps.executeQuery();
   if(rs.next())
   {
    e=new EmpPojo();
     e.setEmpid(rs.getString(1));
           e.setEmpname(rs.getString(2));
           e.setJob(rs.getString(3));
           e.setSal(rs.getInt(4));
          
    }
   return e;
      }
   public static boolean getUpdate(EmpPojo ep) throws SQLException
   {
    PreparedStatement ps=DBConnection.getConnection().prepareStatement("update Employees set ename=?,role=?,sal=?,where empid=?");
   ps.setString(1, ep.getEmpname());
   ps.setString(2, ep.getJob());
   ps.setDouble(3, ep.getSal());
   ps.setString(4, ep.getEmpid());
   int res =ps.executeUpdate();
   return res==1;
   }
   public static boolean DeletedEmp(String eno)throws SQLException
   {
    int num=0;
     PreparedStatement ps=DBConnection.getConnection().prepareStatement("delete users where empid=?");
     ps.setString(1, eno);
     int rs=ps.executeUpdate();
     if(rs==1)
     {
      PreparedStatement ps1=DBConnection.getConnection().prepareStatement("delete users where empid=?");
      ps1.setString(1, eno);
      num=ps1.executeUpdate();
 
     
     }

   return num==1;
   }
   public static ArrayList<String> getIdRole()throws SQLException
    {
     Connection conn=DBConnection.getConnection();
        String qry="select distinct empid  from employees order by empid";
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
 public static EmpPojo getFindKey(String id)throws SQLException
 {EmpPojo ep=null;
 PreparedStatement ps=DBConnection.getConnection().prepareStatement("select * from employees where empid=?");
   ps.setString(1, id);
   ResultSet rs=ps.executeQuery();
   if(rs.next())
   {
    ep=new EmpPojo();
ep.setEmpid(rs.getString(1));
ep.setEmpname(rs.getString(2));
ep.setJob(rs.getString(3));
ep.setSal(rs.getDouble(4));
   }
 return ep;
 }

  
  }
                 
                  
           
          
           
           
           
         
