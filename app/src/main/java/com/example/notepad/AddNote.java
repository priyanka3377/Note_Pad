package com.example.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNote extends AppCompatActivity {

    EditText editTextTitle, editTextContent;
    FloatingActionButton saveNoteBtn;
    DatabaseReference databaseReference;
    String noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_note);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Note");


        editTextTitle = findViewById(R.id.editTextTitle);
        editTextContent = findViewById(R.id.editTextContent);
        saveNoteBtn = findViewById(R.id.saveNoteBtn);

        Intent intent = getIntent();
        if (intent != null) {
            noteId = intent.getStringExtra("noteId");
            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");

            if (title != null && content != null) {
                editTextTitle.setText(title);
                editTextContent.setText(content);
            }
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("note");

        saveNoteBtn.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString().trim();
            String content = editTextContent.getText().toString().trim();

            if (title.isEmpty() && content.isEmpty()){
                Toast.makeText(this, "Please write something before saving", Toast.LENGTH_SHORT).show();
                return;
            }

            databaseReference = FirebaseDatabase.getInstance().getReference("note");

            if (noteId == null) {
                noteId = databaseReference.push().getKey();
            }

            CardModel cardModel = new CardModel(noteId, title, content);
            databaseReference.child(noteId).setValue(cardModel)
                    .addOnSuccessListener(save -> {
                        Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(fail -> Toast.makeText(this, "Failed to save note", Toast.LENGTH_SHORT).show());
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}