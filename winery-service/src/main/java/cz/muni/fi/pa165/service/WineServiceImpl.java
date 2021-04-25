package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.WineDao;
import cz.muni.fi.pa165.entities.Wine;
import cz.muni.fi.pa165.exceptions.WineryServiceException;
import org.springframework.beans.factory.annotation.Autowired;
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
        wineDao.create(wine);
    }

    @Override
    public List<Wine> findAllWines() {
        return wineDao.findAll();
    }

    @Override
    public Wine findWineById(Long id) {
        return wineDao.findById(id);
    }

    @Override
    public Wine findWineByName(String name) {
        return wineDao.findByName(name);
    }

    @Override
    public void updateWine(Wine wine) {
        wineDao.update(wine);
    }

    @Override
    public void removeWine(Wine wine) {
        wineDao.remove(wine);
    }

    @Override
    public void sellWine(Wine wine, Integer amount) {
        if (wine.getStocked() < amount) {
            throw new WineryServiceException("Not enough stocked wine!");
        }
        
        wine.setSold(wine.getSold() + amount);
        wine.setStocked(wine.getStocked() - amount);
        wineDao.update(wine);
    }
}
