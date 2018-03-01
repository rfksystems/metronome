package com.rfksystems.metronome;

import java.io.Serializable;
import java.util.concurrent.ConcurrentMap;

public class Metronome implements Serializable {
    private final MetronomeConfiguration configuration;
    private final ConcurrentMap<String, MetronomeMeter> meters;
    private final ConcurrentMap<String, MetronomeTimer> timers;

    public Metronome() {
        this(new MetronomeConfiguration());
    }

    public Metronome(final MetronomeConfiguration configuration) {
        this.configuration = configuration;
        this.meters = configuration.createMeterMap();
        this.timers = configuration.createTimerMap();
    }

    public MetronomeMeter meter(final String name) {
        return meters.computeIfAbsent(name, MetronomeMeter::new);
    }

    public MetronomeMeter meter(final String name, final MetronomeMeterConfiguration meterConfiguration) {
        return meters.computeIfAbsent(name, MetronomeMeter::new);
    }

    public MetronomeTimer timer(final String name) {
        return timers.computeIfAbsent(name, MetronomeTimer::new);
    }

    public MetronomeTimer timer(final String name, final MetronomeTimerConfiguration timerConfiguration) {
        return timers.computeIfAbsent(name, MetronomeTimer::new);
    }

    public void reset() {
        meters.clear();
        timers.clear();
    }
}
