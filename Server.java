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
    private static DataInputStream inStream;

    /**
     * Data to the client.
     * 
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/io/DataOutputStream.html
     */
    private static DataOutputStream outStream;

    /** The open port of the server. */
    private static int port;

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

    /**
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/net/ServerSocket.html
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/net/Socket.html
     */
    public static void main(String[] args) {
        // Read arguments
        readArgs(args);

        // Try (with resources) to open a ServerSocket
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            // Try (with resources) to open a Socket
            try (Socket socket = serverSocket.accept()) {
                // Initialize input and output streams
                inStream = new DataInputStream(socket.getInputStream());
                outStream = new DataOutputStream(socket.getOutputStream());

                // Use busy waiting to await bytes from client
                String name = "";
                byte[] bytes = new byte[256];
                boolean waiting = true;
                while (waiting) {
                    try {
                        // Read incoming bytes
                        inStream.read(bytes);
                        waiting = false;
                    } catch (SocketException e) {
                        ; // busy waiting
                    } catch (IOException e) {
                        System.out.println("Error reading name!");
                        e.printStackTrace();
                        Client.closeStreams(inStream, outStream);
                        System.exit(1);
                    }
                }

                // Convert bytes to a string and construct greeting, then convert that to bytes
                name = new String(bytes);
                String greeting = "Hello, " + name;
                bytes = greeting.getBytes();

                // Try to send bytes back to client
                try {
                    outStream.write(bytes);
                } catch (IOException e) {
                    System.out.println("Failed to greet " + name + " :(");
                    e.printStackTrace();
                    Client.closeStreams(inStream, outStream);
                    System.exit(1);
                }
            } catch (IOException e) {
                System.out.println("Error accepting connection!");
                e.printStackTrace();
                Client.closeStreams(inStream, outStream);
                System.exit(1);
            }
        } catch (IOException e) {
            System.out.println("Error opening socket!");
            e.printStackTrace();
            System.exit(1);
        } finally {
            // Close non-autocloseable resources
            Client.closeStreams(inStream, outStream);
        }
    }

}

