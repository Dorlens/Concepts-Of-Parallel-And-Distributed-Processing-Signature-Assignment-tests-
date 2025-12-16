import java.util.Random;

class FloorSearch extends Thread {
    int floor;
    int target;

    FloorSearch(int floor, int target) {
        this.floor = floor;
        this.target = target;
    }

    public void run() {
        Random rand = new Random();
        int rooms = 10;

        for (int room = 1; room <= rooms; room++) {
            try {
                Thread.sleep(rand.nextInt(100)); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (room == target) {
                System.out.println("Floor " + floor + "  target found in the room " + room);
                break; 
            }
        }
    }
}

public class ParallelApproach {
    public static void main(String[] args) {
        int floors = 5; 
        int targetRoom = 7; 

        Thread[] threads = new Thread[floors];

        
        for (int i = 0; i < floors; i++) {
            threads[i] = new FloorSearch(i + 1, targetRoom);
            threads[i].start();
        }

        
        for (int i = 0; i < floors; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Donee searching all the floors!");
    }
}