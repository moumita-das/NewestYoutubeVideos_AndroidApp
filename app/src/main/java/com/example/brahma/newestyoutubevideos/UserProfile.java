package com.example.brahma.newestyoutubevideos;

public class UserProfile{
    private String name, age, gender;
    public UserProfile(String name, String age, String gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    public UserProfile(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
