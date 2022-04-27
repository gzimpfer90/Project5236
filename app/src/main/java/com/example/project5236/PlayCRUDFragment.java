package com.example.project5236;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project5236.databinding.FragmentPlayCrudBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PlayCRUDFragment extends Fragment {

    private static final String TAG = "READ: ";
    private String nextName;
    private int nextLevelVal;
    private String readList;
    private int updateStars = 0;
    private int updateLevelVal;
    private String updateLevelName;
    private int deleteLevelVal;
    private String deleteLevelName;
    private FragmentPlayCrudBinding binding;
    FirebaseDatabase rootNode;
    DatabaseReference levelsReference;
    DatabaseReference nextReference;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentPlayCrudBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                levelsReference = rootNode.getReference("Levels");
                nextReference = rootNode.getReference("NextLevel");

                nextReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        nextName = "Level" + snapshot.getValue().toString();
                        nextLevelVal = Integer.parseInt(snapshot.getValue().toString());
                        LevelHelperClass nextLevelHelper = new LevelHelperClass( 0, 0, 0);
                        nextReference.setValue(nextLevelVal + 1);
                        levelsReference.child(nextName).setValue(nextLevelHelper);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        binding.buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                levelsReference = rootNode.getReference("Levels");
                nextReference = rootNode.getReference("NextLevel");
                levelsReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        readList = snapshot.getValue().toString();
                        Log.d(TAG, readList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        binding.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                levelsReference = rootNode.getReference("Levels");
                nextReference = rootNode.getReference("NextLevel");
                nextReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        updateStars += 1;
                        if (updateStars == 4) {
                            updateStars = 0;
                        }
                        updateLevelVal = Integer.parseInt(snapshot.getValue().toString()) - 1;
                        updateLevelName = "Level" + updateLevelVal;
                        LevelHelperClass updateHelper = new LevelHelperClass(0, updateStars, 0);
                        levelsReference.child(updateLevelName).setValue(updateHelper);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                levelsReference = rootNode.getReference("Levels");
                nextReference = rootNode.getReference("NextLevel");
                nextReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        deleteLevelVal = Integer.parseInt(snapshot.getValue().toString()) - 1;
                        deleteLevelName = "Level" + deleteLevelVal;
                        levelsReference.child(deleteLevelName).removeValue();
                        nextReference.setValue(deleteLevelVal);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}
