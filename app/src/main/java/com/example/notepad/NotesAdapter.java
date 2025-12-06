package com.example.notepad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    Context context;
    List<CardModel> list;

    public NotesAdapter(Context context, List<CardModel> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        CardModel note = list.get(position);
        holder.title.setText(note.getTitle());
        holder.content.setText(note.getContent());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddNote.class);
            intent.putExtra("noteId", note.getNoteId());
            intent.putExtra("title", note.getTitle());
            intent.putExtra("content", note.getContent());
            context.startActivity(intent);
        });

        holder.delete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Note")
                    .setMessage("Are you sure you want to delete this note?")
                    .setPositiveButton("Delete", (dialog, which) -> {

                        int pos = holder.getAdapterPosition();
                        if (pos == RecyclerView.NO_POSITION) return;

                        CardModel model = list.get(pos);
                        String noteId = model.getNoteId();

                        FirebaseDatabase.getInstance().getReference("note")
                                .child(noteId)
                                .removeValue()
                                .addOnSuccessListener(success -> {
                                    Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT).show();
                                });
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;
        ImageButton delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.card_title);
            content = itemView.findViewById(R.id.card_content);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
