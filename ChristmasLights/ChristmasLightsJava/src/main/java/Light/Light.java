package Light;

public class Light {
    private boolean isOn = false;

    public boolean isOn(){
        return isOn;
    }

    public void TurnOn() {
        isOn = true;
    }

    public void TurnOff() {
        isOn = false;
    }
}
