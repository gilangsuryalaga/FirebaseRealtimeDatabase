package com.example.firebaserealtimedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTrackActivity extends AppCompatActivity {

    TextView textViewArtistName;
    EditText editTextTrackName;
    SeekBar seekBarRating;

    Button buttonAddTrack;

    ListView listViewTracks;
    DatabaseReference databaseTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track);

        textViewArtistName = (TextView) findViewById(R.id.textViewArtistName);
        editTextTrackName = (EditText) findViewById(R.id.editTextTrackName);
        seekBarRating = (SeekBar) findViewById(R.id.seekBarRating);
        buttonAddTrack = (Button) findViewById(R.id.buttonAddTrack);
        listViewTracks = (ListView) findViewById(R.id.listViewTracks);

        Intent intent = getIntent();

        String id = intent.getStringExtra(MainActivity.ARTIST_ID);
        String name = intent.getStringExtra(MainActivity.ARTIST_NAME);

        textViewArtistName.setText(name);

        databaseTracks = FirebaseDatabase.getInstance().getReference("tracks").child(id);

        buttonAddTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTrack();
            }
        });
    }

    private void saveTrack(){
        String trackname = editTextTrackName.getText().toString().trim();
        int rating = seekBarRating.getProgress();

        if (!TextUtils.isEmpty(trackname)){
            String id = databaseTracks.push().getKey();

            Track track = new Track(id,trackname,rating);
            databaseTracks.child(id).setValue(track);
            Toast.makeText(this, " Track saved Succesfully", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Track should not be Empty", Toast.LENGTH_LONG).show();
        }
    }
}
