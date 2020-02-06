package com.example.splitter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
                if (name.length() == 0) {
                    // received an empty name. Should pop-up an error dialog and not add to list
                    // Use the AlertDialog.Builder to configure the AlertDialog.
                    final AlertDialog.Builder alertDialogBuilder =
                            new AlertDialog.Builder(view.getContext())
                                    .setTitle("Blank Name")
                                    .setMessage("Name cannot be empty!")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    ;

                    // Show the AlertDialog.
                    alertDialogBuilder.show();
                } else {    // received non-empty name. Add to list.
                    people.add(name);
                    name_list.append(name + "\n");
                    names.setText("");
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ItemizeActivity.class);
                if (subtotal.getText().length() == 0) {
                    final AlertDialog.Builder alertDialogBuilder =
                            new AlertDialog.Builder(view.getContext())
                                    .setTitle("Empty Subtotal")
                                    .setMessage("Subtotal cannot be empty!")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                            ;

                    // Show the AlertDialog.
                    alertDialogBuilder.show();
                    return;
                }
                intent.putExtra("subtotal", Double.parseDouble(subtotal.getText().toString()));
                int tip_val = 0;
                if (tip.getText().toString().length() > 0) {
                    tip_val = Integer.parseInt(tip.getText().toString());
                }
                intent.putExtra("tip", tip_val);
                intent.putExtra("names", people);

                startActivity(intent);
            }
        });
    }
}
