public class BuildingSearch {

    public static void main(String[] args) throws InterruptedException {


        int floors = 4;
        int roomsPerFloor = 5;

        Building building = new Building(floors, roomsPerFloor);

        
        building.placeTarget(1, 2); 
        System.out.println("searching the building\n");

       
        Thread[] threads = new Thread[floors];
        SharedFlag foundFlag = new SharedFlag(); 

        for (int f = 0; f < floors; f++) {
            threads[f] = new Thread(new Searcher("Searcher-" + (f + 1),
                    building, f, foundFlag));
            threads[f].start();
        }

       
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("\nDone searching.");
    }
}

class SharedFlag {
    public volatile boolean found = false;
}


class Building {
    private final Floor[] floors;

    public Building(int floorCount, int roomsPerFloor) {
        floors = new Floor[floorCount];
        for (int i = 0; i < floorCount; i++) {
            floors[i] = new Floor(i + 1, roomsPerFloor);
        }
    }

    public Floor getFloor(int index) {
        return floors[index];
    }

    public void placeTarget(int floorIndex, int roomIndex) {
        floors[floorIndex].getRoom(roomIndex).setTarget(true);
    }
}


class Floor {
    private final int floorNumber;
    private final Room[] rooms;

    public Floor(int floorNumber, int roomCount) {
        this.floorNumber = floorNumber;
        rooms = new Room[roomCount];
        for (int i = 0; i < roomCount; i++) {
            rooms[i] = new Room(i + 1);
        }
    }

    public Room getRoom(int index) {
        return rooms[index];
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getRoomCount() {
        return rooms.length;
    }
}


class Room {
    private final int roomNumber;
    private boolean hasTarget = false;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean hasTarget() {
        return hasTarget;
    }

    public void setTarget(boolean value) {
        hasTarget = value;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}


class Searcher implements Runnable {

    private final String name;
    private final Building building;
    private final int floorIndex;
    private final SharedFlag flag;

    public Searcher(String name, Building building, int floorIndex, SharedFlag flag) {
        this.name = name;
        this.building = building;
        this.floorIndex = floorIndex;
        this.flag = flag;
    }

    @Override
    public void run() {

        Floor floor = building.getFloor(floorIndex);

        for (int r = 0; r < floor.getRoomCount(); r++) {

            if (flag.found) {
                System.out.println(name + ": stopping because someone else found it");
                return;
            }

            try {
                Thread.sleep(120);
            } catch (InterruptedException ignored) {}

            Room room = floor.getRoom(r);

            System.out.println(name + ": checking Floor " +
                floor.getFloorNumber() + ", Room " + room.getRoomNumber());

            if (room.hasTarget()) {
                flag.found = true;
                System.out.println(name + ": FOUND IT at Floor " +
                        floor.getFloorNumber() + ", Room " + room.getRoomNumber());
                return;
            }
        }
        System.out.println(name + ": done with Floor " +
                floor.getFloorNumber() + " (nothing here)");
    }
}