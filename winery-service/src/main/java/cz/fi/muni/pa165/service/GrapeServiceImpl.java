package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.GrapeDao;
import cz.fi.muni.pa165.entities.Grape;
import cz.fi.muni.pa165.enums.GrapeColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class implements the GrapeService interface.
 */

@Service
public class GrapeServiceImpl  implements GrapeService{

    @Autowired
    GrapeDao grapeDao;

    @Override
    public void create(Grape grape) {
        grapeDao.create(grape);
    }

    @Override
    public List<Grape> findAll() {
        return grapeDao.findAll();
    }

    @Override
    public Grape findById(Long id) {
        return grapeDao.findById(id);
    }

    @Override
    public Grape findByName(String name) {
        return grapeDao.findByName(name);
    }

    @Override
    public List<Grape> findByColor(GrapeColor color) {
        return grapeDao.findByColor(color);
    }

    @Override
    public void remove(Grape grape) {
        grapeDao.remove(grape);
    }

    @Override
    public void update(Grape grape) {
        grapeDao.update(grape);
    }
}
