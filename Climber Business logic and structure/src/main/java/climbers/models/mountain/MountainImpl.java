package climbers.models.mountain;

import java.util.ArrayList;
import java.util.Collection;

import static climbers.common.ExceptionMessages.MOUNTAIN_NAME_NULL_OR_EMPTY;

public class MountainImpl implements Mountain {

    private String name;
    private Collection<String> peaksList;

    public MountainImpl(String name) {
        setName(name);
        this.peaksList = new ArrayList<>();
    }

    @Override
    public Collection<String> getPeaksList() {
        return this.peaksList;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(MOUNTAIN_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }
}
