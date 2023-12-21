package climbers.core;

import climbers.models.climber.Climber;
import climbers.models.climber.RockClimber;
import climbers.models.climber.WallClimber;
import climbers.models.climbing.Climbing;
import climbers.models.climbing.ClimbingImpl;
import climbers.models.mountain.Mountain;
import climbers.models.mountain.MountainImpl;
import climbers.repositories.ClimberRepository;
import climbers.repositories.MountainRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static climbers.common.ConstantMessages.*;
import static climbers.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private ClimberRepository climbersRepository;
    private MountainRepository mountainsRepository;
    private Climbing climbing;
    private int mountainCount;

    public ControllerImpl() {
        this.climbersRepository = new ClimberRepository();
        this.mountainsRepository = new MountainRepository();
        this.climbing = new ClimbingImpl();
    }

    @Override
    public String addClimber(String type, String climberName) {
        if (!"RockClimber".equals(type) && !"WallClimber".equals(type)) {
            throw new IllegalArgumentException(CLIMBER_INVALID_TYPE);
        }
        Climber climber = null;

        switch (type) {
            case "RockClimber":
                climber = new RockClimber(climberName);
                break;
            case "WallClimber":
                climber = new WallClimber(climberName);
                break;
        }
        this.climbersRepository.add(climber);

        return String.format(CLIMBER_ADDED, type, climberName);
    }

    @Override
    public String addMountain(String mountainName, String... peaks) {

        Mountain mountain = new MountainImpl(mountainName);

        List<String> listOfPeaks = Arrays.asList(peaks);

        mountain.getPeaksList().addAll(listOfPeaks);

        this.mountainsRepository.add(mountain);

        return String.format(MOUNTAIN_ADDED, mountainName);
    }

    @Override
    public String removeClimber(String climberName) {

        Climber climber = this.climbersRepository.byName(climberName);

        if (climber == null) {
            String excMsg = String.format(CLIMBER_DOES_NOT_EXIST, climberName);
            throw new IllegalArgumentException(excMsg);
        }
        this.climbersRepository.remove(climber);
        return String.format(CLIMBER_REMOVE, climberName);
    }

    @Override
    public String startClimbing(String mountainName) {

        Mountain mountain = this.mountainsRepository.byName(mountainName);

        Collection<Climber> collection = this.climbersRepository.getCollection();

        List<Climber> goingToClimb = new ArrayList<>();
        List<Climber> removed = new ArrayList<>();

        for (Climber climber : collection) {
            double strength = climber.getStrength();
            if (climber.getStrength() <= 0) {
                strength = 0;
            }
            if (strength == 0) {
                removed.add(climber);
            } else {
                goingToClimb.add(climber);

            }
        }
        if (goingToClimb.isEmpty()) {
            throw new IllegalArgumentException(THERE_ARE_NO_CLIMBERS);
        }
        Climbing climbing = new ClimbingImpl();
        climbing.conqueringPeaks(mountain,collection);
        long removedClimbers = collection.stream().filter(c->c.getStrength()==0).count();
        this.mountainCount++;
        //this.climbing.conqueringPeaks(mountain, collection);

        return String.format(PEAK_CLIMBING, mountainName, removedClimbers);
    }

    @Override
    public String getStatistics() {

        StringBuilder build = new StringBuilder();
        build.append(String.format(FINAL_MOUNTAIN_COUNT, this.mountainCount));
        build.append(System.lineSeparator());
        build.append(FINAL_CLIMBERS_STATISTICS);

        Collection<Climber> climbers = climbersRepository.getCollection();

        for (Climber climber : climbers) {
            build.append(System.lineSeparator());
            build.append(String.format(FINAL_CLIMBER_NAME, climber.getName()));
            build.append(System.lineSeparator());
            build.append(String.format(FINAL_CLIMBER_STRENGTH, climber.getStrength()));
            build.append(System.lineSeparator());
            if (climber.getRoster().getPeaks().isEmpty()) {
                build.append(String.format(FINAL_CLIMBER_PEAKS, "None"));
            } else {
                build.append(String.format(FINAL_CLIMBER_PEAKS,
                        String.join(FINAL_CLIMBER_FINDINGS_DELIMITER, climber.getRoster().getPeaks())));
            }
        }
        return build.toString();
    }
}
