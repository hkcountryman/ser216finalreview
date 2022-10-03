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

    /**
     * The server's socket.
     * 
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/net/ServerSocket.html
     */
    private ServerSocket serverSocket;

    /**
     * The client's socket.
     * 
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/net/Socket.html
     */
    private Socket socket;

    /** The open port of the server. */
    private static int port;

    /**
     * 
     * @param port
     */
    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            inStream = new DataInputStream(socket.getInputStream());
            outStream = new DataOutputStream(socket.getOutputStream());
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
            try {
                server.inStream.read(bytes);
                waiting = false;
            } catch (SocketException e) {
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
        try {
            server.outStream.write(bytes);
        } catch (IOException e) {
            System.out.println("Failed to greet " + name + " :(");
            e.printStackTrace();
            System.exit(1);
        }
    }

}

