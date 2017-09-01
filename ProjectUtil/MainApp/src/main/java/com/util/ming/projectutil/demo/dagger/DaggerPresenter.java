package com.util.ming.projectutil.demo.dagger;

import com.util.ming.projectutil.demo.dagger.entity.DaggerUser;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ming on 17/8/31.
 */

public class DaggerPresenter {
    DaggerActivity activity;
    DaggerUser user;

    public DaggerPresenter(DaggerActivity activity, DaggerUser user) {
        this.user = user;
        this.activity = activity;
    }

    public void showUserName() {
        activity.showUserName(user.getUserName());
    }
}