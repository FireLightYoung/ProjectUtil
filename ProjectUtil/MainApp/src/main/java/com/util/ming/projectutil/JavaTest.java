package com.util.ming.projectutil;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ming on 17/8/21.
 */

public class JavaTest {


    public static void main(String[] args) {
        DOC doc = new DOC("doc", 10);
        doc.showDoc();
        DOC copy = doc.clone();
        doc.showDoc();
    }
}

class DOC implements Cloneable {
    String name;
    int age;
    ArrayList<String> mList = new ArrayList<>();

    public DOC(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void showDoc() {
        System.out.print("---------------------------");
        System.out.print("name=" + name);
        System.out.print("age=" + age);
    }

    protected DOC clone() {

        DOC doc = null;
        try {
            doc = (DOC) super.clone();
            doc.name = this.name;
            doc.age = this.age;
            doc.mList = (ArrayList<String>) this.mList.clone();
            return doc;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
