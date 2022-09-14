package text.based.drawing.program.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import text.based.drawing.program.model.CanvasCoordinate;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class CanvasServiceTest {
    CanvasService canvasService;

    @BeforeEach
    public void setUp() {
        canvasService = new CanvasService();
    }

    @Test
    void shouldDrawACanvasWithTheWidthAndHeightProvided() {
        canvasService.drawACanvas(5, 3);
        assertThat(canvasService.getColumn()).isEqualTo(7);
        assertThat(canvasService.getRow()).isEqualTo(5);
    }

    @Test
    void shouldDrawLine() {
        canvasService.drawACanvas(5, 3);
        assertThat(canvasService.getColumn()).isEqualTo(7);
        assertThat(canvasService.getRow()).isEqualTo(5);
        canvasService.drawLine(new CanvasCoordinate(5, 1), new CanvasCoordinate(5, 3));
        assertThat(canvasService.getCanvasString()[1][5]).isEqualTo("x");
    }

    @Test
    void shouldDrawRectangle() {
        canvasService.drawACanvas(5, 3);
        canvasService.drawRectangle(new CanvasCoordinate(1, 1), new CanvasCoordinate(5, 3));
        assertThat(canvasService.getCanvasString()[3][5]).isEqualTo("x");
    }
}