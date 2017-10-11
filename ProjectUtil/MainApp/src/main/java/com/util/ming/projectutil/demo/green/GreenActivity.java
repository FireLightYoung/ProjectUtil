package com.util.ming.projectutil.demo.green;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.util.ming.projectutil.R;
import com.util.ming.projectutil.activity.BaseActivity;

/**
 * Created by ming on 17/8/28.
 */

public class GreenActivity extends BaseActivity implements View.OnClickListener {


    private EditText insertDaoNameEdit;
    private EditText insertDaoNumberEdit;
    private EditText insertDaoAgeEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green);
        initData();
        initView();
        initEvent();

    }

    private void initData() {
    }

    private void initView() {
        findViewById(R.id.btn_insert_green).setOnClickListener(this);
        insertDaoNameEdit = (EditText) findViewById(R.id.txt_insert_green_name);
        insertDaoNumberEdit = (EditText) findViewById(R.id.txt_insert_green_number);
        insertDaoAgeEdit = (EditText) findViewById(R.id.txt_insert_green_age);
    }

    private void initEvent() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert_green:
                String name = insertDaoNameEdit.getEditableText().toString();
                long number = Long.decode(insertDaoNumberEdit.getEditableText().toString());
                int age = Integer.decode(insertDaoAgeEdit.getEditableText().toString());
                Student student = new Student(name, number, age);
                insertDao(student);
                break;
        }
    }

    public void insertDao(Student student) {

    }


    public Student searchDao(long number) {
        return null;
    }
}
