package com.example.project5236;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.project5236.databinding.FragmentHighScoresBinding;
import com.example.project5236.databinding.FragmentSecondBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class HighScoresFragment extends Fragment {

    private FragmentHighScoresBinding binding;
    private static final String TAG = "Testing: ";

    FirebaseDatabase rootNode;
    DatabaseReference levelsReference;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentHighScoresBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> level_list = new ArrayList<>();
        ListView listView = binding.highScoreList;
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                level_list);
        listView.setAdapter(listViewAdapter);

        rootNode = FirebaseDatabase.getInstance();
        levelsReference = rootNode.getReference("Levels");

        levelsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                level_list.clear();
                HashMap<String, Long> hashMap;
                for(DataSnapshot ds : snapshot.getChildren()) {
                    hashMap = (HashMap<String, Long>) ds.getValue();
                    Long completeValue = hashMap.get("complete");
                    String completeString = "";
                    if (completeValue > 0) {
                        completeString = "Complete";
                    } else {
                        completeString = "Incomplete";
                    }
                    level_list.add(
                            getString(R.string.level) + " " + ds.getKey().substring(5) + ": "
                                    + getString(R.string.completed) + ", "
                                    + "Score: " + hashMap.get("score").toString() + ", "
                                    + getString(R.string.star) + ": "
                                    + hashMap.get("stars").toString());
                }
                listViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HighScoresFragment.this)
                        .navigate(R.id.action_HighScoresFragment_to_FirstFragment);
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
        Log.d(TAG, "HSFragment on resume occurred");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "HSFragment on pause occurred");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "HSFragment on stop occurred");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "HSFragment on destroy occurred");
    }
}