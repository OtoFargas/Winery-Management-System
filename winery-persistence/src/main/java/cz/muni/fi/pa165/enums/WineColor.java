package cz.muni.fi.pa165.enums;

/**
 * Enum representing the color options for wine
 *
 * @author Vladimir Visnovsky
 */

public enum WineColor {
    RED, WHITE, ROSE, DESSERT, SPARKLING;

    @Override
    public String toString() {
        switch (this) {
            case RED: return "Red";
            case WHITE: return "White";
            case ROSE: return "Rose";
            case DESSERT: return "Dessert";
            default: return "Sparkling";
        }
    }
}
