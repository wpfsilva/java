import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class CheckPortTask implements Runnable {
    private String host;
    private int port;
    private int timeout;

    public CheckPortTask(String host, int port, int timeout) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    @Override
    public synchronized void run() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), timeout);
            System.out.println("Porta: " + port + " Aberta.");
            socket.close();
        } catch (ConnectException e) {
            System.out.println("Porta: " + port + " Fechada.");
        } catch (IOException e) {
            System.out.println("Porta: " + port + " Timeout.");
        }
    }
    private int getOpenPort(int openPort)
    {
        return openPort;
    }
}
