package cz.muni.fi.pa165.sampledata;

import cz.muni.fi.pa165.entities.Feedback;
import cz.muni.fi.pa165.entities.Grape;
import cz.muni.fi.pa165.entities.Harvest;
import cz.muni.fi.pa165.entities.User;
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
import cz.muni.fi.pa165.service.UserService;
import cz.muni.fi.pa165.service.WineService;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    @Autowired
    private final UserService userService;

    private Wine wine1;
    private Wine wine2;
    private Wine wine3;

    private Grape grape1;
    private Grape grape2;
    private Grape grape3;

    private Harvest harvest1;
    private Harvest harvest2;
    private Harvest harvest3;

    private Feedback feedback0;
    private Feedback feedback1;
    private Feedback feedback2;
    private Feedback feedback3;
    private Feedback feedback4;

    private User user0;
    private User user1;
    private User user2;
    private User user3;

    @Inject
    public SampleDataLoadingFacadeImpl(FeedbackService feedbackService, WineService wineService, HarvestService harvestService,
                                       GrapeService grapeService, UserService userService) {
        this.feedbackService = feedbackService;
        this.wineService = wineService;
        this.harvestService = harvestService;
        this.grapeService = grapeService;
        this.userService = userService;
    }

    @Override
    public void loadData() throws IOException {

        // wines
        wine1 = new Wine();
        wine1.setName("Cabernet Sauvignon");
        wine1.setSold(20);
        wine1.setStocked(30);
        wine1.setColor(WineColor.RED);
        wine1.setTaste(Taste.SWEET);
        wine1.setIngredients(new ArrayList<>(List.of(Ingredient.OAK, Ingredient.SULFUR, Ingredient.TANNINS)));
        wine1.setWineYear(2015);

        wine2 = new Wine();
        wine2.setName("Pinot Blanc");
        wine2.setSold(10);
        wine2.setStocked(40);
        wine2.setColor(WineColor.DESSERT);
        wine2.setTaste(Taste.DRY);
        wine2.setIngredients(new ArrayList<>(List.of(Ingredient.GRAPE_JUICE, Ingredient.SULFUR)));
        wine2.setWineYear(2014);

        wine3 = new Wine();
        wine3.setName("Merlot");
        wine3.setSold(15);
        wine3.setStocked(14);
        wine3.setColor(WineColor.WHITE);
        wine3.setTaste(Taste.SEMI_DRY);
        wine3.setIngredients(new ArrayList<>(List.of(Ingredient.GRAPE_JUICE, Ingredient.TANNINS)));
        wine3.setWineYear(2014);

        // grapes
        grape1 = new Grape();
        grape1.setName("Merlot");
        grape1.setColor(GrapeColor.RED);
        grape1.setQuantity(56);

        List<Disease> diseases = new ArrayList<>();
        diseases.add(Disease.CROWN_GALL);
        diseases.add(Disease.GREY_MOLD);
        grape1.setDiseases(diseases);

        grape2 = new Grape();
        grape2.setName("Cabernet Sauvignon");
        grape2.setColor(GrapeColor.WHITE);
        grape2.setQuantity(99);

        diseases = new ArrayList<>();
        diseases.add(Disease.DOWNY_MILDEW);
        diseases.add(Disease.POWDERY_MILDEW);
        grape2.setDiseases(diseases);

        grape3 = new Grape();
        grape3.setName("Riesling");
        grape3.setColor(GrapeColor.WHITE);
        grape3.setQuantity(12);

        diseases = new ArrayList<>();
        diseases.add(Disease.ANTHRACNOSE);
        grape3.setDiseases(diseases);

        // harvests
        harvest1 = new Harvest();
        harvest1.setHarvestYear(2015);
        harvest1.setQuality(Quality.HIGH);
        harvest1.setQuantity(200);

        harvest2 = new Harvest();
        harvest2.setHarvestYear(2014);
        harvest2.setQuality(Quality.MEDIUM);
        harvest2.setQuantity(168);

        harvest3 = new Harvest();
        harvest3.setHarvestYear(2014);
        harvest3.setQuality(Quality.LOW);
        harvest3.setQuantity(123);

        // feedbacks
        feedback0 = new Feedback();
        feedback0.setAuthor("Lukas Fudor");
        feedback0.setContent("Very good wine.");
        feedback0.setDate(new GregorianCalendar(2020, Calendar.AUGUST, 11).getTime());
        feedback0.setRating(10);

        feedback1 = new Feedback();
        feedback1.setAuthor("Vlado Visnovsky");
        feedback1.setContent("Not good.");
        feedback1.setDate(new GregorianCalendar(2020, Calendar.MARCH, 11).getTime());
        feedback1.setRating(1);

        feedback2 = new Feedback();
        feedback2.setAuthor("Marko Biocina");
        feedback2.setContent("Pretty nice taste, the touch of vanilla makes all the difference.");
        feedback2.setDate(new GregorianCalendar(2020, Calendar.APRIL, 20).getTime());
        feedback2.setRating(7);

        feedback3 = new Feedback();
        feedback3.setAuthor("Igor Orecic");
        feedback3.setContent("Excellent wine, one of the best I ever had.");
        feedback3.setDate(new GregorianCalendar(2020, Calendar.DECEMBER, 9).getTime());
        feedback3.setRating(9);

        feedback4 = new Feedback();
        feedback4.setAuthor("Jakov Prorokovic");
        feedback4.setContent("I don't usually like sweet wines, but you can tell this one is special.");
        feedback4.setDate(new GregorianCalendar(2020, Calendar.MARCH, 19).getTime());
        feedback4.setRating(10);

        user0 = new User();
        user0.setFirstName("user");
        user0.setSurname("user");
        user0.setEmail("user");
        user0.setAdmin(false);

        user1 = new User();
        user1.setFirstName("admin");
        user1.setSurname("admin");
        user1.setEmail("admin");
        user1.setAdmin(true);

        user2 = new User();
        user2.setFirstName("Vladimir");
        user2.setSurname("Visnovsky");
        user2.setEmail("vladimir.visnovsky@winery.com");
        user2.setAdmin(false);

        user3 = new User();
        user3.setFirstName("Lukas");
        user3.setSurname("Fudor");
        user3.setEmail("lukas.fudor@winery.com");
        user3.setAdmin(false);

        harvest1.setGrape(grape1);
        harvest1.setWine(wine1);
        grape1.addHarvest(harvest1);
        wine1.addHarvest(harvest1);

        harvest2.setGrape(grape2);
        harvest2.setWine(wine2);
        grape2.addHarvest(harvest2);
        wine2.addHarvest(harvest2);

        harvest3.setGrape(grape3);
        harvest3.setWine(wine3);
        grape3.addHarvest(harvest3);
        wine3.addHarvest(harvest3);

        feedback0.setWine(wine1);
        wine1.addFeedback(feedback0);

        feedback1.setWine(wine1);
        wine1.addFeedback(feedback1);

        feedback2.setWine(wine2);
        wine2.addFeedback(feedback2);

        feedback3.setWine(wine3);
        wine3.addFeedback(feedback3);

        feedback4.setWine(wine3);
        wine3.addFeedback(feedback4);

        grapeService.createGrape(grape1);
        grapeService.createGrape(grape2);
        grapeService.createGrape(grape3);

        wineService.createWine(wine1);
        wineService.createWine(wine2);
        wineService.createWine(wine3);

        harvestService.createHarvest(harvest1);
        harvestService.createHarvest(harvest2);
        harvestService.createHarvest(harvest3);

        feedbackService.createFeedback(feedback0);
        feedbackService.createFeedback(feedback1);
        feedbackService.createFeedback(feedback2);
        feedbackService.createFeedback(feedback3);
        feedbackService.createFeedback(feedback4);

        userService.registerUser(user0, "user");
        userService.registerUser(user1, "admin");
        userService.registerUser(user2, "tarantula");
        userService.registerUser(user3, "1234");
    }
}
