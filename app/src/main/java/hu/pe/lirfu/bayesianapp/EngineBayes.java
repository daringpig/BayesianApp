package hu.pe.lirfu.bayesianapp;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lirfu on 24.06.17..
 */

public class EngineBayes {
    private static EngineBayes instance;

    private ArrayList<DataEntry> entries;
    private Map<String, Double> resultsApriory;
    private Map<String, Double> resultsAposteriory;

    private EngineBayes() {
        entries = new ArrayList<>();
        resultsApriory = new TreeMap<>();
        resultsAposteriory = new TreeMap<>();
    }

    public static EngineBayes getInstance() {
        if (instance == null)
            instance = new EngineBayes();

        return instance;
    }

    public EngineBayes addEntries(ArrayList<DataEntry> entries) {
        this.entries = entries;

        // Init a priory probabilities.
        for (DataEntry e : entries) {
            resultsApriory.put(e.getResult(), 0.0);

            for (String key : e.getKeys())
                resultsAposteriory.put(key + "_" + e.getResult(), 0.0);
        }

        return this;
    }

    public void calculate() {
        if (entries == null || entries.size() == 0) return;

        // Sum occurrences of features.
        for (DataEntry e : entries) {
            resultsApriory.put(e.getResult(), resultsApriory.get(e.getResult()) + 1);

            for (String key : e.getKeys())
                resultsAposteriory.put(
                        key + "_" + e.getResult(),
                        resultsAposteriory.get(key + "_" + e.getResult()) + 1
                );
        }

        // Divide values by number of features to get probabilities.
        for (DataEntry e : entries) {
            resultsApriory.put(
                    e.getResult(),
                    resultsApriory.get(e.getResult()) / resultsApriory.size()
            );

            for (String key : e.getKeys())
                resultsAposteriory.put(
                        key + "_" + e.getResult(),
                        resultsAposteriory.get(key + "_" + e.getResult()) / resultsAposteriory.size()
                );
        }
    }

    public String testData(String... keys) {
        String result = null;

        double prob;
        double max = 0;

        // Calculate probabilities of each result.
        for (String res : resultsApriory.keySet()) {
            // A priory.
            prob = resultsApriory.get(res);

            // A posteriors.
            for (String key : keys)
                if (resultsAposteriory.containsKey(key + "_" + res))
                    prob *= resultsAposteriory.get(key + "_" + res);
                else prob *= Double.MIN_VALUE;

            if (prob > max) {
                max = prob;
                result = res;
            } // TODO ne racuna dobro.
        }

        return result;
    }
}
