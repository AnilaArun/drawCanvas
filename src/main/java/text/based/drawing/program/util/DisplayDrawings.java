package text.based.drawing.program.util;

public class DisplayDrawings {
    public static void displayCanvas(int row, int column, String[][] canvasString) {
        System.out.println();
        for(int i = 0; i< row; i++) {
            for(int j = 0; j< column; j++) {
                System.out.print(canvasString[i][j]);
            }
            System.out.println();
        }
    }
}
