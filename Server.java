import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

    /**
     * Data from the client.
     * 
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/io/DataInputStream.html
     */
    private DataInputStream inStream;

    /**
     * Data to the client.
     * 
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/io/DataOutputStream.html
     */
    private DataOutputStream outStream;

    /** The open port of the server. */
    private static int port;

    /**
     * Server constructor.
     *
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/net/ServerSocket.html
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/net/Socket.html
     */
    public Server(int port) {
        // try setting serverSocket, socket, inStream, and outStream...
        try {
            //
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Reads the args array to populate the static port member.
     * 
     * @param args the args array from the main method
     */
    private static void readArgs(String[] args) {
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) { // catches ArrayIndexOutOfBoundsException or NumberFormatException
            System.out.println("Usage: java Server [server port]");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        readArgs(args);
        Server server = new Server(port);
        String name = "";
        byte[] bytes = new byte[256];
        boolean waiting = true;
        while (waiting) {
            // use busy waiting to wait for data from the client
            // (hint: what kind of Exception is thrown if we get no data?)
            try {
                //
            } catch ( /* ... */ ) {
                ; // busy waiting
            } catch (IOException e) {
                System.out.println("Error reading name!");
                e.printStackTrace();
                System.exit(1);
            }
        }
        name = new String(bytes);
        String greeting = "Hello, " + name;
        bytes = greeting.getBytes();
        // try writing the greeting back to the client
        try {
            //
        } catch (IOException e) {
            System.out.println("Failed to greet " + name + " :(");
            e.printStackTrace();
            System.exit(1);
        }
    }

}

