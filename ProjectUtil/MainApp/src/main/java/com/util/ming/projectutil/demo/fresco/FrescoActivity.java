package com.util.ming.projectutil.demo.fresco;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.GenericDraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.util.ming.projectutil.R;

/**
 * Created by ming on 17/5/10.
 */

public class FrescoActivity extends Activity {
    SimpleDraweeView SimpleDraweeView1;
    SimpleDraweeView SimpleDraweeView2;
    DraweeView draweeView;
    GenericDraweeView genericDraweeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);
        setImage1();
        setImage2();
        setImage3();
        setImage4();
    }

    public void setImage1() {
        Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/logo.png");
        SimpleDraweeView1 = (SimpleDraweeView) findViewById(R.id.my_image_view);
        SimpleDraweeView1.setImageURI(uri);
    }

    public void setImage2() {
        Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/logo.png");
        SimpleDraweeView2 = (SimpleDraweeView) findViewById(R.id.my_SimpleDraweeView_view);
        SimpleDraweeView2.setImageURI(uri);
    }

    public void setImage3() {
        Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/logo.png");
        draweeView = (DraweeView) findViewById(R.id.my_DraweeView_view);
        ViewGroup.LayoutParams params = draweeView.getLayoutParams();
        params.height = 100;
        params.width = 100;

        draweeView.setLayoutParams(params);
        draweeView.setImageURI(uri);
    }

    public void setImage4() {
        Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/logo.png");
        genericDraweeView = (GenericDraweeView) findViewById(R.id.my_GenericDraweeView_view);
        genericDraweeView.setImageURI(uri);
    }
}
