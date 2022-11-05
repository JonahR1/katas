import Light.Light;
import TurnOnLightCommand.TurnOnLightCommand;

import java.util.ArrayList;
import java.util.function.Consumer;

public class GridLights {
    private ArrayList<ArrayList<Light>> arrayLights;

    public GridLights(int horizontalSize, int verticalSize) {
        this.arrayLights = generateArrayLights(horizontalSize, verticalSize);
    }

    public boolean isLightOn(int columnLightPosition, int lineLightPosition) {
        return arrayLights.get(columnLightPosition).get(lineLightPosition).isOn();
    }

    public void TurnOn(LightPosition topLeftLimit, LightPosition bottomRightLimit) {
        ExecuteCommandForLightsInsideLimits(topLeftLimit, bottomRightLimit, light -> new TurnOnLightCommand(light).execute());
    }

    public void TurnOff(LightPosition topLeftLimit, LightPosition bottomRightLimit) {
        ExecuteCommandForLightsInsideLimits(topLeftLimit, bottomRightLimit, light -> new TurnOffLightCommand(light).execute());
    }

    private void ExecuteCommandForLightsInsideLimits(LightPosition topLeftLimit, LightPosition bottomRightLimit, Consumer<Light> commandConsumer) {
        for (int columnLightPosition = 0; columnLightPosition < arrayLights.size(); ++columnLightPosition)
            for (int lineLightPosition = 0; lineLightPosition < arrayLights.get(columnLightPosition).size(); lineLightPosition++)
                if (isLightPositionInsideLimits(topLeftLimit, bottomRightLimit, new LightPosition(columnLightPosition, lineLightPosition)))
                    commandConsumer.accept(this.arrayLights.get(columnLightPosition).get(lineLightPosition));
    }

    private ArrayList<ArrayList<Light>> generateArrayLights(int horizontalSize, int verticalSize) {
        ArrayList<ArrayList<Light>> arrayLights = new ArrayList<ArrayList<Light>>(horizontalSize);

        for (int columnVerticalLights = 0; columnVerticalLights < horizontalSize; ++columnVerticalLights)
            arrayLights.add(generateVerticalArrayLights(verticalSize));

        return arrayLights;
    }

    private ArrayList<Light> generateVerticalArrayLights(int verticalSize) {
        ArrayList<Light> verticalArrayLights = new ArrayList<Light>(verticalSize);

        for (int verticalLightsPosition = 0; verticalLightsPosition < verticalSize; ++verticalLightsPosition)
            verticalArrayLights.add(new Light());

        return verticalArrayLights;
    }

    private boolean isLightPositionInsideLimits(LightPosition topLeftLimit, LightPosition bottomRightLimit, LightPosition light) {
        boolean lightUnderOrAtBottomRightLimit = light.line() <= bottomRightLimit.line() && light.column() <= bottomRightLimit.column();
        boolean lightUpperOrAtTopLeftLimit = light.line() >= topLeftLimit.line() && light.column() >= topLeftLimit.column();

        return lightUnderOrAtBottomRightLimit && lightUpperOrAtTopLeftLimit;
    }
}
