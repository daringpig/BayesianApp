package hu.pe.lirfu.bayesianapp.util;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lirfu on 25.06.17..
 */

public class DemoSource implements Source {
    private ArrayList<DataEntry> entries;

    public DemoSource() {
        entries = new ArrayList<>();

        entries.add(new DataEntry("DA", "suncano", "vruce"));
        entries.add(new DataEntry("DA", "suncano", "toplo"));
        entries.add(new DataEntry("DA", "oblacno", "toplo"));
        entries.add(new DataEntry("NE", "suncano", "vruce"));
        entries.add(new DataEntry("NE", "oblacno", "hladno"));
        entries.add(new DataEntry("NE", "kisa", "hladno"));
        entries.add(new DataEntry("DA", "suncano", "vruce"));
        entries.add(new DataEntry("DA", "suncano", "toplo"));
        entries.add(new DataEntry("NE", "kisa", "toplo"));
        entries.add(new DataEntry("DA", "oblacno", "vruce"));
    }

    @Override
    public List<DataEntry> loadEntries(Context context) {
        return entries;
    }

    @Override
    public void storeEntries(Context context, List<DataEntry> entries) {
        this.entries = new ArrayList<>(entries);
    }
}
