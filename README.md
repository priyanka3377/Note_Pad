Note_Pad 📝

A simple Android note-taking app built with Java and Firebase Realtime Database.
Easily add, view, and manage your notes in a clean and intuitive interface.

Features ✨

Add new notes with title and content

View all saved notes in a RecyclerView

Firebase Realtime Database integration for cloud storage

Clean and responsive UI

Edit and delete notes (optional enhancement)

Installation 🚀

Clone the repository

git clone https://github.com/priyanka3377/Note_Pad.git
cd Note_Pad


Add Firebase Configuration

Go to Firebase Console

Create a new project → Add an Android app with your package name

Download the google-services.json file

Place it in the /app folder of your project

Open in Android Studio

Sync Gradle

Build and run the app on your device or emulator

Firebase Setup 🔧

The app uses Firebase Realtime Database for storing notes.

Make sure your database rules allow read/write access for authenticated users (or public for testing):

{
  "rules": {
    ".read": true,
    ".write": true
  }
}


⚠️ For production, secure your database properly.

Project Structure 🗂️
Note_Pad/
├── app/

│   ├── java/com/example/notepad/

│   │   ├── MainActivity.java     # Displays list of notes

│   │   ├── AddNoteActivity.java    # Add new notes

│   │   ├── NotesAdapter.java       # RecyclerView adapter

│   │   └── CardModel.java          # Note model

│   └── res/

│       ├── layout/                  # XML layouts

│       └── drawable/                # Icons, images

├── build.gradle

├── settings.gradle

└── README.md

Future Enhancements 💡

Add categories or tags for notes

Implement search functionality

Use Firebase Authentication to manage users

Add dark mode for better UX

Contributing 🤝

Contributions are welcome! For major changes please open an issue first to discuss changes. Suggestions are welcomed!
