package model.emulator;

import java.util.TimerTask;

class Timer {

    private volatile int value;
    private java.util.Timer timer = new java.util.Timer();

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (value > 0) {
                    value = value - 1;
                }
            }
        }, 10, 10);
    }

    /*@Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(10);
                if (value > 0) {
                    value -= 1;
                }
            }
        } catch (InterruptedException e) {}
    }*/
}