package text.based.drawing.program.components;

import text.based.drawing.program.model.CanvasCoordinate;

import static text.based.drawing.program.config.DrawingConstants.LINE_CHAR;
public interface Canvas {
    void drawACanvas(int width, int height);
    void drawLine(CanvasCoordinate firstCoordinate, CanvasCoordinate secondCoordinate);
    void drawRectangle(CanvasCoordinate firstCoordinate, CanvasCoordinate secondCoordinate);
    default void drawVerticalLine(int from, int to, int column, String[][] canvasString) {
        for(int i=from; i <= to; i++) {
            canvasString[i][column] = String.valueOf(LINE_CHAR);
        }
    }
    default void drawHorizontalLine(int from, int to, int row, String[][] canvasString) {
        for(int i=from; i <=to; i++) {
            canvasString[row][i] = String.valueOf(LINE_CHAR);
        }
    }
}
