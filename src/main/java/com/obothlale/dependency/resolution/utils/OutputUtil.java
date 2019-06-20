package com.obothlale.dependency.resolution.utils;

public final class OutputUtil {

    public static String removeCharacters(String line){
        return line.replace("[", "").replace("]", "").replace(",", "");
    }
}
