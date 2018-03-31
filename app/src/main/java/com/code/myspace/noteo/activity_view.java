package com.code.myspace.noteo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class activity_view extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser fireUser = mAuth.getCurrentUser();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();

    ProgressDialog mProgressDialog;

    NotesAdapter notesAdapter;
    List<NotesData> notesData;
    ListView scrollList;
    NotesData userMessageData;
    TextView loggedEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        scrollList = findViewById(R.id.scrollList);
        loggedEmail = findViewById(R.id.email);

        Intent intent = getIntent();
        loggedEmail.setText(intent.getStringExtra("email"));

        notesData = new ArrayList<NotesData>();
        notesAdapter = new NotesAdapter(this,R.layout.notes_card,notesData);
        scrollList.setAdapter(notesAdapter);

        showProgessBar("Loading...");
        showNotes();

    }

    private void showNotes() {
        databaseReference.child("notes_base").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String s) {
                if (snapshot.exists()) {
                    NotesData oneMsg = snapshot.getValue(NotesData.class);
                    boolean cond1 = (fireUser.getEmail().equals(loggedEmail.getText().toString()));
                    if(cond1){
                        notesData.add(new NotesData(oneMsg.userMail,oneMsg.notes,oneMsg.time));
                    }
                    notesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        hideProgressDialog();

    }


    private void showProgessBar(String msg){
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(activity_view.this);
            mProgressDialog.setMessage(msg);
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
