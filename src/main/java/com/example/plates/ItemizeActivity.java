package com.example.plates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ItemizeActivity extends FragmentActivity implements ItemizeDialogFragment.ItemizeDialogListener{

    ItemizeDialogFragment itemize;
    TextView overview;
    TextView items;
    TextView remaining;
    Bundle bundle;
    Bill bill;
    ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemize);

        overview = findViewById(R.id.overview);
        items = findViewById(R.id.items);
        remaining = findViewById(R.id.remaining);
        final FloatingActionButton addItem = findViewById(R.id.add_item);

        Intent intent = getIntent();
        double subtotal = intent.getDoubleExtra("subtotal", 0);
        double tip = intent.getIntExtra("tip", 0);
        if (tip > 1) {
            tip /= 100;
        }
        double total = subtotal * (1 + tip);
        names = intent.getStringArrayListExtra("names");

        bundle = new Bundle();
        bundle.putStringArrayList("names", names);
        overview.append("subtotal: " + subtotal + "\n");
        overview.append("tip percentage: " + tip*100 + "%\n");
        overview.append("total: " + total + "\n");
        overview.append("People: \n");
        for (String name : names) {
            overview.append(name + "\n");
        }

        bill = new Bill(names, subtotal, tip);
        String r = "" + subtotal;

        remaining.setText(r);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }

    public void showDialog() {
        itemize = new ItemizeDialogFragment();
        itemize.setArguments(bundle);
        itemize.show(getSupportFragmentManager(), "itemize");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        String itemName = itemize.itemName.getText().toString();
        double itemPrice = Double.parseDouble(itemize.itemPrice.getText().toString());
        String name = itemize.people.getSelectedItem().toString();

        Item item = new Item(itemPrice, itemName);
        bill.addItem(item, name);

        StringBuilder sb = new StringBuilder();
        for (String person : names) {
            Person p = bill.people.get(person);
            sb.append(p.toString());
        }
        items.setText(sb.toString());
        String newSubtotal = "" + bill.subtotal;
        remaining.setText(newSubtotal);

        dialog.dismiss();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.dismiss();
    }

}
