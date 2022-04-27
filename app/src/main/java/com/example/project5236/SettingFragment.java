package com.example.project5236;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.project5236.databinding.FragmentSecondBinding;
import com.example.project5236.databinding.FragmentSettingBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class SettingFragment extends Fragment {

    private FragmentSettingBinding binding;
    FirebaseDatabase rootNode;
    DatabaseReference levelsReference;
    DatabaseReference nextReference;
    private int deleteLevelVal;
    private String deleteLevelName;
    private static final String TAG = "Testing: ";

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSettingBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                levelsReference = rootNode.getReference("Levels");
                nextReference = rootNode.getReference("NextLevel");
                nextReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        deleteLevelVal = Integer.parseInt(snapshot.getValue().toString()) - 1;
                        while (deleteLevelVal != 1) {
                            deleteLevelName = "Level" + deleteLevelVal;
                            levelsReference.child(deleteLevelName).removeValue();
                            nextReference.setValue(deleteLevelVal);
                            deleteLevelVal--;
                        }
                        String currentName = "Level1";
                        LevelHelperClass completeUpdate = new LevelHelperClass(0,0, 0);
                        levelsReference.child(currentName).setValue(completeUpdate);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SettingFragment.this)
                        .navigate(R.id.action_SettingFragment_to_FirstFragment);
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
        Log.d(TAG, "SettingFragment on resume occurred");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "SettingFragment on pause occurred");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "SettingFragment on stop occurred");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "SettingFragment on destroy occurred");
    }

}