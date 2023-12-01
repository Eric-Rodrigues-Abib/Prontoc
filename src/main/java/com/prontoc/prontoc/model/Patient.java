package com.prontoc.prontoc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "Patients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    private String id;
    private String nameP;
    private Number age;
    private String birth;
    private String sex;
    private List<String> contact;
    @DocumentReference
    private List<User> crm;


    //getters setters


    public String getId() {
        return id;
    }

    public void setId(String patientid) {
        id = patientid;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public Number getAge() {
        return age;
    }

    public void setAge(Number age) {
        this.age = age;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<String> getContact() {
        return contact;
    }

    public void setContact(List<String> contact) {
        this.contact = contact;
    }

    public List<User> getCrm() {
        return crm;
    }

    public void setCrm(List<User> crm) {
        this.crm = crm;
    }
}
