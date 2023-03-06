package com.example.faptebune.utils.observer;

import com.example.faptebune.utils.events.Event;

public interface Observable <E extends Event>{
    void addObserver(Observer<E> e);

    void removeObserver(Observer<E> e);

    void notifyObservers(E t);
}