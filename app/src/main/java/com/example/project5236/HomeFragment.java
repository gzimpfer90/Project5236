package com.example.project5236;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.project5236.databinding.FragmentFirstBinding;

public class HomeFragment extends Fragment {

    private FragmentFirstBinding binding;
    private static final String TAG = "Testing: ";

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(HomeFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_PlayCRUDFragment);
                startActivity(new Intent(getContext(), PlayActivity.class));
            }
        });

        binding.levelSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        binding.highScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_FirstFragment_to_HighScoresFragment);
            }
        });
        binding.settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SettingFragment);
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
        Log.d(TAG, "HomeFragment on resume occurred");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "HomeFragment on pause occurred");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "HomeFragment on stop occurred");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "HomeFragment on destroy occurred");
    }

}