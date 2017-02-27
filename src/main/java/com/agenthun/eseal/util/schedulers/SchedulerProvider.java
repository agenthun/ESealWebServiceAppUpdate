package com.agenthun.eseal.util.schedulers;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by agenthun on 2017/2/27.
 */
public class SchedulerProvider implements BaseSchedulerProvider {
    private static SchedulerProvider instance;

    public static synchronized SchedulerProvider getInstance() {
        if (instance == null) {
            instance = new SchedulerProvider();
        }
        return instance;
    }

    public Scheduler computation() {
//        return Schedulers.computation();
        return Schedulers.immediate();
    }

    public Scheduler io() {
//        return Schedulers.io();
        return Schedulers.immediate();
    }

    public Scheduler ui() {
//        return Schedulers.io();
        return Schedulers.immediate();
    }
}
