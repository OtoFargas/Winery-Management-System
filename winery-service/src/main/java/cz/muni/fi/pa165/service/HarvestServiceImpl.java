package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.HarvestDao;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.exceptions.WineryServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        try {
            harvestDao.create(harvest);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public List<Harvest> findAllHarvests() {
        try {
            return harvestDao.findAll();
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public Harvest findHarvestById(Long id) {
        try {
            return harvestDao.findById(id);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public List<Harvest> findHarvestByYear(Integer year) {
        try {
            return harvestDao.findByYear(year);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public void updateHarvest(Harvest harvest) {
        try {
            harvestDao.update(harvest);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }

    @Override
    public void removeHarvest(Harvest harvest) {
        try {
            harvestDao.remove(harvest);
        } catch (DataAccessException e) {
            throw new WineryServiceException(e.getMessage());
        }
    }
}
