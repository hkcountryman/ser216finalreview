import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;

public class Client {

    /**
     * Data from the server.
     * 
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/io/DataInputStream.html
     */
    private static DataInputStream inStream;

    /**
     * Data to the server.
     * 
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/io/DataOutputStream.html
     */
    private static DataOutputStream outStream;

    /** The hostname of the server. */
    private static String host;

    /** The open port of the server. */
    private static int port;

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

    /**
     * Does terrible, terrible exception handling.
     * 
     * @param e the exception to handle
     * @param msg a message to print
     */
    public static void handleException(Exception e, String msg) {
        System.out.println(msg);
        e.printStackTrace();
        System.exit(1);
    }

    /**
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/net/InetAddress.html
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/net/Socket.html
     * @see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/util/Scanner.html
     */
    public static void main(String[] args) {
        // Read arguments
        readArgs(args);

        // Try to get host's InetAddress
        InetAddress ip = null; // TODO: assign the InetAddress

        // Try (with resources) to open a Socket
        try (/* TODO: Open a Socket in this block */) {
            // Try (with resources) to open in/out streams
            try ( /* TODO: Open input and output streams in this block */ ) {
                // Try (with resources) to get name from user using Scanner class
                System.out.println("What is your name?");
                String name = "";
                try (/* TODO: Open a Scanner in this block */) {
                    name = scanner.nextLine();
                } catch (/* TODO: What exceptions? */) {
                    handleException(e, "");
                }

                // Convert name to bytes and try to write it to server
                byte[] bytes = name.getBytes();
                try {
                    // TODO: 
                } catch (/* TODO: What exceptions? */) {
                    handleException(e, "");
                }

                // Use busy waiting to await bytes from server
                bytes = new byte[256];
                boolean waiting = true;
                while (waiting) {
                    try {
                        // TODO: Read incoming bytes
                    } catch (SocketException e) {
                        ; // busy waiting
                    } catch (/* TODO: What exceptions? */) {
                        handleException(e, "");
                    }
                }

                // Convert bytes to greeting and display
                String greeting = new String(bytes);
                System.out.println(greeting);
            } catch (/* TODO: What exceptions? */) {
                handleException(e, "");
            }
        } catch (/* TODO: What exceptions? */) {
            handleException(e, "");
        }
    }

}

