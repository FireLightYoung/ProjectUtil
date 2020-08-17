package com.example.animationlib;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;


public class AnimationDemoActivity extends Activity implements View.OnClickListener {
    View view_anim;
    Button anim_btn1;
    Button anim_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_demo);
        view_anim = findViewById(R.id.view_anim);
        anim_btn1 = findViewById(R.id.anim_btn1);
        anim_btn2 = findViewById(R.id.anim_btn2);


        anim_btn1.setOnClickListener(this);
        anim_btn2.setOnClickListener(this);
        view_anim.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.view_anim) {
            Toast.makeText(this, "点击了view ", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.anim_btn1) {
            AllAnimation(view_anim);
        } else if (view.getId() == R.id.anim_btn2) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate);
//            animation.setFillAfter(true);
            view_anim.startAnimation(animation);
        }
    }


    private void RotateAnimation(View myView) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(myView, "rotation", 0f, 360f);
        anim.setRepeatCount(-1);
        anim.setRepeatMode(ObjectAnimator.RESTART);
        anim.setDuration(1000);
        anim.start();
    }

    private void AlpahAnimation(View myView) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(myView, "alpha", 1.0f, 0.8f, 0.6f, 0.4f, 0.2f, 0.0f);
        anim.setRepeatCount(-1);
        anim.setRepeatMode(ObjectAnimator.REVERSE);
        anim.setDuration(2000);
        anim.start();
    }

    private void AllAnimation(View myView) {
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(myView, "alpha", 1.0f, 0.5f, 0.8f, 1.0f);
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(myView, "scaleX", 0.0f, 1.0f);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(myView, "scaleY", 0.0f, 2.0f);
        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(myView, "rotation", 0, 360);
        ObjectAnimator transXAnim = ObjectAnimator.ofFloat(myView, "translationX", 100, 400);
        ObjectAnimator transYAnim = ObjectAnimator.ofFloat(myView, "translationY", 100, 750);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(alphaAnim, scaleXAnim, scaleYAnim, rotateAnim, transXAnim, transYAnim);
//                set.playSequentially(alphaAnim, scaleXAnim, scaleYAnim, rotateAnim, transXAnim, transYAnim);
        set.setDuration(3000);
        set.start();
    }

}