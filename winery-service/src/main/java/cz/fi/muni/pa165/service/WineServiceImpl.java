package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.WineDao;
import cz.fi.muni.pa165.entities.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Implementation of the WineService.
 *
 *
 */
@Service
public class WineServiceImpl implements WineService {

    @Autowired
    WineDao wineDao;

    @Override
    public void create(Wine wine) {
        wineDao.create(wine);
    }

    @Override
    public List<Wine> findAll() {
        return wineDao.findAll();
    }

    @Override
    public Wine findById(Long id) {
        return wineDao.findById(id);
    }

    @Override
    public Wine findByName(String name) {
        return wineDao.findByName(name);
    }

    @Override
    public void remove(Wine wine) {
        wineDao.remove(wine);
    }
}
