package com.itproject.game;

import java.util.Random;

public class Worldview {
    enum WorldviewType {WORLDVIEW1, WORLDVIEW2, WORLDVIEW3}

    private final City city;
    private final Random PRNG;

    public Worldview(City city) {
        this.city = city;
        this.PRNG = city.PRNG;

        generateDistribution();
    }

    int[] worldviewCount = new int[3];
    float[] worldviewDistribution = new float[3];
    final int END = worldviewCount.length;

    void generateDistribution() {
        float totalPercentage = 1;
        for (int i = 0; i < END; i++) {
            do {
                // Check whether we reached last worldview. If so -- assign the rest prob.
                worldviewDistribution[i] = (i == END - 1) ? totalPercentage : PRNG.nextFloat();
            } while ((worldviewDistribution[i] >= totalPercentage) && (i != END - 1));

            totalPercentage -= worldviewDistribution[i];
        }
    }

    WorldviewType determineType() {
        int randWorldview;
        for (randWorldview = PRNG.nextInt(END - 1); (worldviewCount[randWorldview] + 1) /
                city.citizens.size() >= worldviewDistribution[randWorldview]; randWorldview = PRNG.nextInt(END - 1)) {}

        switch (randWorldview) {
            case 0:
                return WorldviewType.WORLDVIEW1;
            case 1:
                return WorldviewType.WORLDVIEW2;
            case 2:
                return WorldviewType.WORLDVIEW3;
            default:
                throw new IllegalStateException();
        }
    }

    void assess() {

    }
}
