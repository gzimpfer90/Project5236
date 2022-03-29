package com.example.project5236;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project5236.databinding.FragmentPlayBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PlayFragment extends Fragment {

    private static final String TAG = "READ: ";
    private String nextName;
    private int nextLevelVal;
    private String readList;
    private int updateStars = 0;
    private int updateLevelVal;
    private String updateLevelName;
    private int deleteLevelVal;
    private String deleteLevelName;
    private FragmentPlayBinding binding;
    FirebaseDatabase rootNode;
    DatabaseReference levelsReference;
    DatabaseReference nextReference;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentPlayBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}

