package harpoonDiver.repositories;

import harpoonDiver.models.diver.Diver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DiverRepository implements Repository<Diver>{

    private Collection<Diver> divers;

    public DiverRepository() {
        this.divers = new ArrayList<>();
    }

    public Collection<Diver> getCollection() {
        return Collections.unmodifiableCollection(this.divers);
    }


    public void add(Diver diver) {
        divers.add(diver);
    }

    public boolean remove(Diver diver) {
        return divers.remove(diver);
    }


    public Diver byName(String name) {
        return this.divers.stream().filter(d -> d.getClass()
                        .getSimpleName()
                        .equals(name))
                .findFirst()
                .orElse(null);
    }
}
