package com.epam.web.util.customTag;

import com.epam.web.domain.MovieRating;
import com.epam.web.domain.type.GenderType;
import com.epam.web.domain.type.GenreType;
import com.epam.web.domain.type.OccupationType;

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

    public static MovieRating getUserRate(List<MovieRating> ratingList, int userId) {
        MovieRating useRate = null;
        for (MovieRating movieRating : ratingList) {
            if (movieRating.getUserId() == userId) {
                useRate = movieRating;
                break;
            }
        }
        return useRate;
    }

}
