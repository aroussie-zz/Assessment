package com.allstate.alexandreroussiere.allstate.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.allstate.alexandreroussiere.allstate.R;
import com.allstate.alexandreroussiere.allstate.network.OnCoordinatesChangedListener;

/**
 * Created by Alexandre Roussière on 23/08/2016.
 */
public class AccelerometerFragment extends Fragment implements OnCoordinatesChangedListener,
        View.OnClickListener {

    private static final String TAG = "AccelerometerFragment";

    private View view;
    private TextView xValue;
    private TextView yValue;
    private TextView zValue;
    private Button button;
    private AccelerometerPresenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.accelerometer_layout, container, false);
        setViews();
        presenter = new AccelerometerPresenter(this, getContext());
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.stopAccelerometer();
    }

    private void setViews() {
        xValue = (TextView) view.findViewById(R.id.coordinateX);
        yValue = (TextView) view.findViewById(R.id.coordinateY);
        zValue = (TextView) view.findViewById(R.id.coordinateZ);

        xValue.setText(getString(R.string.coordinateX, 0.0));
        yValue.setText(getString(R.string.coordinateY, 0.0));
        zValue.setText(getString(R.string.coordinateZ, 0.0));

        button = (Button) view.findViewById(R.id.button_accelerometer);
        button.setText(R.string.buttonStart);
        button.setOnClickListener(this);
    }


    @Override
    public void updateUI(float x, float y, float z) {
        xValue.setText(getString(R.string.coordinateX, x));
        yValue.setText(getString(R.string.coordinateY, y));
        zValue.setText(getString(R.string.coordinateZ, z));
    }

    @Override
    public void displayMessageError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_accelerometer) {
            if (button.getText() == getResources().getString(R.string.buttonStart)) {
                button.setText(R.string.buttonStop);
                presenter.startAccelerometer();
            } else if (button.getText() == getResources().getString(R.string.buttonStop)) {
                button.setText(R.string.buttonStart);
                presenter.stopAccelerometer();
            }
        }
    }
}
