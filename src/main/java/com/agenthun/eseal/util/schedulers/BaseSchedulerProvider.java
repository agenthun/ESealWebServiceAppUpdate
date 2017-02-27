package com.agenthun.eseal.util.schedulers;

import com.sun.istack.internal.NotNull;
import rx.Scheduler;

/**
 * Created by agenthun on 2017/2/27.
 */
public interface BaseSchedulerProvider {

    @NotNull
    Scheduler computation();

    @NotNull
    Scheduler io();

    @NotNull
    Scheduler ui();
}
