package text.based.drawing.program.model;

import lombok.Data;

@Data
public class CanvasCoordinate {
    private int xCoordinate;
    private int yCoordinate;
    public CanvasCoordinate() {
    }
    public CanvasCoordinate(int xValue, int yValue) {
        this.xCoordinate = xValue;
        this.yCoordinate = yValue;
    }
}