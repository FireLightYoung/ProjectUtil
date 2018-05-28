package com.util.ming.projectutil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ming on 17/8/21.
 */

public class JavaTest {


    public static void main(String[] args) {
        long a1 = 1;
        long a2 = 2;
        long a3 = 4;
        int a = (1 << 1) + 1;
        long b = a1 | a2;
        System.out.println("aaaaaaaaaaaaaa==================" + a+",,,,,b=="+b);
//        DOC doc = new DOC("doc", 10);
//        doc.showDoc();
//        DOC copy = doc.clone();
//        doc.showDoc();
//        System.out.println("==================");
//        copy.showDoc();
        ArrayList<Long> managerIdList = new ArrayList<>();
        managerIdList.add(1L);
        managerIdList.add(2L);
        managerIdList.add(123L);
        managerIdList.add(456L);
        JavaTest javaTest = new JavaTest();
        javaTest.registerManagerInfo(managerIdList);

        boolean f = javaTest.managerInfoFilter(1L);
        System.out.println("==================" + f);


        long c = a1 | a2 | a3;

        long d = (1 << 2) + (1 << 1) + 1;

        System.out.println("==================c==" + c + ",d==," + d);

    }


    /**
     * 注册客户经理信息队列
     */
    public List<Long> managerIdList = new ArrayList<>();

    /**
     * 注册客户经理信息，传入该客户所涉及的经理ID队列
     *
     * @param managerId 经理队列
     */
    public void registerManagerInfo(ArrayList<Long> managerId) {
        if (managerId != null) {
            this.managerIdList = managerId;
        }
    }

    /**
     * 获取经理队列
     *
     * @return
     */
    public List<Long> getManagerInfo() {
        if (managerIdList == null) {
            managerIdList = new ArrayList<>();
        }
        return managerIdList;
    }

    /**
     * 清理经理队列
     *
     * @return
     */
    public void clearManagerInfo() {
        if (managerIdList == null) {
            managerIdList = new ArrayList<>();
        }
        managerIdList.clear();
    }


//    /**
//     * 过滤经理队列
//     *
//     * @return
//     */
//    public boolean managerInfoFilter(long userId) {
//        if (managerIdList == null) {
//            boolean equal = false;
//            for (int i = 0; i < managerIdList.size(); i++) {
//                long id = managerIdList.get(i);
//                if (id == userId) {
//                    equal = true;
//                    break;
//                }
//            }
//            return equal;
//
//        }
//        return false;
//    }

    /**
     * 过滤经理队列
     *
     * @return
     */
    public boolean managerInfoFilter(long userId) {
        if (managerIdList != null) {
            if (managerIdList.contains(userId)) {
                return true;
            }
        }
        return false;
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
        System.out.println("---------------------------");
        System.out.println("name=" + name);
        System.out.println("age=" + age);
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
