import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
     * Closes an input and output stream.
     * 
     * @param inStream the input stream
     * @param outStream the output stream
     */
    public static void closeStreams(DataInputStream inStream, DataOutputStream outStream) {
        try {
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host!");
            e.printStackTrace();
            System.exit(1);
        }

        // Try (with resources) to open a Socket
        try (Socket socket = new Socket(ip, port)) {
            // Initialize input and output streams
            inStream = new DataInputStream(socket.getInputStream());
            outStream = new DataOutputStream(socket.getOutputStream());

            // Try (with resources) to get name from user using Scanner class
            System.out.println("What is your name?");
            String name = "";
            try (Scanner scanner = new Scanner(System.in)) {
                name = scanner.nextLine();
            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("Error reading input!");
                e.printStackTrace();
                closeStreams(inStream, outStream);
                System.exit(1);
            }

            // Convert name to bytes and try to write it to server
            byte[] bytes = name.getBytes();
            try {
                outStream.write(bytes);
            } catch (IOException e) {
                System.out.println("Error writing name!");
                e.printStackTrace();
                closeStreams(inStream, outStream);
                System.exit(1);
            }

            // Use busy waiting to await bytes from server
            bytes = new byte[256];
            boolean waiting = true;
            while (waiting) {
                try {
                    // Read incoming bytes
                    inStream.read(bytes);
                    waiting = false;
                } catch (SocketException e) {
                    ; // busy waiting
                } catch (IOException e) {
                    System.out.println("Server failed to greet you :(");
                    e.printStackTrace();
                    closeStreams(inStream, outStream);
                    System.exit(1);
                }
            }

            // Convert bytes to greeting and display
            String greeting = new String(bytes);
            System.out.println(greeting);
        } catch (IOException e) {
            System.out.println("Error opening socket!");
            e.printStackTrace();
            System.exit(1);
        } finally {
            // Close non-autocloseable resources
            closeStreams(inStream, outStream);
        }
    }

}

