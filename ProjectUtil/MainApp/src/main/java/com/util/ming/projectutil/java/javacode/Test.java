package com.util.ming.projectutil.java.javacode;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ming on 17/9/26.
 */

public class Test {


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.print("123123124");

        int a = 100;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("haha", "123");

            String num = jsonObject.getString("didi");
            System.out.println("num==" + num);
        } catch (JSONException e) {
            System.out.println("Eeeeeeeee");
            e.printStackTrace();
        }

    }
}
