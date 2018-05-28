package com.util.ming.projectutil.dressingb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ming on 17/12/15.
 */

public class Test {


    public void mothed1() {
        List<String> list = new ArrayList<String>();
//        对于循环

        for (int i = 0; i < list.size(); i++) {
        }

//        改成

        for (int i = 0, e = list.size(); i < e; i++) {
        }
    }
}
