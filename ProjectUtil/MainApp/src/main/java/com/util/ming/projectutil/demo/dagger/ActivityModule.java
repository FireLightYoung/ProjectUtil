package com.util.ming.projectutil.demo.dagger;

import com.util.ming.projectutil.demo.dagger.entity.DaggerUser;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ming on 17/8/31.
 */

@Module
public class ActivityModule {
    private DaggerActivity activity;

    public ActivityModule(DaggerActivity activity) {
        this.activity = activity;
    }

    @Provides
    public DaggerActivity provideActivity() {
        return activity;
    }

    @Provides
    public DaggerUser provideUser() {
        return new DaggerUser("user form ActivityModule", "123");
    }

    @Provides
    public DaggerPresenter provideDaggerPresenter(DaggerActivity activity, DaggerUser user) {
        return new DaggerPresenter(activity, user);
    }
}