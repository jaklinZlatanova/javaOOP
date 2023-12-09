package goldDigger.repositories;

import goldDigger.models.discoverer.Discoverer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DiscovererRepository implements Repository<Discoverer> {

    private Collection<Discoverer> discoverers;

    public DiscovererRepository(){
        this.discoverers=new ArrayList<>();
    }

    @Override
    public Collection<Discoverer> getCollection() {
        return Collections.unmodifiableCollection(this.discoverers);
    }

    @Override
    public void add(Discoverer entity) {

    }

    @Override
    public boolean remove(Discoverer entity) {
        return false;
    }

    @Override
    public Discoverer byName(String name) {
        return null;
    }
}
