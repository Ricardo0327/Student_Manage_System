package com.neuq;


import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;
public class StudentManager {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ArrayList<Student> array = new ArrayList<>();
        while (true) {
            System.out.println("--------欢迎来到学生管理系统--------");
            System.out.println("1 添加学生");
            System.out.println("2 删除学生");
            System.out.println("3 修改学生");
            System.out.println("4 查看所有学生");
            System.out.println("5 退出");
            System.out.println("请输入你的选择:");
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();

            switch (line) {
                case "1":
                    System.out.println("添加学生");
                    addStudent();
                    break;
                case "2":
                    System.out.println("删除学生");
                    deleteStudent();
                    break;
                case "3":
                    System.out.println("修改学生");
                    setStudent();
                    break;
                case "4":
                    System.out.println("查看所有学生");
                    findAllStudent();
                    break;
                case "5":
                    System.out.println("谢谢使用");
                    System.exit(0);//JVM退出

            }
        }
    }

    public static void addStudent() throws SQLException, ClassNotFoundException {
        /*
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生学号:");
        int sid = sc.nextInt();

        boolean flag=false;
        for(int i=0;i<array.size();i++){
            if(array.get(i).getSid()==sid) {
                flag = true;
                break;
            }
        }
        if(flag){
            System.out.println("该学号已经存在，请重新输入");
        }else {
            System.out.println("请输入学生姓名:");
            String name = sc.nextLine();
            System.out.println("请输入学生年龄:");
            int age = sc.nextInt();
            System.out.println("请输入学生地址:");
            String address = sc.nextLine();

            //Student s = new Student(sid, name, age, address);
            //array.add(s);

            System.out.println("添加学生成功");
        }
        */
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the student ID");
        int sid = sc.nextInt();
        DataBaseManager stu = new DataBaseManager();
        boolean flag= stu.sid_exist(sid);
        if(flag){
            System.out.println("该学号已存在，请重新输入");
        }else {
            System.out.println("请输入学生姓名:");
            String name = sc.next();
            System.out.println("请输入学生年龄:");
            int age = sc.nextInt();
            System.out.println("请输入学生地址:");
            String address = sc.next();
            DataBaseManager stu_info = new DataBaseManager(sid, name, age, address);
            stu_info.insert_student(sid, name, age, address);
        }
    }

    public static void deleteStudent() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要删除学生的学号");
        int sid = sc.nextInt();
        DataBaseManager stu = new DataBaseManager();
        boolean flag = stu.sid_exist(sid);
        if(flag){
            stu.delete_student(sid);
        }else{
            System.out.println("该学号不存在，请重新输入");
        }

}

    public static void findAllStudent() throws SQLException {
        System.out.println("---学号---姓名---年龄---居住地");
        DataBaseManager stu_info = new DataBaseManager();
        stu_info.search_student();

    }

    public static void setStudent() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要修改的学生的学号:");
        int sid = sc.nextInt();
        DataBaseManager dbm = new DataBaseManager();
        boolean flag = dbm.sid_exist(sid);
        if(!flag){
            System.out.println("该学号不存在，请重新输入");
        }else {
            System.out.println("请输入学生新姓名:");
            String name = sc.next();
            System.out.println("请输入学生新年龄:");
            int age = sc.nextInt();
            System.out.println("请输入学生新居住地:");
            String address = sc.next();
            DataBaseManager dam =new DataBaseManager(sid,name,age,address);
            dbm.set_student(sid,name,age,address);
        }

    }
}
