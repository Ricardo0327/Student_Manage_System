package com.neuq;
import javax.xml.crypto.Data;
import java.sql.*;
public class DataBaseManager {
    private int sid;
    private String name;
    private int age;
    private String address;
    public DataBaseManager(){}
    public DataBaseManager(int sid, String name, int age, String address) {
        this.sid = sid;
        this.name = name;
        this.age = age;
        this.address = address;
    }
    public boolean sid_exist(int sid) throws ClassNotFoundException, SQLException {
        boolean res = false;
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pstmt=null;
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           String jdbc = "jdbc:mysql://127.0.0.1:3306/student";
           conn = DriverManager.getConnection(jdbc, "root", "iPhone0327");
           String sql="Select sid from student";
           pstmt = conn.prepareStatement(sql);
           rs=pstmt.executeQuery();
           while(rs.next()){
               int id = rs.getInt("sid");
               if(sid==id){
                   res=true;
                   break;
               }
           }
        }
        catch(Exception e) {
            e.printStackTrace();
        }finally{
            if(rs!=null){
                rs.close();
            }
            if(pstmt!=null){
                pstmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        }
        return res;
    }

    public void insert_student(int sid, String name, int age, String address) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbc = "jdbc:mysql://127.0.0.1:3306/student";
            conn = DriverManager.getConnection(jdbc, "root", "iPhone0327");
            String insert_sql = "insert into student(sid,name,age,address) value(?,?,?,?)";
            //获取执行SQL语句的对象PreparedStatement
            pstmt = conn.prepareStatement(insert_sql);

            pstmt.setInt(1, sid);
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.setString(4, address);
            int ans=pstmt.executeUpdate();
            if(ans>0){
                System.out.println("添加成功");
            }else{
                System.out.println("添加失败");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            if(pstmt!=null){
                pstmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        }
    }
    public void delete_student(int sid) throws SQLException {
        Connection conn=null;
        PreparedStatement pstmt=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbc="jdbc:mysql://127.0.0.1:3306/student";
            conn = DriverManager.getConnection(jdbc,"root","iPhone0327");
            String sql = "delete from student where sid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,sid);
            int ans=pstmt.executeUpdate();
            if(ans>0){
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(pstmt!=null){
                pstmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        }
    }
    public void search_student() throws SQLException {
        Connection conn=null;
        Statement state=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbc = "jdbc:mysql://127.0.0.1:3306/student";
            conn = DriverManager.getConnection(jdbc, "root", "iPhone0327");
            state = conn.createStatement();
            String sql = "select * from student";
            //获取执行SQL语句的对象PreparedStatement
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("sid");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                System.out.println("---"+id+"---"+name+"---"+age+"---"+address);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(state!=null){
                state.close();
            }
            if(conn!=null){
                conn.close();
            }
        }
    }
    public void set_student(int sid,String name,int age,String address) throws SQLException {
        Connection conn=null;
        PreparedStatement pstmt=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url= "jdbc:mysql://127.0.0.1:3306/student";
            conn= DriverManager.getConnection(url,"root","iPhone0327");
            String upadte_sql ="update student SET name =?, age =? ,address = ? where sid = ?";
            pstmt = conn.prepareStatement(upadte_sql);
            pstmt.setString(1,name);
            pstmt.setInt(2,age);
            pstmt.setString(3,address);
            pstmt.setInt(4,sid);
            int ans=pstmt.executeUpdate();
            if(ans>0){
                System.out.println("修改成功");
            }else{
                System.out.println("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(pstmt!=null) {
                pstmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        }
    }
}
