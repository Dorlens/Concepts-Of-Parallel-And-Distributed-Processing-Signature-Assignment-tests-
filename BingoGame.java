import java.util.Random;

public class BingoGame {

    
    static final int MAX_SIZE = 5;

   
    static class BingoCell {
        int number;
        boolean isMarked;
    }

    public static void main(String[] args) {

        final int MAX_ITERATIONS = 10;

        BingoCell[][] bingoCard = new BingoCell[MAX_SIZE][MAX_SIZE];

       
        for (int r = 0; r < MAX_SIZE; r++) {
            for (int c = 0; c < MAX_SIZE; c++) {
                bingoCard[r][c] = new BingoCell();
            }
        }

        generateBingoCard(bingoCard);
        displayBingoCard(bingoCard);

        for (int i = 1; i <= MAX_ITERATIONS; i++) {
            int choice = i;

            Thread t = new Thread(() -> {
                switch (choice) {
                    case 1:
                        checkForBingoFirstRow(bingoCard);
                        break;
                    case 2:
                        checkForDiagonalBingoLR(bingoCard);
                        break;
                    default:
                        System.out.println("\nUnknown choice: " + choice);
                }
            });

            t.start();
        }

        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // -------------------------
    static void generateBingoCard(BingoCell[][] bingoCard) {
        Random rand = new Random();

        for (int row = 0; row < MAX_SIZE; row++) {
            for (int column = 0; column < MAX_SIZE; column++) {

                int baseNbr = (row + 1) + (column * 15);
                int randomNbr = rand.nextInt(11); // 0–10
                int cellNbr = baseNbr + randomNbr;

                bingoCard[row][column].number = cellNbr;

                if (cellNbr % 3 == 0 || cellNbr % 5 == 0 || cellNbr % 7 == 0) {
                    bingoCard[row][column].isMarked = true;
                } else {
                    bingoCard[row][column].isMarked = false;
                }
            }
        }
    }

    // -------------------------
    static void displayBingoCard(BingoCell[][] bingoCard) {
        System.out.println(" Y    O   U    W  O   N   B    I    N    G    O");
        System.out.println(" --   --   --   - - -    --    --   --   -- --");

        for (int row = 0; row < MAX_SIZE; row++) {
            for (int column = 0; column < MAX_SIZE; column++) {

                if (bingoCard[row][column].isMarked)
                    System.out.print("(");
                else
                    System.out.print(" ");

                if (row == 2 && column == 2)
                    System.out.print("**");
                else
                    System.out.printf("%2d", bingoCard[row][column].number);

                if (bingoCard[row][column].isMarked)
                    System.out.print(") ");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
    }

    // ------------------------------------------------------
    static void checkForBingoFirstRow(BingoCell[][] bingoCard) {
        int count = 0;

        for (int column = 0; column < MAX_SIZE; column++) {
            if (bingoCard[0][column].isMarked) {
                count++;
            }
        }

       String threadName = Thread.currentThread().getName();
        if (count == 5)
            System.out.println("\n(" + threadName + ") !!!CONGRATULATIONS!!! Bingo in first row");
        else
            System.out.println("\n(" + threadName + ") No Bingo in first row");
    }

    // ------------------------------------------------------
    static void checkForDiagonalBingoLR(BingoCell[][] bingoCard) {
        int count = 0;

        if (bingoCard[0][0].isMarked) count++;
        if (bingoCard[1][1].isMarked) count++;
        if (bingoCard[2][2].isMarked) count++;
        if (bingoCard[3][3].isMarked) count++;
        if (bingoCard[4][4].isMarked) count++;

        String threadName = Thread.currentThread().getName();

        if (count == 5)
            System.out.println("\n(" + threadName + ") !!!CONGRATULATIONS!!! Bingo in LR diagonal");
        else
            System.out.println("\n(" + threadName + ") No Bingo in LR diagonal");
    }
}