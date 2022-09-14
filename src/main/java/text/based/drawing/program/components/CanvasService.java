package text.based.drawing.program.components;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import text.based.drawing.program.model.CanvasCoordinate;
import text.based.drawing.program.util.DisplayDrawings;

import static text.based.drawing.program.config.DrawingConstants.*;
@Data
public class CanvasService implements Canvas {

    private String[][] canvasString;
    private int row;
    private int column;
    @Override
    public void drawACanvas(int width, int height) {
        setCanvasBoarderWidthAndHeight(width, height);
        canvasString = new String[row][column];
        for (int i = 0 ; i < row ; i++) {
            for (int j = 0; j < column; j++) {
                if ((i == 0) || (i == row - 1)) {
                    canvasString[i][j] = String.valueOf(HORIZONTAL_CANVAS_BOARDER);
                } else if ((j == column -1) || (j == 0 && i != row - 1)) {
                    canvasString[i][j] =String.valueOf(VERTICAL_CANVAS_BOARDER);
                } else {
                    canvasString[i][j] =String.valueOf(EMPTY_SPACE);
                }
            }
        }
        DisplayDrawings.displayCanvas(row, column, canvasString);
    }
    private void setCanvasBoarderWidthAndHeight(int width, int height) {
        this.setRow(height + 2);
        this.setColumn(width + 2);
    }
    public void drawLine(CanvasCoordinate firstCoordinate, CanvasCoordinate secondCoordinate) {
        //Draw vertical line if x cordinates are same.
        if (firstCoordinate.getXCoordinate() == secondCoordinate.getXCoordinate()) {
            if (firstCoordinate.getYCoordinate() < secondCoordinate.getYCoordinate()) {
                drawVerticalLine(firstCoordinate.getYCoordinate(), secondCoordinate.getYCoordinate(), firstCoordinate.getXCoordinate(), this.canvasString);
            }
            if (firstCoordinate.getYCoordinate() > secondCoordinate.getYCoordinate()) {
                drawVerticalLine(secondCoordinate.getYCoordinate(), firstCoordinate.getYCoordinate(), firstCoordinate.getXCoordinate(), this.canvasString);
            }
        }

        //Draw horizontal line if y cordinates are same
        if (firstCoordinate.getXCoordinate() < secondCoordinate.getXCoordinate()) {
            drawHorizontalLine(firstCoordinate.getXCoordinate(), secondCoordinate.getXCoordinate(), firstCoordinate.getYCoordinate(), this.canvasString);
        }
        if (firstCoordinate.getXCoordinate() > secondCoordinate.getXCoordinate()) {
            drawHorizontalLine(secondCoordinate.getXCoordinate(), firstCoordinate.getXCoordinate(), firstCoordinate.getYCoordinate(), this.canvasString);
        }
        DisplayDrawings.displayCanvas(row, column, canvasString);
    }
    public void drawRectangle(CanvasCoordinate firstCoordinate, CanvasCoordinate secondCoordinate) {
        drawHorizontalLine(firstCoordinate.getXCoordinate(), secondCoordinate.getXCoordinate(), firstCoordinate.getYCoordinate(), this.canvasString);
        drawVerticalLine(firstCoordinate.getYCoordinate(), secondCoordinate.getYCoordinate(), secondCoordinate.getXCoordinate(), this.canvasString);
        drawHorizontalLine(firstCoordinate.getXCoordinate(), secondCoordinate.getXCoordinate(), secondCoordinate.getYCoordinate(), this.canvasString);
        drawVerticalLine(firstCoordinate.getYCoordinate(), secondCoordinate.getYCoordinate(), firstCoordinate.getXCoordinate(), this.canvasString);

        DisplayDrawings.displayCanvas(row, column, canvasString);
    }
}

