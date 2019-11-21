package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.test.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MainActivityViewModel mViewModel;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //region ViewModel & LiveData Demo
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mBinding.setViewmodel(mViewModel);

        mViewModel.getTxtViewValue().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String value) {

                String newValue = getString(R.string.edit_text_value, value);
                mBinding.textView.setText(newValue);
            }
        });
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
                    location.getLatitude() + ", " + location.getLongitude());
            mViewModel.setTxtViewValue(newValue);
            mViewModel.lat.set(location.getLatitude() + "");

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