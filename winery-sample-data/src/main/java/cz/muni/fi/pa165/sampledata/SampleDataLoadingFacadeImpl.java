package cz.muni.fi.pa165.sampledata;

import cz.muni.fi.pa165.entities.Feedback;
import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.entities.Wine;
import cz.muni.fi.pa165.enums.Disease;
import cz.muni.fi.pa165.enums.GrapeColor;
import cz.muni.fi.pa165.enums.Ingredient;
import cz.muni.fi.pa165.enums.Quality;
import cz.muni.fi.pa165.enums.Taste;
import cz.muni.fi.pa165.enums.WineColor;
import cz.muni.fi.pa165.service.FeedbackService;
import cz.muni.fi.pa165.service.GrapeService;
import cz.muni.fi.pa165.service.HarvestService;
import cz.muni.fi.pa165.service.WineService;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Loads some sample data to populate the eshop database.
 *
 * @author Oto Fargas
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    private final FeedbackService feedbackService;

    @Autowired
    private final WineService wineService;

    @Autowired
    private final HarvestService harvestService;

    @Autowired
    private final GrapeService grapeService;

    private Wine wine1;
    private Wine wine2;
    private Wine wine3;

    private Grape grape1;
    private Grape grape2;
    private Grape grape3;

    private Harvest harvest1;
    private Harvest harvest2;
    private Harvest harvest3;

    private Feedback feedback1;
    private Feedback feedback2;
    private Feedback feedback3;

    @Inject
    public SampleDataLoadingFacadeImpl(FeedbackService feedbackService, WineService wineService, HarvestService harvestService, GrapeService grapeService) {
        this.feedbackService = feedbackService;
        this.wineService = wineService;
        this.harvestService = harvestService;
        this.grapeService = grapeService;
    }

    @Override
    public void loadData() throws IOException {

        // wines
        wine1 = new Wine();
        wine1.setName("wine1");
        wine1.setSold(20);
        wine1.setStocked(30);
        wine1.setColor(WineColor.RED);
        wine1.setTaste(Taste.SWEET);
        wine1.setIngredients(new ArrayList<>(List.of(Ingredient.OAK, Ingredient.SULFUR, Ingredient.TANNINS)));

        wine2 = new Wine();
        wine2.setName("wine2");
        wine2.setSold(10);
        wine2.setStocked(40);
        wine2.setColor(WineColor.DESSERT);
        wine2.setTaste(Taste.DRY);
        wine2.setIngredients(new ArrayList<>(List.of(Ingredient.GRAPE_JUICE, Ingredient.SULFUR)));

        wine3 = new Wine();
        wine3.setName("wine3");
        wine3.setSold(15);
        wine3.setStocked(14);
        wine3.setColor(WineColor.WHITE);
        wine3.setTaste(Taste.SEMI_DRY);
        wine3.setIngredients(new ArrayList<>(List.of(Ingredient.GRAPE_JUICE, Ingredient.TANNINS)));

        // wineService.createWine(wine1);
        // wineService.createWine(wine2);
        // wineService.createWine(wine3);

        // grapes
        grape1 = new Grape();
        grape1.setName("Test1");
        grape1.setColor(GrapeColor.RED);
        grape1.setQuantity(56);

        List<Disease> diseases = new ArrayList<>();
        diseases.add(Disease.CROWN_GALL);
        diseases.add(Disease.GREY_MOLD);
        grape1.setDiseases(diseases);

        grape2 = new Grape();
        grape2.setName("Test2");
        grape2.setColor(GrapeColor.WHITE);
        grape2.setQuantity(99);

        diseases = new ArrayList<>();
        diseases.add(Disease.DOWNY_MILDEW);
        diseases.add(Disease.POWDERY_MILDEW);
        grape2.setDiseases(diseases);

        grape3 = new Grape();
        grape3.setName("Test3");
        grape3.setColor(GrapeColor.WHITE);
        grape3.setQuantity(12);

        diseases = new ArrayList<>();
        diseases.add(Disease.ANTHRACNOSE);
        grape3.setDiseases(diseases);

        grapeService.createGrape(grape1);
        grapeService.createGrape(grape2);
        grapeService.createGrape(grape3);


        // harvests
        harvest1 = new Harvest();
        harvest1.setHarvestYear(2015);
        harvest1.setQuality(Quality.HIGH);
        harvest1.setQuantity(200);
        harvest1.setGrape(grape1);
        harvest1.setWine(wine1);

        harvest2 = new Harvest();
        harvest2.setHarvestYear(2014);
        harvest2.setQuality(Quality.MEDIUM);
        harvest2.setQuantity(168);
        harvest2.setGrape(grape2);
        harvest2.setWine(wine2);

        harvest3 = new Harvest();
        harvest3.setHarvestYear(2014);
        harvest3.setQuality(Quality.LOW);
        harvest3.setQuantity(123);
        harvest3.setGrape(grape3);
        harvest3.setWine(wine3);

        // feedbacks
        feedback1 = new Feedback(1L);
        feedback1.setAuthor("author1");
        feedback1.setContent("content1");
        feedback1.setDate(new Date());
        feedback1.setRating(5);
        feedback1.setWine(wine1);

        feedback2 = new Feedback(2L);
        feedback2.setAuthor("testAuthor2");
        feedback2.setContent("testContent2");
        feedback2.setDate(new Date());
        feedback2.setRating(3);
        feedback2.setWine(wine2);

        feedback3 = new Feedback(3L);
        feedback3.setAuthor("testAuthor3");
        feedback3.setContent("testContent3");
        feedback3.setDate(new Date());
        feedback3.setRating(8);
        feedback3.setWine(wine1);
    }
}
