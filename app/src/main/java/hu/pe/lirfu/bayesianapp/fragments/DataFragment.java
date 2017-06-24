package hu.pe.lirfu.bayesianapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import hu.pe.lirfu.bayesianapp.DataListAdapter;
import hu.pe.lirfu.bayesianapp.R;

/**
 * Created by lirfu on 24.06.17..
 */

public class DataFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_data, null);

        GridView mainDataList = (GridView) v.findViewById(R.id.main_data_list);
        DataListAdapter adapter = DataListAdapter.getInstance();
        mainDataList.setAdapter(adapter);

        TextView title = (TextView) v.findViewById(R.id.database_title);
        title.setText(title.getText().toString().replace("_NUM_", "" + adapter.getCount()));

        return v;
    }
}
