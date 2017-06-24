package hu.pe.lirfu.bayesianapp;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lirfu on 18.06.17..
 */

public class DataListAdapter implements ListAdapter {
    private static DataListAdapter dataListAdapter;

    private ArrayList<DataEntry> data;
    private ArrayList<DataSetObserver> observers;
    private ArrayList<View> views;

    public static DataListAdapter getInstance() {
        if (dataListAdapter == null)
            dataListAdapter = new DataListAdapter();

        return dataListAdapter;
    }

    private DataListAdapter() {
        observers = new ArrayList<>();
        data = new ArrayList<>();
        views = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(new FileReader(Globals.DATA_FILENAME));

            String tmp;
            while ((tmp = reader.readLine()) != null)
                data.add(DataEntry.parseFromString(tmp));

        } catch (FileNotFoundException e) {
            Log.e(Globals.TAG, "Data file not found: " + Globals.DATA_FILENAME);
        } catch (IOException e) {
            Log.e(Globals.TAG, "Error reading data file: " + e.getMessage());
        }

        data.add(new DataEntry("DA", "suncano", "vruce"));
        data.add(new DataEntry("DA", "suncano", "toplo"));
        data.add(new DataEntry("DA", "oblacno", "toplo"));
        data.add(new DataEntry("NE", "suncano", "vruce"));
        data.add(new DataEntry("NE", "oblacno", "hladno"));
        data.add(new DataEntry("NE", "kisa", "hladno"));
        data.add(new DataEntry("DA", "suncano", "vruce"));
        data.add(new DataEntry("DA", "suncano", "toplo"));
        data.add(new DataEntry("NE", "kisa", "toplo"));
        data.add(new DataEntry("DA", "oblacno", "vruce"));

        EngineBayes.getInstance().addEntries(data).calculate();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        observers.add(dataSetObserver);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        observers.remove(dataSetObserver);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            Context c = viewGroup.getContext();
            LayoutInflater inflater = LayoutInflater.from(c);
            view = inflater.inflate(R.layout.list_entry, viewGroup, false);
        }

        ((TextView)view.findViewById(R.id.entry_result)).setText(data.get(i).getResult());
        ((TextView)view.findViewById(R.id.entry_title)).setText(data.get(i).getKeyInitials());

        return view;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
