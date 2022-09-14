package text.based.drawing.program.util;

import lombok.AllArgsConstructor;
import text.based.drawing.program.components.CanvasService;
import text.based.drawing.program.exception.NegativeParameterException;
import text.based.drawing.program.exception.OutOfCanvasCharacterException;
import text.based.drawing.program.model.CanvasCoordinate;

import static text.based.drawing.program.config.DrawingConstants.*;

@AllArgsConstructor
public class CommandLineProcessor {
    private CanvasService canvasService;
    private CanvasCoordinate canvasCoordinate;

    public boolean lineProcess(String[] values) {
        boolean isProcessed = false;
        switch(values[0].toUpperCase()) {
            case CANVAS_CHAR :
                if (values.length != 3) {
                    System.out.println(" Please enter 3 parameters separated with spaces as C as first character and two number parameters" +
                            "  to draw the canvas as its width and height");
                } else {
                    try {
                        if (Integer.parseInt(values[1]) <= 0 || Integer.parseInt(values[2]) <= 0) {
                            throw new NegativeParameterException("Accepts only positive integers");
                        }
                        canvasCoordinate.setXCoordinate(Integer.parseInt(values[1]));
                        canvasCoordinate.setYCoordinate(Integer.parseInt(values[2]));
                        createCanvas(Integer.parseInt(values[1]), Integer.parseInt(values[2]));
                        isProcessed = true;
                    } catch(NumberFormatException e) {
                        throw new NumberFormatException("Accepts only numbers as parameters after char C");
                    }
                }
                break;
            case DRAW_LINE_CHAR :
                if (values.length != 5) {
                    System.out.println(" Please enter 5 parameters separated with spaces and L as the first character and four number parameters" +
                            "  to draw a line ");
                } else {
                    try {
                        if (Integer.parseInt(values[1]) > canvasCoordinate.getXCoordinate() || Integer.parseInt(values[3]) > canvasCoordinate.getXCoordinate()) {
                            throw new OutOfCanvasCharacterException("X coordinate is outside of Canvas boundary");
                        } else if (Integer.parseInt(values[2]) > canvasCoordinate.getYCoordinate() || Integer.parseInt(values[4]) > canvasCoordinate.getYCoordinate()) {
                            throw new OutOfCanvasCharacterException("Y coordinate is outside of Canvas boundary");
                        }
                        canvasService.drawLine(new CanvasCoordinate(Integer.parseInt(values[1]), Integer.parseInt(values[2])), new CanvasCoordinate(Integer.parseInt(values[3]), Integer.parseInt(values[4])));
                        isProcessed = true;
                    } catch(NumberFormatException e) {
                        throw new NumberFormatException("Accepts only numbers as parameters after char L");
                    }
                }
                break;
            case RECTANGLE_CHAR :
                try {
                    if (values.length != 5) {
                        System.out.println(" Please enter 5 parameters separated with spaces and R as the first character and four number parameters" +
                                "  to draw a rectangle");
                    } else {
                        if (Integer.parseInt(values[1]) > canvasCoordinate.getXCoordinate() || Integer.parseInt(values[3]) > canvasCoordinate.getXCoordinate()) {
                            throw new OutOfCanvasCharacterException("X coordinate is outside of Canvas boundary");
                        } else if (Integer.parseInt(values[2]) > canvasCoordinate.getYCoordinate() || Integer.parseInt(values[4]) > canvasCoordinate.getYCoordinate()) {
                            throw new OutOfCanvasCharacterException("Y coordinate is outside of Canvas boundary");
                        }
                        canvasService.drawRectangle(new CanvasCoordinate(Integer.parseInt(values[1]), Integer.parseInt(values[2])), new CanvasCoordinate(Integer.parseInt(values[3]), Integer.parseInt(values[4])));
                        isProcessed = true;
                    }
                } catch(NumberFormatException e) {
                        throw new NumberFormatException("Accepts only numbers as parameters after char R");
                }
                break;
            case QUIT_CHAR :
                System.exit(0);
            default:
                isProcessed = false;
                System.out.println("The program looks for the Commands with first character as C L R or Q followed by space separated digits. Please retry.");
       }
       return isProcessed;
    }
    private void createCanvas(int width, int height) {
        canvasService.drawACanvas(width, height);
    }
}
