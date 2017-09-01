package com.util.ming.projectutil.demo.green;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by ming on 17/8/28.
 */
@Entity
public class Student {
    @Property(nameInDb = "name")
    String name;
    @Index
    long number;
    @Property(nameInDb = "age")
    int age;

    public Student() {
    }

    public Student(String name, long number, int age) {
        this.name = name;
        this.number = number;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
