package harpoonDiver.repositories;

import harpoonDiver.models.divingSite.DivingSite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DivingSiteRepository implements Repository<DivingSite> {

    private Collection<DivingSite> sites;

    public DivingSiteRepository() {
        this.sites = new ArrayList<>();
    }

    public void add(DivingSite divingSite) {
        sites.add(divingSite);
    }


    public boolean remove(DivingSite divingSite) {
        return sites.remove(divingSite);
    }


    public DivingSite byName(String name) {
        return this.sites.stream().filter(ds -> ds.getClass()
                        .getSimpleName()
                        .equals(name))
                .findFirst()
                .orElse(null);
    }

    public Collection<DivingSite> getCollection() {
        return Collections.unmodifiableCollection(this.sites);
    }
}
