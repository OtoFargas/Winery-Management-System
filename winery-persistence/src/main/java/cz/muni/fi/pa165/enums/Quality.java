package cz.muni.fi.pa165.enums;

/**
 * Enum representing the quality levels of harvested grapes
 *
 * @author Lukáš Fudor
 */

public enum Quality {
    HIGH, MEDIUM, LOW;

    @Override
    public String toString() {
        switch (this) {
            case HIGH: return "High";
            case MEDIUM: return "Medium";
            default: return "Low";
        }
    }
}
