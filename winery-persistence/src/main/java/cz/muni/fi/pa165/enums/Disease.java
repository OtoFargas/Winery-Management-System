package cz.muni.fi.pa165.enums;

/**
 * Enum representing the possible diseases of a grape plant.
 *
 * @author Lukáš Fudor
 */
public enum Disease {
    POWDERY_MILDEW, DOWNY_MILDEW, GREY_MOLD, CROWN_GALL, BLACK_ROT, ANTHRACNOSE;

    @Override
    public String toString() {
        switch (this) {
            case POWDERY_MILDEW: return "Powdery Mildew";
            case DOWNY_MILDEW: return "Downy Mildew";
            case GREY_MOLD: return "Grey Mold";
            case CROWN_GALL: return "Crown Gall";
            case BLACK_ROT: return "Black Rot";
            default: return "Anthracnose";
        }
    }
}
