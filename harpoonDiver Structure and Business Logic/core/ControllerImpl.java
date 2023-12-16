package harpoonDiver.core;

import harpoonDiver.common.ConstantMessages;
import harpoonDiver.models.diver.DeepWaterDiver;
import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.diver.OpenWaterDiver;
import harpoonDiver.models.diver.WreckDiver;
import harpoonDiver.models.diving.Diving;
import harpoonDiver.models.diving.DivingImpl;
import harpoonDiver.models.divingSite.DivingSite;
import harpoonDiver.models.divingSite.DivingSiteImpl;
import harpoonDiver.repositories.DiverRepository;
import harpoonDiver.repositories.DivingSiteRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static harpoonDiver.common.ConstantMessages.*;
import static harpoonDiver.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private DiverRepository diversRepository;
    private DivingSiteRepository divingSitesRepository;
    private Diving diving;
    private int siteCount;


    public ControllerImpl() {
        this.diversRepository = new DiverRepository();
        this.divingSitesRepository = new DivingSiteRepository();
        this.diving = new DivingImpl();
    }

    @Override
    public String addDiver(String kind, String diverName) {
        if (!"DeepWaterDiver".equals(kind) && !"OpenWaterDiver".equals(kind) && !"WreckDiver".equals(kind)) {
            throw new IllegalArgumentException(DIVER_INVALID_KIND);
        }
        Diver diver = null;

        switch (kind) {
            case "DeepWaterDiver":
                diver = new DeepWaterDiver(diverName);
                break;
            case "OpenWaterDiver":
                diver = new OpenWaterDiver(diverName);
                break;
            case "WreckDiver":
                diver = new WreckDiver(diverName);
                break;
        }
        this.diversRepository.add(diver);

        return String.format(DIVER_ADDED, kind, diverName);
    }

    @Override
    public String addDivingSite(String siteName, String... seaCreatures) {

        DivingSite divingSite = new DivingSiteImpl(siteName);

        List<String> seaCreaturesList = Arrays.asList(seaCreatures);

        divingSite.getSeaCreatures().addAll(seaCreaturesList);

        this.divingSitesRepository.add(divingSite);

        return String.format(DIVING_SITE_ADDED, siteName);
    }

    @Override
    public String removeDiver(String diverName) {

        Diver diver = this.diversRepository.byName(diverName);

        if (diver == null) {

            String exceptionMessage = String.format(DIVER_DOES_NOT_EXIST, diverName);
            throw new IllegalArgumentException(exceptionMessage);
        }
        this.diversRepository.remove(diver);
        return String.format(DIVER_REMOVE, diverName);
    }

    @Override
    public String startDiving(String siteName) {

        DivingSite divingSite = this.divingSitesRepository.byName(siteName);

        Collection<Diver> collection = this.diversRepository.getCollection();

        List<Diver> goingToDive = new ArrayList<>();
        List<Diver> removed = new ArrayList<>();

        for (Diver diver : collection) {
            if (diver.getOxygen() <= 30) {
                removed.add(diver);
            } else {
                goingToDive.add(diver);
            }
        }
        if (goingToDive.isEmpty()) {
            throw new IllegalArgumentException(SITE_DIVERS_DOES_NOT_EXISTS);
        }
        this.diving.searching(divingSite, goingToDive);
        this.siteCount++;

        return String.format(SITE_DIVING, siteName, removed.size());
    }

    @Override
    public String getStatistics() {

        StringBuilder build = new StringBuilder();
        build.append(String.format(ConstantMessages.FINAL_DIVING_SITES, this.siteCount));
        build.append(System.lineSeparator());
        build.append(ConstantMessages.FINAL_DIVERS_STATISTICS);

        Collection<Diver> divers = diversRepository.getCollection();
        for (Diver diver : divers) {
            build.append(System.lineSeparator());
            build.append(String.format(ConstantMessages.FINAL_DIVER_NAME, diver.getName()));
            build.append(System.lineSeparator());
            build.append(String.format(ConstantMessages.FINAL_DIVER_OXYGEN, diver.getOxygen()));
            build.append(System.lineSeparator());
            if (diver.getSeaCatch().getSeaCreatures().isEmpty()) {
                build.append(String.format(ConstantMessages.FINAL_DIVER_CATCH, "None"));

            } else {
                build.append(String.format(ConstantMessages.FINAL_DIVER_CATCH,
                        String.join(ConstantMessages.FINAL_DIVER_CATCH_DELIMITER, diver.getSeaCatch().getSeaCreatures())));
            }
        }
        return build.toString();
    }
}

