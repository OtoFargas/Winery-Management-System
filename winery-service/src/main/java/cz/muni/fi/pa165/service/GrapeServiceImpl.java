package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.GrapeDao;
import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.enums.Disease;
import cz.muni.fi.pa165.enums.GrapeColor;
import cz.muni.fi.pa165.exceptions.WineryServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        try {
            grapeDao.create(grape);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public List<Grape> findAllGrapes() {
        try {
            return grapeDao.findAll();
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public Grape findGrapeById(Long id) {
        try {
            return grapeDao.findById(id);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public Grape findGrapeByName(String name) {
        try {
            return grapeDao.findByName(name);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public List<Grape> findGrapeByColor(GrapeColor color) {
        try {
            return grapeDao.findByColor(color);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public void removeGrape(Grape grape) {
        try {
            grapeDao.remove(grape);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public void updateGrape(Grape grape) {
        try {
            grapeDao.update(grape);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public void addHarvestToGrape(Grape grape, Harvest harvest) {
        if (grape.getHarvests().contains(harvest)) {
            throw new WineryServiceException("This grape already contains this harvest!");
        }
        grape.addHarvest(harvest);
        try {
            grapeDao.update(grape);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public void cureDisease(Grape grape, Disease disease) {
        if (!grape.getDiseases().contains(disease)) {
            throw new WineryServiceException("There is no such disease: " + disease);
        }
        List<Disease> diseases = new ArrayList<>(grape.getDiseases());
        diseases.remove(disease);
        grape.setDiseases(diseases);
        try {
            grapeDao.update(grape);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public void cureAllDiseases(Grape grape) {
        grape.setDiseases(new ArrayList<>());
        try {
            grapeDao.update(grape);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }
}
