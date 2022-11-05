import Light.Light;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LightTest {
    @Test
    void initALightShouldBeOff() {
        // Act
        Light light = new Light();

        // Assert
        assertFalse(light.isOn());
    }

    @Test
    void GivenLightOffWhenTurnOnThenShouldBeOn(){
        // Arrange
        Light light = new Light();

        // Act
        light.TurnOn();

        // Assert
        assertTrue(light.isOn());
    }

    @Test
    void GivenLightOnWhenTurnOffThenShouldBeOff(){
        // Arrange
        Light light = new Light();
        light.TurnOn();

        // Act
        light.TurnOff();

        // Assert
        assertFalse(light.isOn());
    }
}
