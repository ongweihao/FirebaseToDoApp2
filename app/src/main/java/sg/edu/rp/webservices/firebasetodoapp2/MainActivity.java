package sg.edu.rp.webservices.firebasetodoapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // Declare variable
    TextView textViewTitle, textViewDate, textViewDays, textViewCompleted;
    EditText etTitle, etDate, etDays;
    private CheckBox cb;
    private Button btnAdd;

    // TODO: Task 1 - Declare Firebase variables
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference messagePOJOReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get UI element
        textViewDate = (TextView)findViewById(R.id.textViewDate);
        textViewDays = (TextView)findViewById(R.id.textViewDays);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewCompleted = (TextView)findViewById(R.id.textViewCompleted);
        etTitle = (EditText)findViewById(R.id.editTextTitle);
        etDate = (EditText)findViewById(R.id.editTextDate);
        etDays = (EditText)findViewById(R.id.editTextDays);
        cb = (CheckBox)findViewById(R.id.cbCompleted);
        btnAdd = (Button)findViewById(R.id.buttonAdd);


        // TODO: Task 2 - Get Firebase database instance and reference
        firebaseDatabase = FirebaseDatabase.getInstance();
        messagePOJOReference = firebaseDatabase.getReference("toDoItem");
        // TODO: Task 3 - Add a value event listener to the "message" node
        messagePOJOReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // TODO: Task 4 - Get the latest value from the dataSnapshot and display on the UI using the EditText message
                // This method will get fired everytime the "message" value updates in the realtime database. We're getting our data back as a DataSnapshot
                toDoItem message = dataSnapshot.getValue(toDoItem.class);
                textViewTitle.setText("Title: " + message.getTitle());
                textViewDays.setText("Date: " + message.getDays());
                textViewDate.setText("Days: " + message.getDate());
                textViewCompleted.setText("Completed? " + message.isCompleted());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // This method will be invoked if there is any error
                Log.e(TAG, "Database error occurred", databaseError.toException());
            }
        });

        // TODO: Task 5 - Update UI elements, and then include OnClickListener for Send Message button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Note: We're not directly updating the text view, but calling setValue() to update the data in the database instead
                if (cb.isChecked()==true) {
                    textViewCompleted.setText("Completed?: True");
                } else{
                    textViewCompleted.setText("Completed?: False");
                }
                toDoItem message = new toDoItem(etTitle.getText().toString(), etDate.getText().toString(), Integer.parseInt(etDays.getText().toString())  ,false);
                messagePOJOReference.setValue(message);
            }
        });
    }
}
