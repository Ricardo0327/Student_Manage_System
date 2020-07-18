package com.neuq;

public class Student {
    private int sid;
    private String name;
    private int age;
    private String address;
    public Student(){

    }
    public Student(int sid,String name,int age,String address) {
        this.sid=sid;
        this.name=name;
        this.age=age;
        this.address=address;
    }
    public int getSid(){
        return sid;
    }
    public void setSid(int sid){
        this.sid=sid;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address)
    {
        this.address=address;
    }
}
