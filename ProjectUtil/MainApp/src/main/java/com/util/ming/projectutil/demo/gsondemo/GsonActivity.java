package com.util.ming.projectutil.demo.gsondemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.util.ming.projectutil.R;
import com.util.ming.projectutil.activity.BaseActivity;

/**
 * Created by ming on 17/5/9.
 */

public class GsonActivity extends BaseActivity implements View.OnClickListener {
    private TextView textview;

    public String json;
    public String json2 = "{\n" +
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
        json = getString(R.string.json);
    }

    private void initView() {

        textview = (TextView) findViewById(R.id.textView_gson);

    }

    private void initEvent() {
//        parseJson();

        createJson();
    }

    private void createJson() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "李四");
        jsonObject.addProperty("number", "123456");
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("studyName", "物理");
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("studyName", "化学");
        JsonObject jsonObject3 = new JsonObject();
        jsonObject3.addProperty("studyName", "生物");
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);
        jsonArray.add(jsonObject3);
        jsonObject.add("study", jsonArray);
        parseJson(jsonObject.toString());
    }

    private void parseJson(String json) {
        Gson gson = new GsonBuilder()
                .setLenient()// json宽松
                .enableComplexMapKeySerialization()//支持Map的key为复杂对象的形式
                .serializeNulls() //智能null
                .setPrettyPrinting()// 调教格式
                .disableHtmlEscaping() //默认是GSON把HTML 转义的
                .create();
        GsonUser user = gson.fromJson(json, GsonUser.class);
        String strUser = user.toString();


        textview.setText(strUser);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}
