package com.example.scollex.jobbu;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class User {
    private String userID, Name;
    private int Age;
    private String gender, bio;
    private Calendar birthday;
    private Education education;
    private int expectedSalary;
    private String jobType;
    private String language;
    private String skill;

    public User() {
    }

    public User(String userID,String Name, int Age, String bio, String gender, Calendar birthday, Education education,
                int expectedSalary, String jobType, String language, String skill) {
        this.userID = userID;
        this.Name = Name;
        this.Age = Age;
        this.bio = bio;
        this.gender = gender;
        this.birthday = birthday;
        this.education = education;
        this.expectedSalary = expectedSalary;
        this.jobType = jobType;
        this.language = language;
        this.skill = skill;
    }

    public String getUserID() {
        return userID;
    }

    public int getAge() {
        return Age;
    }

    public String getGender() {
        return gender;
    }

    public String getBio() {
        return bio;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public String getBirthdate(){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        return format.format(birthday.getTime());
    }

    public Education getEducation() {
        return education;
    }

    public int getExpectedSalary() {
        return expectedSalary;
    }

    public String getJobType() {
        return jobType;
    }

    public String getLanguage() {
        return language;
    }

    public String getSkill() {
        return skill;
    }

    public String getName() {
        return Name;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.Name = Name;
    }

    public void setAge(int age) {
        this.Age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public void setExpectedSalary(int expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public static class Education {
        String level;
        Period period;
        String Professions;
        String where;

        public Education(String level,String professions) {
            this.level = level;
            this.Professions = professions;
        }

        public Education(String level, Education.Period period, String professions, String where) {
            this.level = level;
            this.period = period;
            Professions = professions;
            this.where = where;
        }

        private class Period{
            Calendar start;
            Calendar end;


            public Period(Calendar start, Calendar end) {
                this.start = start;
                this.end = end;
            }
        }
    }
}
