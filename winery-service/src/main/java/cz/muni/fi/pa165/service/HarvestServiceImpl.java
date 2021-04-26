package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.HarvestDao;
import cz.muni.fi.pa165.entities.Harvest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class implements the HarvestService interface.
 *
 * @author Lukáš Fudor
 */

@Service
public class HarvestServiceImpl implements HarvestService {

    @Autowired
    HarvestDao harvestDao;

    @Override
    public void createHarvest(Harvest harvest) {
        harvestDao.create(harvest);
    }

    @Override
    public List<Harvest> findAllHarvests() {
        return harvestDao.findAll();
    }

    @Override
    public Harvest findHarvestById(Long id) {
        return harvestDao.findById(id);
    }

    @Override
    public List<Harvest> findHarvestByYear(Integer year) {
        return harvestDao.findByYear(year);
    }

    @Override
    public void updateHarvest(Harvest harvest) {
        harvestDao.update(harvest);
    }

    @Override
    public void removeHarvest(Harvest harvest) {
        harvestDao.remove(harvest);
    }
}
