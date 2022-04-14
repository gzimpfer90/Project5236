package com.example.project5236;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.project5236.databinding.FragmentSecondBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LevelSelectFragment extends Fragment {

    private FragmentSecondBinding binding;
    private static final String TAG = "Testing: ";

    FirebaseDatabase rootNode;
    DatabaseReference levelsReference;

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

        ArrayList<String> level_list = new ArrayList<>();
        ListView listView = binding.levellist;
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                level_list);
        listView.setAdapter(listViewAdapter);

        rootNode = FirebaseDatabase.getInstance();
        levelsReference = rootNode.getReference("NextLevel");

        levelsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                level_list.clear();
                String nextL = snapshot.getValue().toString();
                int nextLevel = Integer.parseInt(nextL);
                for (int i = 0; i < nextLevel - 1; i++) {
                    level_list.add("Level "+(i+1));
                }
                listViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView < ? > parent, View view, int position, long id) {

                String info = ( (TextView) view ).getText().toString();
                Toast.makeText( getContext(), "Selected " + info, Toast.LENGTH_LONG ).show();
                NavHostFragment.findNavController(LevelSelectFragment.this)
                        .navigate(R.id.action_SecondFragment_to_GameActivity);
            }
        });

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
        Log.d(TAG, "LSFragment on resume occurred");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "LSFragment on pause occurred");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "LSFragment on stop occurred");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "LSFragment on destroy occurred");
    }

}