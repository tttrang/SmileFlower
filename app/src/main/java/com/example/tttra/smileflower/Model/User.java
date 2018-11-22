package com.example.tttra.smileflower.Model;

public class User {
    private String Name;
    private String PassWord;
    private String Email;
    private String Phone;

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public User(String name, String passWord, String email, String phone) {
        Name = name;
        PassWord = passWord;
        Email = email;
        Phone = phone;
    }

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String name, String passWord) {
        Name = name;
        PassWord = passWord;
    }

    public User(String name, String passWord, String email) {
        Name = name;
        PassWord = passWord;
        Email = email;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
}
