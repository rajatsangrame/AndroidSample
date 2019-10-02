package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region ViewModel & LiveData Demo
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(String value) {

                TextView tv = findViewById(R.id.textView);
                String newValue = getString(R.string.edit_text_value, value);
                tv.setText(newValue);
            }
        };

        mViewModel.getTxtViewValue().observe(this, observer);
        //endregion

        // Life Cycle & Observer
        new MyLocationManager(this, this, mListener);

    }

    public void onClick(View view) {

        //region ViewModel & LiveData Demo
        EditText et = findViewById(R.id.editText);
        mViewModel.setTxtViewValue(et.getText().toString());
        //endregion
    }

    private LocationListener mListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            String newValue = getString(R.string.location__value,
                    location.getLatitude() + ", " + location.getLatitude());

            ((TextView) findViewById(R.id.location))
                    .setText(newValue);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}