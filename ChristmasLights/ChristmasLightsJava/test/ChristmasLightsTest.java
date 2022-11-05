import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChristmasLightsTest {

    private GridLights gridLights;
    private int horizontalSize;
    private int verticalSize;

    @BeforeEach
    void setUp() {
        horizontalSize = 1000;
        verticalSize = 1000;
        gridLights = new GridLights(horizontalSize, verticalSize);
    }

    @Test
    void checkAllLightsAreOff() {
        AssertAllLightsByExpectedState(false, new LightPosition(0, 0), new LightPosition(horizontalSize, verticalSize));
    }

    @Test
    void turnOnLightsFrom0_0_Through999_999_ShouldTurnOnAllLights() {
        // Arrange
        LightPosition topLeftLimit = new LightPosition(0, 0);
        LightPosition bottomRightLimit = new LightPosition(999, 999);

        // Act
        gridLights.TurnOn(topLeftLimit, bottomRightLimit);

        // Assert
        AssertAllLightsByExpectedState(true, new LightPosition(0, 0), new LightPosition(horizontalSize, verticalSize));
    }

    @Test
    void turnOnLightsFrom0_0_Through999_0_ShouldTurnOnFirstLineOfLights() {
        // Arrange
        LightPosition topLeftLimit = new LightPosition(0, 0);
        LightPosition bottomRightLimit = new LightPosition(999, 0);

        // Act
        gridLights.TurnOn(topLeftLimit, bottomRightLimit);

        // Assert
        AssertAllLightsByExpectedState(true, new LightPosition(0, 0), new LightPosition(horizontalSize, 1));
        AssertAllLightsByExpectedState(false, new LightPosition(0, 1), new LightPosition(horizontalSize, verticalSize));
    }

    @Test
    void turnOnLightsFrom0_0_Through0_999_ShouldTurnOnFirstColumnOfLights() {
        // Arrange
        LightPosition topLeftLimit = new LightPosition(0, 0);
        LightPosition bottomRightLimit = new LightPosition(0, 999);

        // Act
        gridLights.TurnOn(topLeftLimit, bottomRightLimit);

        // Assert
        AssertAllLightsByExpectedState(true, new LightPosition(0, 0), new LightPosition(1, verticalSize));
        AssertAllLightsByExpectedState(false, new LightPosition(1, 0), new LightPosition(horizontalSize, verticalSize));
    }

    @Test
    void turnOnLightsFrom0_999_Through999_999_ShouldTurnOnLastLineOfLights() {
        //Arrange
        LightPosition topLeftLimit = new LightPosition(0, 999);
        LightPosition bottomRightLimit = new LightPosition(999, 999);

        // Act
        gridLights.TurnOn(topLeftLimit, bottomRightLimit);

        // Assert
        AssertAllLightsByExpectedState(true, new LightPosition(0, 999), new LightPosition(horizontalSize, verticalSize));
        AssertAllLightsByExpectedState(false, new LightPosition(0, 0), new LightPosition(horizontalSize, 999));
    }

    @Test
    void turnOnLightsFrom999_0_Through999_999_ShouldTurnOnLastColumnOfLights() {
        // Arrange
        LightPosition topLeftLimit = new LightPosition(999, 0);
        LightPosition bottomRightLimit = new LightPosition(999, 999);

        // Act
        gridLights.TurnOn(topLeftLimit, bottomRightLimit);

        // Assert
        AssertAllLightsByExpectedState(true, new LightPosition(999, 0), new LightPosition(horizontalSize, verticalSize));
        AssertAllLightsByExpectedState(false, new LightPosition(0, 0), new LightPosition(999, verticalSize));
    }

    private void AssertAllLightsByExpectedState(boolean expectedState, LightPosition start, LightPosition limit) {
        for (int columnLightPosition = start.column(); columnLightPosition < limit.column(); ++columnLightPosition) {
            for (int lineLightPosition = start.line(); lineLightPosition < limit.line(); lineLightPosition++)
                assertEquals(expectedState, gridLights.isLightOn(columnLightPosition, lineLightPosition));
        }
    }
}
