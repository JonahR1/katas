import java.util.ArrayList;

public class GridLights {
    private ArrayList<ArrayList<Boolean>> arrayLights;

    public GridLights(int horizontalSize, int verticalSize) {
        this.arrayLights = generateArrayLights(horizontalSize, verticalSize);
    }

    public boolean isLightOn(int columnLightPosition, int lineLightPosition) {
        return arrayLights.get(columnLightPosition).get(lineLightPosition);
    }

    public void TurnOn(LightPosition topLeftLimit, LightPosition bottomRightLimit) {
        for (int columnLightPosition = 0; columnLightPosition < arrayLights.size(); ++columnLightPosition) {
            for (int lineLightPosition = 0; lineLightPosition < arrayLights.get(columnLightPosition).size(); lineLightPosition++) {
                if (isLightPositionInsideLimits(topLeftLimit, bottomRightLimit, new LightPosition(columnLightPosition, lineLightPosition)))
                    this.arrayLights.get(columnLightPosition).set(lineLightPosition, true);
            }
        }
    }

    private ArrayList<ArrayList<Boolean>> generateArrayLights(int horizontalSize, int verticalSize) {
        ArrayList<ArrayList<Boolean>> arrayLights = new ArrayList<ArrayList<Boolean>>(horizontalSize);

        for (int columnVerticalLights = 0; columnVerticalLights < horizontalSize; ++columnVerticalLights)
            arrayLights.add(generateVerticalArrayLights(verticalSize));

        return arrayLights;
    }

    private ArrayList<Boolean> generateVerticalArrayLights(int verticalSize) {
        ArrayList<Boolean> verticalArrayLights = new ArrayList<Boolean>(verticalSize);

        for (int verticalLightsPosition = 0; verticalLightsPosition < verticalSize; ++verticalLightsPosition)
            verticalArrayLights.add(false);

        return verticalArrayLights;
    }

    private boolean isLightPositionInsideLimits(LightPosition topLeftLimit, LightPosition bottomRightLimit, LightPosition light) {
        boolean lightUnderOrAtBottomRightLimit = light.line() <= bottomRightLimit.line() && light.column() <= bottomRightLimit.column();
        boolean lightUpperOrAtTopLeftLimit = light.line() >= topLeftLimit.line() && light.column() >= topLeftLimit.column();

        return lightUnderOrAtBottomRightLimit && lightUpperOrAtTopLeftLimit;
    }
}
