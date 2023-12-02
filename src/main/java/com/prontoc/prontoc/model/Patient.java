package com.prontoc.prontoc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
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
    private String email;
    private String address;
    private String phone;
    @DocumentReference
    private List<String> crm;


    //getters setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getCrm() {
        return crm;
    }

    public void setCrm(List<String> crm) {
        this.crm = crm;
    }

    public void addCrmMedicosrelacinados(String crm)
    {
        if(this.crm == null)
        {
            this.crm = new ArrayList<>();
        }
        this.crm.add(crm);
    }
}
