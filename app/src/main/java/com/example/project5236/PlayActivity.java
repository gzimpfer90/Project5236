package com.example.project5236;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project5236.databinding.ActivityPlayBinding;

public class PlayActivity extends AppCompatActivity{

    private static final String TAG = "Testing: ";
    private ActivityPlayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "PlayActivity on start occurred");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "PlayActivity on resume occurred");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "PlayActivity on pause occurred");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "PlayActivity on stop occurred");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "PlayActivity on destroy occurred");
    }
}
