import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static boolean running = true;

    public static void launchServer() throws IOException {
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("ServerSocket awaiting connections...");


        while (running) {

            Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
            System.out.println("Connection from " + socket + "!");

            // get the input stream from the connected socket
            InputStream inputStream = socket.getInputStream();
            // create a DataInputStream so we can read data from it.
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            // read the message from the socket
            String message = dataInputStream.readUTF();
            System.out.println("The message sent from the socket was: " + message);
        }


        System.out.println("Closing sockets.");
        ss.close();
    }


    public static void main(String[] args) throws IOException {
        launchServer();
    }
    
    
    public void close() {
        running = false;
    }
}
