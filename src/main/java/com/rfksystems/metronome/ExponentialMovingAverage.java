package com.rfksystems.metronome;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class ExponentialMovingAverage implements Serializable {
    private final long windowNanos;

    // Mutable state
    private volatile long lastNanos;
    private volatile double average = .0;

    /**
     * Creates a moving average of samples over a {@code window} for the {@code timeUnit}.
     */
    public ExponentialMovingAverage(final long window, final TimeUnit timeUnit) {
        this.windowNanos = timeUnit.toNanos(window);
    }

    /**
     * Updates the average with the {@code sample}.
     */
    public synchronized void update(final double sample) {
        final long now = System.nanoTime();

        if (0 == lastNanos) {
            average = sample;
            lastNanos = now;
            return;
        }

        final long elapsedNanos = now - lastNanos;
        final double coefficient = Math.exp(-1.0 * ((double) elapsedNanos / windowNanos));
        average = (1.0 - coefficient) * sample + coefficient * average;
        lastNanos = now;
    }

    /**
     * Returns the moving average.
     */
    public double get() {
        return average;
    }

    /**
     * Tick the internal moving average clock back by the {@code duration}. Useful for testing.
     */
    void tick(final long duration, final TimeUnit timeUnit) {
        if (lastNanos != 0)
            lastNanos -= timeUnit.toNanos(duration);
    }
}