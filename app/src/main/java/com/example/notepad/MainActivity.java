package com.example.notepad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    DatabaseReference databaseReference;
    List<CardModel> list;
    NotesAdapter adapter;
    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        recyclerview = findViewById(R.id.recyclerview);

        databaseReference = FirebaseDatabase.getInstance().getReference("note");

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new NotesAdapter(MainActivity.this, list);
        recyclerview.setAdapter(adapter);

        loadNotes();

        fab.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddNote.class));
        });

    }

    public void loadNotes(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for (DataSnapshot data : snapshot.getChildren()) {
                    CardModel note = data.getValue(CardModel.class);
                    list.add(note);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to load notes", Toast.LENGTH_SHORT).show();
            }
        });
    }
}