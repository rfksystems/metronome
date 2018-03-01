package com.rfksystems.metronome;

@FunctionalInterface
public interface Factory<T> {
    T getInstance();
}
