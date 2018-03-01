package com.rfksystems.metronome;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MetronomeConfiguration {
    private transient Factory<ConcurrentMap<String, MetronomeMeter>> meterMapFactory;
    private transient Factory<ConcurrentMap<String, MetronomeTimer>> timerMapFactory;

    public MetronomeConfiguration withMeterMapFactory(Factory<ConcurrentMap<String, MetronomeMeter>> meterMapFactory) {
        this.meterMapFactory = meterMapFactory;
        return this;
    }

    public MetronomeConfiguration withTimerMapFactory(Factory<ConcurrentMap<String, MetronomeTimer>> timerMapFactory) {
        this.timerMapFactory = timerMapFactory;
        return this;
    }

    public ConcurrentMap<String, MetronomeMeter> createMeterMap() {
        return null != meterMapFactory ? meterMapFactory.getInstance() : new ConcurrentHashMap<>();
    }

    public ConcurrentMap<String, MetronomeTimer> createTimerMap() {
        return null != timerMapFactory ? timerMapFactory.getInstance() : new ConcurrentHashMap<>();
    }
}
