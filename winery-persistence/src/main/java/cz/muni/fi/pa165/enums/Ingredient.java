package cz.muni.fi.pa165.enums;

/**
 * Enum representing the possible ingredients in wine
 *
 * @author Vladimir Visnovsky
 */

public enum Ingredient {
    POTASSIUM, CALCIUM, SULFUR, SUGAR, GRAPE_JUICE, WATER, OAK, VANILLA, TANNINS, YEAST;

    @Override
    public String toString() {
        switch (this) {
            case POTASSIUM: return "Potassium";
            case CALCIUM: return "Calcium";
            case SULFUR: return "Sulfur";
            case SUGAR: return "Sugar";
            case GRAPE_JUICE: return "Grape Juice";
            case WATER: return "Water";
            case OAK: return "Oak";
            case VANILLA: return "Vanilla";
            case TANNINS: return "Tannins";
            default: return "Yeast";
        }
    }
}
