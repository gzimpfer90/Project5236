package com.example.project5236;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.project5236.databinding.FragmentSecondBinding;

public class LevelSelectFragment extends Fragment {

    private FragmentSecondBinding binding;
    private static final String TAG = "Testing: ";

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LevelSelectFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "on resume occurred");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "on pause occurred");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "on stop occurred");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "on destroy occurred");
    }

}