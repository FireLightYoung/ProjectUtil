package com.util.ming.projectutil.demo.dagger;

import dagger.Component;

/**
 * Created by ming on 17/8/31.
 */

@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(DaggerActivity daggerActivity);
}