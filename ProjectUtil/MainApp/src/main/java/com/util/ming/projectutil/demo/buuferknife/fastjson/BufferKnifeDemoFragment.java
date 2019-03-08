package com.util.ming.projectutil.demo.buuferknife.fastjson;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.util.ming.projectutil.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ming on 17/12/6.
 */

public class BufferKnifeDemoFragment extends Fragment {


    @BindView(R.id.textView_buffer_fragment)
    TextView mTextView;
    @BindView(R.id.btn_buffer_fragment)
    Button mButton;

    Unbinder mUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bufferknife, container, false);
        //初始化bufferKnife
        mUnbinder = ButterKnife.bind(this, view);
        // TODO Use fields...
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
