package text.based.drawing.program;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import text.based.drawing.program.components.CanvasService;
import text.based.drawing.program.util.CommandLineProcessor;
import text.based.drawing.program.model.CanvasCoordinate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DrawingProgramIntegrationTest {
    DrawingProgram drawingProgram;
    CommandLineProcessor commandLineProcessor;

    @BeforeEach
    public void setUp() {
        drawingProgram = new DrawingProgram();
        commandLineProcessor = new CommandLineProcessor(new CanvasService(), new CanvasCoordinate());
    }

    @Test
    public void shouldReadCommandLineCharactersAndDrawCanvas() {
        String data = "C 20 5";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner sc = new Scanner(System.in);
        System.setIn(stdin);
        boolean isProcessed = drawingProgram.readAndProcessInputValues(sc, commandLineProcessor);
        assertTrue(isProcessed);
    }

    @Test
    public void shouldReadCommandLineCharactersAndDrawACanvasAndShouldDrawALineAndQuitWithCharQ() {
        String userInput = String.format("C 20 5%sL 1 2 1 5%sq",
                System.lineSeparator(),
                System.lineSeparator());
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        InputStream stdin = System.in;
        Scanner sc = new Scanner(System.in);
        System.setIn(stdin);
        boolean isProcessed = drawingProgram.readAndProcessInputValues(sc, commandLineProcessor);
        assertTrue(isProcessed);
    }

    @Test
    public void shouldReadCommandLineCharactersAndDrawACanvasAndShouldDrawALineAndARectangle() {
        String userInput = String.format("C 20 5%sL 1 2 1 5%sR 6 1 12 4%sq",
                System.lineSeparator(),
                System.lineSeparator(),
                System.lineSeparator());
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        InputStream stdin = System.in;
        Scanner sc = new Scanner(System.in);
        System.setIn(stdin);
        boolean isProcessed = drawingProgram.readAndProcessInputValues(sc, commandLineProcessor);
        assertTrue(isProcessed);
    }
}