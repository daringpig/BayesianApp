package hu.pe.lirfu.bayesianapp;

/**
 * Created by lirfu on 18.06.17..
 */

public class DataEntry {
    private String[] keys;
    private String result;

    DataEntry(String result, String... keys) {
        this.keys = keys;
        this.result = result;
    }

    public String[] getKeys() {
        return keys;
    }

    public String getResult() {
        return result;
    }

    public String getKeyInitials(){
        String str="";

        for(String key:keys)
        str+=key.charAt(0);

        return str;
    }

    @Override
    public String toString() {
        String s = "";

        for (String d : keys)
            s += d + ",";

        s += result;

        return s;
    }

    /**
     * Interprets the CSV format.<br>
     * First n-1 entries become keys, last entry becomes the result.
     */
    public static DataEntry parseFromString(String string) {
        String[] parts = string.split(",");

        String[] keys = new String[parts.length - 1];
        String result = parts[parts.length - 1];


        System.arraycopy(parts, 0, keys, 0, parts.length - 1);
        return new DataEntry(result, keys);
    }

    @Override
    public boolean equals(Object obj) {
        if(keys.length != ((DataEntry)obj).keys.length) return false;

        for (String s : keys)
            if (!s.equals(obj))
                return false;

        return result.equals(obj);
    }
}
