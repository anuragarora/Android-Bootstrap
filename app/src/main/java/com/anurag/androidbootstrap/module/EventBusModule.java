package com.anurag.androidbootstrap.module;

import de.greenrobot.event.EventBus;

/**
 * Created by anurag_arora on 11/17/2015.
 */

public class EventBusModule {
    private static EventBus sEventBus = EventBus.builder().throwSubscriberException(true).build();

    public static EventBus eventBus() {
        return sEventBus;
    }
}
