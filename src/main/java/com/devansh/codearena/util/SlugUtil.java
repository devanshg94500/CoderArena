package com.devansh.codearena.util;

public class SlugUtil {

    private SlugUtil() {
    }

    public static String generate(String title) {

        return title
                .trim()
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-+", "-");
    }
}