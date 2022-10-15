import java.util.ArrayList;

public class GridLights {
    private ArrayList<ArrayList<Boolean>> arrayLights;

    public GridLights(int horizontalSize, int verticalSize) {
        this.arrayLights = generateArrayLights(horizontalSize, verticalSize);
    }

    public boolean isLightOn(int columnLightPosition, int lineLightPosition) {
        return arrayLights.get(columnLightPosition).get(lineLightPosition);
    }

    public void TurnOn(int columnTopLeftLimit, int lineTopLeftLimit, int columnBottomRightLimit, int lineBottomRightLimit) {
        for(int columnLightPosition = 0 ; columnLightPosition < arrayLights.size() ; ++columnLightPosition) {
            for (int lineLightPosition = 0; lineLightPosition < arrayLights.get(columnLightPosition).size(); lineLightPosition++) {
                if(isLightPositionInsideLimits(columnTopLeftLimit, lineTopLeftLimit, columnBottomRightLimit, lineBottomRightLimit, columnLightPosition, lineLightPosition))
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

    private boolean isLightPositionInsideLimits(int columnTopLeftLimit, int lineTopLeftLimit, int columnBottomRightLimit, int lineBottomRightLimit, int columnLightPosition, int lineLightPosition) {
        return lineLightPosition <= lineBottomRightLimit && columnLightPosition <= columnBottomRightLimit &&
                lineLightPosition >= lineTopLeftLimit && columnLightPosition >= columnTopLeftLimit;
    }
}
