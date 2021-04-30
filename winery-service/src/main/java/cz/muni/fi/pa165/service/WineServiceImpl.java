package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.WineDao;
import cz.muni.fi.pa165.entities.Feedback;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.entities.Wine;
import cz.muni.fi.pa165.exceptions.WineryServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Implementation of the WineService.
 *
 * @author Oto Fargas
 */
@Service
public class WineServiceImpl implements WineService {

    @Autowired
    WineDao wineDao;

    @Override
    public void createWine(Wine wine) {
        try {
            wineDao.create(wine);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public List<Wine> findAllWines() {
        try {
            return wineDao.findAll();
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public Wine findWineById(Long id) {
        try {
            return wineDao.findById(id);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public Wine findWineByName(String name) {
        try {
            return wineDao.findByName(name);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public void updateWine(Wine wine) {
        try {
            wineDao.update(wine);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public void removeWine(Wine wine) {
        try {
            wineDao.remove(wine);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public void sellWine(Wine wine, Integer amount) {
        if (wine.getStocked() < amount) {
            throw new WineryServiceException("Not enough stocked wine!");
        }
        
        wine.setSold(wine.getSold() + amount);
        wine.setStocked(wine.getStocked() - amount);
        try {
            wineDao.update(wine);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public void addFeedbackToWine(Wine wine, Feedback feedback) {
        if (wine.getFeedbacks().contains(feedback)) {
            throw new WineryServiceException("This wine already contains this feedback!");
        }
        wine.addFeedback(feedback);
        try {
            wineDao.update(wine);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public void addHarvestToWine(Wine wine, Harvest harvest) {
        if (wine.getHarvests().contains(harvest)) {
            throw new WineryServiceException("This wine already contains this harvest!");
        }
        wine.addHarvest(harvest);
        try {
            wineDao.update(wine);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }
}
