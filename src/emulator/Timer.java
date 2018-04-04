package emulator;

class Timer implements Runnable {

    private int value;

    public void setValue(int value) {
        synchronized (this) {
            this.value = value;
        }
    }

    public int getValue() {
        synchronized (this) {
            return value;
        }
    }

    @Override
    public void run() {
        while (value > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {}
            synchronized (this) {
                value -= 1;
            }
        }
    }
}
