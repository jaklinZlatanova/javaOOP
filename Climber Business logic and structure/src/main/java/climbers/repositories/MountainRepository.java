package climbers.repositories;

import climbers.models.climber.Climber;
import climbers.models.mountain.Mountain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MountainRepository implements Repository<Mountain> {

    private Collection<Mountain> mountains;

    public MountainRepository() {
        this.mountains = new ArrayList<>();
    }

    @Override
    public void add(Mountain mountain) {
        mountains.add(mountain);
    }

    @Override
    public boolean remove(Mountain mountain) {
        return mountains.remove(mountain);
    }

    @Override
    public Mountain byName(String name) {
        return this.mountains.stream()
                .filter(m -> m.getName()
                        .equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Mountain> getCollection() {
        return Collections.unmodifiableCollection(this.mountains);
    }
}