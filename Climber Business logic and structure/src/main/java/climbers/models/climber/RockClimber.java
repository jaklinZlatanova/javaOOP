package climbers.models.climber;

public class RockClimber extends BaseClimber{

    public RockClimber(String name) {
        super(name,120);
    }

    @Override
    public void climb() {
        double newStrength = getStrength() - 60;

        if (newStrength<=0){
            setStrength(0);
        }
        setStrength(newStrength);
    }
}
