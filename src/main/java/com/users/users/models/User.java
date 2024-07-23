package com.users.users.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String gender;
    private int age;
    private LocalDateTime creationDate;

    public User(){}

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getGender(){
        return this.gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }
    public void setCreationDate(LocalDateTime now) {
        this.creationDate = now;
    }
}
