package text.based.drawing.program;

import text.based.drawing.program.components.CanvasService;
import text.based.drawing.program.util.CommandLineProcessor;
import text.based.drawing.program.model.CanvasCoordinate;

import java.util.Scanner;

import static text.based.drawing.program.config.DrawingConstants.*;

public class DrawingProgram {

    public static void main(String[] args) {
        DrawingProgram drawingProgram = new DrawingProgram();
        Scanner sc= new Scanner(System.in);
        CommandLineProcessor processor = new CommandLineProcessor( new CanvasService(), new CanvasCoordinate());
        drawingProgram.readAndProcessInputValues(sc, processor);
    }
    public boolean readAndProcessInputValues(Scanner sc, CommandLineProcessor processor) {
        boolean isProcessed = false;
        try {
            while (true) {
                System.out.print( ENTER_COMMAND);
                String newValues = sc.nextLine();
                isProcessed = processor.lineProcess(newValues.split(REGEX));
            }
        } catch(Exception e) {
            System.out.println("An error occurred .");
        }
        return isProcessed;
    }
}