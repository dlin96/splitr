package com.example.plates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // widgets
    EditText names;
    EditText tip;
    EditText subtotal;
    TextView name_list;
    TextView bill_params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> people = new ArrayList<>();

        names = findViewById(R.id.names);
        name_list = findViewById(R.id.name_list);
        tip = findViewById(R.id.tip);
        subtotal = findViewById(R.id.subtotal);
        bill_params = findViewById(R.id.bill_params);
        final FloatingActionButton next = findViewById(R.id.next_activity);
        final Button addPerson = findViewById(R.id.add_person);

        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = names.getText().toString();
                people.add(name);
                name_list.append(name + "\n");
                names.setText("");
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ItemizeActivity.class);
                intent.putExtra("subtotal", Double.parseDouble(subtotal.getText().toString()));
                intent.putExtra("tip", Integer.parseInt(tip.getText().toString()));
                intent.putExtra("names", people);

                startActivity(intent);
            }
        });
    }
}
