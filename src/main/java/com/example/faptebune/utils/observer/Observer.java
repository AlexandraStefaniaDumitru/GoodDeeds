package com.example.faptebune.utils.observer;

import com.example.faptebune.utils.events.Event;

public interface Observer <E extends Event> {
    void update(E e);
}