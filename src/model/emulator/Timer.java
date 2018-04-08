package model.emulator;

class Timer implements Runnable {

    private volatile int value;

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
            if (value > 0) {
                value -= 1;
            } else {

            }
        }
    }
}
