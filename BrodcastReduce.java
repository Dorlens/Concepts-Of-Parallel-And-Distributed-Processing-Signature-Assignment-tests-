import java.util.*;
import java.util.stream.*;
import java.util.concurrent.ThreadLocalRandom;



public class BrodcastReduce {
    static final int MAX = 1_000_000;
   
    public static void main(String[] args) {
        Broadcast(MAX);
        parallel_Broadcast(MAX);
    }
    private static void Broadcast(int tasks) {
        Long startTime = System.nanoTime();// 1 billionth of a second
         IntStream.range(0,tasks).map(task -> ThreadLocalRandom.current().nextInt(1, tasks)+ tasks).toArray();
        Long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.printf("Broadcasting task %d took %d microseconds%n", tasks, totalTime / 1000);
    }

     private static void parallel_Broadcast(int tasks) {
        Long startTime = System.nanoTime();// 1 billionth of a second
         IntStream.range(0,tasks).parallel().map(task -> ThreadLocalRandom.current().nextInt(1, tasks)+ tasks).toArray();
        Long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.printf("Parallel task %d took %d microseconds%n", tasks, totalTime / 1000);
    }
}

