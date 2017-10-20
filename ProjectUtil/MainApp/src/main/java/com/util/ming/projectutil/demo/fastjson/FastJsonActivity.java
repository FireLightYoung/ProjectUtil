package com.util.ming.projectutil.demo.fastjson;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.util.ming.projectutil.R;
import com.util.ming.projectutil.activity.BaseActivity;

/**
 * Created by ming on 17/5/9.
 */

public class FastJsonActivity extends BaseActivity implements View.OnClickListener {
    private TextView textview;

    public String json;
    public String json2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fastjson);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
        json = getString(R.string.json);
        json2 = "{\n" +
                "  \"name\": \"张三\",\n" +
                "  \"number\": \"100001\",\n" +
                "  \"study\": [\n" +
                "    {\n" +
                "      \"studyName\": \"语文\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"studyName\": \"数学\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"studyName\": \"英语\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    private void initView() {

        textview = (TextView) findViewById(R.id.textView_fastJson);

    }

    private void initEvent() {
        parseJson(json);

//        createJson();
    }

    private void createJson() {


    }

    private void parseJson(String jsonStr) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}
