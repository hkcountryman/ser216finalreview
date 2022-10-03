import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client {

    /**
     * Data from the server.
     * 
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/io/DataInputStream.html
     */
    private DataInputStream inStream;

    /**
     * Data to the server.
     * 
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/io/DataOutputStream.html
     */
    private DataOutputStream outStream;

    /** The hostname of the server. */
    private static String host;

    /** The open port of the server. */
    private static int port;

    /**
     * Client constructor.
     * 
     * @param host the hostname of the server
     * @param port the open port of the server
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/net/InetAddress.html
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/net/Socket.html
     */
    public Client(String host, int port) {
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName(host);
            Socket socket = new Socket(ip, port);
            inStream = new DataInputStream(socket.getInputStream());
            outStream = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Reads the args array to populate the static host and port members.
     * 
     * @param args the args array from the main method
     */
    private static void readArgs(String[] args) {
        try {
            host = args[0];
            port = Integer.parseInt(args[1]);
        } catch (Exception e) { // catches ArrayIndexOutOfBoundsException or NumberFormatException
            System.out.println("Usage: java Client [server hostname] [server port]");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        readArgs(args);
        Client client = new Client(host, port);
        System.out.println("What is your name?");
        String name = "";
        try (Scanner scanner = new Scanner(System.in)) {
            name = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error reading input!");
            e.printStackTrace();
            System.exit(1);
        }
        byte[] bytes = name.getBytes();
        try {
            client.outStream.write(bytes);
        } catch (IOException e) {
            System.out.println("Error writing name!");
            e.printStackTrace();
            System.exit(1);
        }
        bytes = new byte[256];
        boolean waiting = true;
        while (waiting) {
            try {
                client.inStream.read(bytes);
                waiting = false;
            } catch (SocketException e) {
                ; // busy waiting
            } catch (IOException e) {
                System.out.println("Server failed to greet you :(");
                e.printStackTrace();
                System.exit(1);
            }
        }
        String greeting = new String(bytes);
        System.out.println(greeting);
    }

}

