package hu.pe.lirfu.bayesianapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import hu.pe.lirfu.bayesianapp.DataListAdapter;
import hu.pe.lirfu.bayesianapp.R;
import hu.pe.lirfu.bayesianapp.util.DataEntry;
import hu.pe.lirfu.bayesianapp.util.EngineBayes;
import hu.pe.lirfu.bayesianapp.util.Pair;

/**
 * Created by lirfu on 24.06.17..
 */

public class DataFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_data, null);

        Iterator<DataEntry> it = EngineBayes.getInstance().getEntriesIterator();
        ArrayList<Pair> entries = new ArrayList<>();
        while(it.hasNext()){
            DataEntry entry = it.next();
            entries.add(new Pair(entry.getResult(), entry.getfeatureInitials()));
        }

        GridView mainDataList = (GridView) v.findViewById(R.id.main_data_list);
        DataListAdapter adapter = new DataListAdapter(entries, true);
        mainDataList.setAdapter(adapter);

        TextView title = (TextView) v.findViewById(R.id.database_title);
        title.setText(title.getText().toString().replace("_NUM_", "" + adapter.getCount()));

        return v;
    }
}
