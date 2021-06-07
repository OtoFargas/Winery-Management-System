package cz.muni.fi.pa165.enums;

/**
 * Enum representing the color of the grapes
 *
 * @author Lukáš Fudor
 */

public enum GrapeColor {
    RED, WHITE;

    @Override
    public String toString() {
        switch (this) {
            case RED: return "Red";
            default: return "White";
        }
    }
}
