import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PortScanner {
    public static void main(String[] args) {
        String host = "192.168.100.1";
        int timeout = 2000;
        int startPort = 1;
        int endPort = 1024;
        int numThreads = 10;

        try (ExecutorService executor = Executors.newFixedThreadPool(numThreads)) {
            for (int port = startPort; port <= endPort; port++) {
                CheckPortTask task = new CheckPortTask(host, port, timeout);
                executor.submit(task);
            }
            executor.shutdown();
            try {
                boolean b = executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                if (b)
                {
                    System.out.println("Scanner Terminou suas tarefas");
                }else
                {
                    System.out.println("Ocorreu algum erro durante o processo");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}