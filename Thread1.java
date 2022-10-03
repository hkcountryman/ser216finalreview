public class Thread1 extends Thread {

    private String threadText;

    public Thread1(String threadText) {
        this.threadText = threadText;
    }

    public Thread1() {
        this("Do some threaded printing...");
    }

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread1 t2 = new Thread1("Do some different threaded printing!");
        // ...
        System.out.println("This is printing from the main process.");
    }

}
