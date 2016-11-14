package com.itproject.game;

public class BiasedRandom {
    private static byte[] precisionOfOneTenth = new byte[10];
    private static byte[] precisionOfOneHundredth = new byte[100];
    private static byte[] precisionOfOneThousandth = new byte[1000];
    private final City city;

    public BiasedRandom(City city) {
        this.city = city;
    }

    public byte nextByte(float[] probabilities, short precision) {
        float totalPercentage = 0;
        short throwDie;
        byte result = -1;

        // Fill the appropriate array
        for (byte i = 1; i < probabilities.length; i++) {
            switch (precision) {
                case 10:
                    for (byte j = (byte) (totalPercentage * 10); j < (byte) (totalPercentage * 10 + probabilities[i] * 10); j++) {
                        precisionOfOneTenth[j] = i;
                    }
                    totalPercentage += probabilities[i] + 0.1;
                    break;
                case 100:
                    for (byte j = (byte) (totalPercentage * 100); j < (byte) (totalPercentage * 100 + probabilities[i] * 100); j++) {
                        precisionOfOneHundredth[j] = i;
                    }
                    totalPercentage += probabilities[i] + 0.01;
                    break;
                case 1000:
                    for (short j = (short) (totalPercentage * 1000); j < (short) (totalPercentage * 1000 + probabilities[i] * 1000); j++) {
                        precisionOfOneThousandth[j] = i;
                    }
                    totalPercentage += probabilities[i] + 0.001;
                    break;
            }
        }

        // Generate number
        throwDie = (short) city.PRNG.nextInt(precision - 1);
        switch (precision) {
            case 10:
                result = precisionOfOneTenth[throwDie];
                break;
            case 100:
                result = precisionOfOneHundredth[throwDie];
                break;
            case 1000:
                result = precisionOfOneThousandth[throwDie];
                break;
        }

        // Clear the array for use in future
        switch (precision) {
            case 10:
                for (byte i = 0; i < (byte) (totalPercentage * 10); i++) {
                    precisionOfOneHundredth[i] = 0;
                }
                break;
            case 100:
                for (byte i = 0; i < (byte) (totalPercentage * 100); i++) {
                    precisionOfOneHundredth[i] = 0;
                }
                break;
            case 1000:
                for (short i = 0; i < (short) (totalPercentage * 1000); i++) {
                    precisionOfOneThousandth[i] = 0;
                }
                break;
        }

        return result;
    }
}
