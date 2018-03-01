package com.rfksystems.metronome;

public class MetronomeTimer {
    public MetronomeTimer(final String name) {

    }

    public MetronomeTimerLoop start() {
        return new MetronomeTimerLoop(this);
    }
}
