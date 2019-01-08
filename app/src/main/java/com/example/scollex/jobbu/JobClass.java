package com.example.scollex.jobbu;

import java.io.Serializable;

public class JobClass implements Serializable {

    private String JobName;
    private String Salary;
    private String jobWorkingTime;
    private String jobType;
    private String JobDesc;
    private String jobState;
    private String JobLocation;
    private int jobThumbnail;

    public JobClass() {
    }

    public JobClass(String jobName, String Salary, String jobWorkingTime, String jobType, String jobDesc, String jobState, String jobLocation, int jobThumbnail) {
        JobName = jobName;
        this.Salary = Salary;
        this.jobWorkingTime = jobWorkingTime;
        this.jobType = jobType;
        JobDesc = jobDesc;
        this.jobState = jobState;
        JobLocation = jobLocation;
        this.jobThumbnail = jobThumbnail;
    }


    public String getJobName() {
        return JobName;
    }

    public void setJobName(String jobName) {
        JobName = jobName;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String category) {
        Salary = category;
    }

    public String getJobWorkingTime() {
        return jobWorkingTime;
    }

    public void setJobWorkingTime(String jobWorkingTime) {
        this.jobWorkingTime = jobWorkingTime;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobDesc() {
        return JobDesc;
    }

    public void setJobDesc(String jobDesc) {
        JobDesc = jobDesc;
    }

    public String getJobState() {
        return jobState;
    }

    public void setJobState(String jobState) {
        this.jobState = jobState;
    }

    public String getJobLocation() {
        return JobLocation;
    }

    public void setJobLocation(String jobLocation) {
        JobLocation = jobLocation;
    }

    public int getJobThumbnail() {
        return jobThumbnail;
    }

    public void setJobThumbnail(int jobThumbnail) {
        this.jobThumbnail = jobThumbnail;
    }
}
