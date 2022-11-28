import java.io.DataInputStream;
import java.io.DataOutputStream;

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
        try (/* TODO: Open a ServerSocket in this block */) {
            // Try (with resources) to open a Socket
            try (/* TODO: Open a Socket in this block */) {
                // Try (with resources) to open in/out streams
                try ( /* TODO: Open input and output streams in this block */ ) {
                    // Use busy waiting to await bytes from client
                    String name = "";
                    byte[] bytes = new byte[256];
                    boolean waiting = true;
                    while (waiting) {
                        try {
                            // TODO: Read incoming bytes
                        } catch (SocketException e) {
                            ; // busy waiting
                        } catch (/* TODO: What exceptions? */) {
                            Client.handleException(e, "");
                        }
                    }

                    // Convert bytes to a string and construct greeting, then convert that to bytes
                    name = new String(bytes);
                    String greeting = "Hello, " + name;
                    bytes = greeting.getBytes();

                    // Try to send bytes back to client
                    try {
                        // TODO:
                    } catch (/* TODO: What exceptions? */) {
                        Client.handleException(e, "");
                    }
                } catch (/* TODO: What exceptions? */) {
                    Client.handleException(e, "");
                }
            } catch (/* TODO: What exceptions? */) {
                Client.handleException(e, "");
            }
        } catch (/* TODO: What exceptions? */) {
            Client.handleException(e, "");
        }
    }

}

