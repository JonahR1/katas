import Light.Light;
import TurnOnLightCommand.Command;

public class TurnOffLightCommand implements Command {
    private final Light light;

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }


    @Override
    public void execute() {
        light.TurnOff();
    }
}
