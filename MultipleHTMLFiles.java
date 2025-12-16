import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

class HTMLTagCounter implements Runnable {
    private static AtomicInteger threadCounter = new AtomicInteger(1);
    private File file;
    private int threadNumber;

    public HTMLTagCounter(File file) {
        this.file = file;
        this.threadNumber = threadCounter.getAndIncrement();
    }

    @Override
    public void run() {
        int tagCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
               
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '<') {
                        tagCount++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getName());
        }
        System.out.println("Thread Number: " + threadNumber + ", File Name: " + file.getName() + ", Tag Count: " + tagCount);
    }
}

public class MultipleHTMLFiles {
    public static void main(String[] args) {
       
        if (args.length == 0) {
            System.out.println("give one HTML file as an argument.");
            return;
        }

        Thread[] threads = new Thread[args.length];

       
        for (int i = 0; i < args.length; i++) {
            File file = new File(args[i]);
            threads[i] = new Thread(new HTMLTagCounter(file));
            threads[i].start();
        }

       
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
        }
    }
}
