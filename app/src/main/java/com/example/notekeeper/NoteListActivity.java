package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private ArrayAdapter<NoteInfo> mAdapterNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this, NoteActivity.class));
            }
        });

        initializeDisplayContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapterNotes.notifyDataSetChanged();
    }

    private void initializeDisplayContent() {
        // Get list view
        final ListView listNotes = findViewById(R.id.list_notes);

        // Get notes using the data manager custom class
        List<NoteInfo> notes = DataManager.getInstance().getNotes();

        // Format the notes using the built-in class
        mAdapterNotes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);

        // Add notes to ui
        listNotes.setAdapter(mAdapterNotes);

        // Add click event to note list items
        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Create an intent
                Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);

                // Get selected note
//                NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(position);

                // Add the selected note as an extra to the intent
                intent.putExtra(NoteActivity.NOTE_POSITION, position);

                // Start the activity passing in the intent
                startActivity(intent);
            }
        });
    }
}