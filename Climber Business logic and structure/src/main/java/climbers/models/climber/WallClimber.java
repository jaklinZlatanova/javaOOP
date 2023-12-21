package climbers.models.climber;

public class WallClimber extends BaseClimber{

    public WallClimber(String name) {
        super(name, 90);
    }
    @Override
    public void climb() {
        double newStrength = getStrength() - 30;

        if (newStrength<=0){
            setStrength(0);
        }
        setStrength(newStrength);
    }
}
