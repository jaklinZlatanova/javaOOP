package handball.repositories;

import handball.entities.equipment.Equipment;

import java.util.Collection;

public class EquipmentRepository implements Repository {

    private Collection<Equipment> equipments;

    @Override
    public void add(Equipment equipment) {
        this.equipments.add(equipment);
    }

    @Override
    public boolean remove(Equipment equipment) {
        return this.equipments.remove(equipment);
    }

    @Override
    public Equipment findByType(String type) {
        return this.equipments.stream()
                        .filter(e -> e.getClass().getSimpleName()
                        .equals(type))
                        .findFirst()
                        .orElse(null);
    }
}
