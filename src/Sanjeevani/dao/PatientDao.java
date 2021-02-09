package Sanjeevani.dao;

import Sanjeevani.dbutil.DBConnection;
import Sanjeevani.pojo.PatientPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PatientDao {
    public static ArrayList<String> getAllDoctorsId()throws SQLException
    {
    ArrayList<String> docId=new ArrayList<>();
    ResultSet rs=DBConnection.getConnection().createStatement().executeQuery("select doctorid from doctor");
    return docId;
    }
    
    public static String getNewID() throws SQLException
    {
    Connection conn=DBConnection.getConnection();
   Statement st=conn.createStatement();
   ResultSet rs= st.executeQuery("Select max(p_id) from patient");
   int id=1;
   if(rs.next())
   {
    String empid=rs.getString(1);
//System.out.println(empid.substring(1));
 if (empid==null)
           return "P101";
	int eno=Integer.parseInt(empid.substring(1));
	id = id + eno;
        String sr = "P" + id;
          return sr;
        }
        else 
        {
        return "P101";
   
      
   }
    }
    public static boolean addPatient(PatientPojo p)throws SQLException
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into patient values(?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, p.getP_id());
        ps.setString(2, p.getF_name());
        ps.setString(3, p.getS_name());
           ps.setInt(4, p.getAge());
        ps.setString(5, p.getOpd());
        ps.setString(6, p.getGender());
        ps.setString(7, p.getM_status());
          ps.setDate(8,p.getDate());
        ps.setString(9, p.getAddress());
        ps.setString(10, p.getCity());
        ps.setString(11,p.getMno());
        ps.setString(12,p.getDoctor_id());
        return (ps.executeUpdate()!=0);    
    }
     public static ArrayList<PatientPojo> getAllPatients()throws SQLException
     {

    Connection con=DBConnection.getConnection();
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("select * from patient order by p_id");
ArrayList<PatientPojo> EmpList=new ArrayList<>();
while(rs.next())
{
    PatientPojo ep=new PatientPojo();
ep.setDoctor_id(rs.getString(1));
ep.setP_id(rs.getString(2));
ep.setF_name(rs.getString(3));
ep.setS_name(rs.getString(4));
ep.setAge(rs.getInt(1));
ep.setGender(rs.getString(1));
ep.setM_status(rs.getString(1));
ep.setAddress(rs.getString(1));
ep.setCity(rs.getString(1));
ep.setMno(rs.getString(1));
ep.setDate(rs.getDate(1));



EmpList.add(ep);
}
        return EmpList;
}




    
}
