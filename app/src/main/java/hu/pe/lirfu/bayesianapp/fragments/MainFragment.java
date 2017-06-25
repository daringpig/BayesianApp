package hu.pe.lirfu.bayesianapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import hu.pe.lirfu.bayesianapp.util.EngineBayes;
import hu.pe.lirfu.bayesianapp.R;

/**
 * Created by lirfu on 24.06.17..
 */

public class MainFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, null);

        Button testBtn = (Button) v.findViewById(R.id.testButton);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Result: " + EngineBayes.getInstance().testData("suncano", "toplo"), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return v;
    }
}
