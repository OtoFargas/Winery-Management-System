package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.GrapeDao;
import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.enums.Disease;
import cz.muni.fi.pa165.enums.GrapeColor;
import cz.muni.fi.pa165.exceptions.WineryServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the GrapeService interface.
 */

@Service
public class GrapeServiceImpl  implements GrapeService {

    @Autowired
    GrapeDao grapeDao;

    @Override
    public void createGrape(Grape grape) {
        grapeDao.create(grape);
    }

    @Override
    public List<Grape> findAllGrapes() {
        return grapeDao.findAll();
    }

    @Override
    public Grape findGrapeById(Long id) {
        return grapeDao.findById(id);
    }

    @Override
    public Grape findGrapeByName(String name) {
        return grapeDao.findByName(name);
    }

    @Override
    public List<Grape> findGrapeByColor(GrapeColor color) {
        return grapeDao.findByColor(color);
    }

    @Override
    public void removeGrape(Grape grape) {
        grapeDao.remove(grape);
    }

    @Override
    public void updateGrape(Grape grape) {
        grapeDao.update(grape);
    }

    @Override
    public void addHarvestToGrape(Grape grape, Harvest harvest) {
        if (grape.getHarvests().contains(harvest)) {
            throw new WineryServiceException("This grape already contains this harvest!");
        }
        grape.addHarvest(harvest);
    }

    @Override
    public void cureDisease(Grape grape, Disease disease) {
        if (!grape.getDiseases().contains(disease)) {
            throw new WineryServiceException("There is no such disease: " + disease);
        }
        List<Disease> diseases = new ArrayList<>(grape.getDiseases());
        diseases.remove(disease);
        grape.setDiseases(diseases);
        grapeDao.update(grape);
    }

    @Override
    public void cureAllDiseases(Grape grape) {
        grape.setDiseases(new ArrayList<>());
        grapeDao.update(grape);
    }
}
