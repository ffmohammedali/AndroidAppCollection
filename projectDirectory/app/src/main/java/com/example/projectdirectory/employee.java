package com.example.projectdirectory;

import android.widget.ImageView;

public class employee {
    String name ;
    String id;

    public String getName()
    {

        return  name ;
    }
    public String getId()
    {

        return  id ;
    }
}

class employeeImage
{
    String name ;
    String id;
    String designation;

    public employeeImage(String name, String id, String designation) {
        this.name = name;
        this.id = id;
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
class employeecheckbox
{
    int value;
    String name;
    boolean ischeck;

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public employeecheckbox(int value, String name) {
        this.value = value;
        this.name = name;
        this.ischeck = false;
    }




    

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
