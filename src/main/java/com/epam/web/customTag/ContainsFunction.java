package com.epam.web.customTag;

import com.epam.web.entity.type.GenderType;
import com.epam.web.entity.type.GenreType;
import com.epam.web.entity.type.OccupationType;

import java.util.List;

public class ContainsFunction {
    public static boolean containsGenreType(List<GenreType> list, GenreType item) {
        for (GenreType element : list) {
            if (item.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsOccupationType(List<OccupationType> list, OccupationType item) {
        for (OccupationType element : list) {
            if (item.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public static boolean equalsGender(GenderType genderType, GenderType personsGender) {
        return genderType.equals(personsGender);
    }

}
