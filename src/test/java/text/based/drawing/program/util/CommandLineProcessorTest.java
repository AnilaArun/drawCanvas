package text.based.drawing.program.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import text.based.drawing.program.components.CanvasService;
import text.based.drawing.program.exception.NegativeParameterException;
import text.based.drawing.program.exception.OutOfCanvasCharacterException;
import text.based.drawing.program.model.CanvasCoordinate;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static text.based.drawing.program.config.DrawingConstants.REGEX;

class CommandLineProcessorTest {
    CommandLineProcessor commandLineProcessor;
    CanvasService canvasService;
    CanvasCoordinate coordinate;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        canvasService = new CanvasService();
        coordinate = new CanvasCoordinate();
        commandLineProcessor = new CommandLineProcessor(canvasService, coordinate);System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void shouldDrawCanvas() {
        String canvasString = "C 5 3";
        boolean isProcessed = commandLineProcessor.lineProcess(canvasString.split(REGEX));
        assertTrue(isProcessed);
        assertThat(canvasService.getColumn()).isEqualTo(7);
        assertThat(canvasService.getRow()).isEqualTo(5);
        assertThat(coordinate.getXCoordinate()).isEqualTo(5);
        assertThat(coordinate.getYCoordinate()).isEqualTo(3);
    }

    @Test
    public void shouldDrawLine() {
        String canvasString = "C 5 3";
        boolean isProcessed = commandLineProcessor.lineProcess(canvasString.split(REGEX));
        assertTrue(isProcessed);
        String LineString = "L 1 1 1 2";
        boolean isLineCreated = commandLineProcessor.lineProcess(LineString.split(REGEX));
        assertTrue(isLineCreated);
        assertThat(canvasService.getColumn()).isEqualTo(7);
        assertThat(canvasService.getRow()).isEqualTo(5);
        assertThat(coordinate.getXCoordinate()).isEqualTo(5);
        assertThat(coordinate.getYCoordinate()).isEqualTo(3);
    }

    @Test
    public void shouldDrawRectangle() {
        String canvasString = "C 5 3";
        boolean isProcessed = commandLineProcessor.lineProcess(canvasString.split(REGEX));
        assertTrue(isProcessed);
        String LineString = "R 1 1 4 3";
        boolean isLineCreated = commandLineProcessor.lineProcess(LineString.split(REGEX));
        assertTrue(isLineCreated);
        assertThat(canvasService.getColumn()).isEqualTo(7);
        assertThat(canvasService.getRow()).isEqualTo(5);
        assertThat(coordinate.getXCoordinate()).isEqualTo(5);
        assertThat(coordinate.getYCoordinate()).isEqualTo(3);
    }
    @Test
    public void shouldNotProcessDrawCanvasIfInputParametersAreLessThanThreeChars() {
        String canvasString = "C 5";
        String outPutString ="Please enter 3 parameters separated with spaces as C as first character and two number parameters" +
                "  to draw the canvas as its width and height";
        boolean isProcessed = commandLineProcessor.lineProcess(canvasString.split(REGEX));
        assertThat(isProcessed).isEqualTo(false);
        assertThat(outPutString).isEqualTo(outputStreamCaptor.toString().trim());
    }
    @Test
    public void shouldThrowAnExceptionIfDrawCanvasContainNegativeParameters() {
        String canvasString = "C -5 3";
        NegativeParameterException exception = assertThrows(NegativeParameterException.class, () -> {
            commandLineProcessor.lineProcess(canvasString.split(REGEX));
        });

        String exceptedMessage = "Accepts only positive integers";
        String exceptionMessage = exception.getMessage();
        assertThat(exceptedMessage).isEqualTo(exceptionMessage);
    }

    @Test
    public void shouldThrowAnExceptionIfDrawLineParametersAreOutsideOfCanvasBoundary() {
        String canvasString = "C 5 3";
        boolean isProcessed = commandLineProcessor.lineProcess(canvasString.split(REGEX));
        assertThat(isProcessed).isEqualTo(true);
        String lineString = "L 6 1 1 2";
        OutOfCanvasCharacterException exception = assertThrows(OutOfCanvasCharacterException.class, () -> {
            commandLineProcessor.lineProcess(lineString.split(REGEX));
        });

        String exceptedMessage = "X coordinate is outside of Canvas boundary";
        String exceptionMessage = exception.getMessage();
        assertThat(exceptedMessage).isEqualTo(exceptionMessage);
        String newLineString = "L 1 4 1 2";
        OutOfCanvasCharacterException newException = assertThrows(OutOfCanvasCharacterException.class, () -> {
            commandLineProcessor.lineProcess(newLineString.split(REGEX));
        });
        String exceptedNewMessage = "Y coordinate is outside of Canvas boundary";
        String exceptionNewMessage = newException.getMessage();
        assertThat(exceptedNewMessage).isEqualTo(exceptionNewMessage);
    }

    @Test
    public void shouldThrowNumberFormatExceptionIfInputParametersHasNoNumbers() {
        String canvasString = "C L R";
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            commandLineProcessor.lineProcess(canvasString.split(REGEX));
        });

        String exceptedMessage = "Accepts only numbers as parameters after char C";
        String exceptionMessage = exception.getMessage();
        assertThat(exceptedMessage).isEqualTo(exceptionMessage);
    }
}