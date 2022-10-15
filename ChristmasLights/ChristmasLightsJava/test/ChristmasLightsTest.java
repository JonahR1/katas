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
        AssertAllLightsByExpectedState(false, 0, 0, horizontalSize, verticalSize);
    }

    @Test
    void turnOnLightsFrom0_0_Through999_999_ShouldTurnOnAllLights() {
        // Act
        gridLights.TurnOn(0, 0, 999, 999);

        // Assert
        AssertAllLightsByExpectedState(true, 0, 0, horizontalSize, verticalSize);
    }

    @Test
    void turnOnLightsFrom0_0_Through999_0_ShouldTurnOnFirstLineOfLights() {
        // Act
        gridLights.TurnOn(0, 0, 999, 0);

        // Assert
        AssertAllLightsByExpectedState(true, 0, 0, horizontalSize, 1);
        AssertAllLightsByExpectedState(false, 0, 1, horizontalSize, verticalSize);
    }

    @Test
    void turnOnLightsFrom0_0_Through0_999_ShouldTurnOnFirstColumnOfLights() {
        // Act
        gridLights.TurnOn(0, 0, 0, 999);

        // Assert
        AssertAllLightsByExpectedState(true, 0, 0, 1, verticalSize);
        AssertAllLightsByExpectedState(false, 1, 0, horizontalSize, verticalSize);
    }

    @Test
    void turnOnLightsFrom0_999_Through999_999_ShouldTurnOnLastLineOfLights() {
        // Act
        gridLights.TurnOn(0, 999, 999, 999);

        // Assert
        AssertAllLightsByExpectedState(true, 0, 999, horizontalSize, verticalSize);
        AssertAllLightsByExpectedState(false, 0, 0, horizontalSize, 999);
    }

    @Test
    void turnOnLightsFrom999_0_Through999_999_ShouldTurnOnLastColumnOfLights() {
        // Act
        gridLights.TurnOn(999, 0, 999, 999);

        // Assert
        AssertAllLightsByExpectedState(true, 999, 0, horizontalSize, verticalSize);
        AssertAllLightsByExpectedState(false, 0, 0, 999, verticalSize);
    }

    private void AssertAllLightsByExpectedState(boolean expectedState, int columnStartingPoint, int lineStartingPoint, int horizontalLimit, int verticalLimit) {
        for (int columnLightPosition = columnStartingPoint; columnLightPosition < horizontalLimit; ++columnLightPosition) {
            for (int lineLightPosition = lineStartingPoint; lineLightPosition < verticalLimit; lineLightPosition++)
                assertEquals(expectedState, gridLights.isLightOn(columnLightPosition, lineLightPosition));
        }
    }
}
