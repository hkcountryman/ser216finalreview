public class Thread2 implements Runnable {

    private String threadText;

    public Thread2(String threadText) {
        this.threadText = threadText;
    }

    public Thread2() {
        this("Do some threaded printing...");
    }

    @Override
    public void run() {
        System.out.println(threadText);
    }

    public static void main(String[] args) {
        Thread2 t1 = new Thread2();
        Thread2 t2 = new Thread2("Do some different threaded printing!");
        Thread t1Thread = new Thread(t1);
        Thread t2Thread = new Thread(t2);
        t1Thread.start();
        t2Thread.start();
        System.out.println("This is printing from the main process.");
    }

}
