public class Thread1 extends Thread {

    private String threadText;

    public Thread1(String threadText) {
        this.threadText = threadText;
    }

    public Thread1() {
        this("Do some threaded printing...");
    }

    // TODO: when this thread is run, print threadText

    public static void main(String[] args) {
        Thread1 t1; // TODO: instantiate
        Thread1 t2; // TODO: instantiate
        // TODO: start both threads
        System.out.println("This is printing from the main process.");
    }

}
