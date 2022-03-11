package com.example.project5236;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.project5236.databinding.FragmentPlayCrudBinding;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class PlayCRUDFragment extends Fragment {

    private static final String TAG = "READ: ";
    private String nextName;
    private int nextLevelVal;
    private String readList;
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
                        LevelHelperClass nextLevelHelper = new LevelHelperClass( 0, 0);
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
    }
}
