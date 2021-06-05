package cz.muni.fi.pa165.enums;

/**
 * Enum representing the taste options for wine
 *
 * @author Vladimir Visnovsky
 */

public enum Taste {
    SWEET, SEMI_SWEET, SEMI_DRY, DRY;

    @Override
    public String toString() {
        switch (this) {
            case SWEET: return "Sweet";
            case SEMI_SWEET: return "Semi Sweet";
            case SEMI_DRY: return "Semi Dry";
            default: return "Dry";
        }
    }
}
