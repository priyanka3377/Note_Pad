package com.example.notepad;

public class CardModel {
    String noteId;
    String title;
    String content;

    public CardModel() {
        // Required empty constructor for Firebase
    }

    public CardModel(String noteId, String title, String content) {
        this.noteId = noteId;
        this.title = title;
        this.content = content;
    }

    public String getNoteId() {
        return noteId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
