package climbers.repositories;

import climbers.models.climber.Climber;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ClimberRepository implements Repository<Climber>{

    private Collection<Climber> climbers;

    public ClimberRepository() {
        this.climbers = new ArrayList<>();
    }

    @Override
    public void add(Climber climber) {
        climbers.add(climber);
    }

    @Override
    public boolean remove(Climber climber) {
        return climbers.remove(climber);
    }

    @Override
    public Climber byName(String name) {
        return this.climbers.stream()
                .filter(c->c.getName()
                        .equals(name))
                .findFirst()
                .orElse(null);
    }
    @Override
    public Collection<Climber> getCollection() {
        return Collections.unmodifiableCollection(this.climbers);
    }
}
