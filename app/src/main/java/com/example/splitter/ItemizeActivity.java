package com.example.splitter;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

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
    double remainingSubtotal;

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
        remainingSubtotal = subtotal;
        double tip = intent.getIntExtra("tip", 0);
        if (tip > 1) tip /= 100;
        double total = (double) Math.round(subtotal * (1 + (double)Math.round(tip * 100)/100) * 100)/100;
        names = intent.getStringArrayListExtra("names");

        bundle = new Bundle();
        bundle.putStringArrayList("names", names);
        overview.append("subtotal: " + moneyFormat(subtotal));
        overview.append("tip percentage: " + moneyFormat(tip*100));
        overview.append("tip amount: " + moneyFormat(tip * subtotal));
        overview.append("total: " + moneyFormat(total));
        overview.append("People: \n");
        for (String name : names) {
            overview.append(name + "\n");
        }

        bill = new Bill(names, subtotal, tip);

        remaining.setText(moneyFormat(subtotal));

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }

    public String moneyFormat(double dollarValue) {
        return String.format(java.util.Locale.US, "%.2f\n", dollarValue);
    }

    public void showDialog() {
        itemize = new ItemizeDialogFragment();
        itemize.setArguments(bundle);
        itemize.show(getSupportFragmentManager(), "itemize");
    }
    
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        String itemName = itemize.itemName.getText().toString();
        String itemPriceStr = itemize.itemPrice.getText().toString();

        // an item field is missing
        if (itemName.equals("") || itemPriceStr.equals("")) {
            // show toast message if empty item name or price entered
            Toast.makeText(this, "Item name and price can't be empty!", Toast.LENGTH_SHORT).show();
        } else {
            double itemPrice = Double.parseDouble(itemPriceStr);
            ArrayList<String> participants = new ArrayList<>();
            for (CheckBox cb : itemize.people) {
                if (cb.isChecked()) {
                    participants.add(cb.getText().toString());
                }
            }

            Item item = new Item(itemPrice, itemName);
            if (remainingSubtotal - itemPrice < 0) {
                Toast.makeText(this, R.string.exceed_subtotal_error,
                        Toast.LENGTH_SHORT).show();
                return;
            }
            bill.addSharedItems(item, participants);

            StringBuilder sb = new StringBuilder();
            for (String person : names) {
                Person p = bill.people.get(person);
                sb.append(p.toString());
            }
            items.setText(sb.toString());
            remaining.setText("" + remainingSubtotal);

            dialog.dismiss();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.dismiss();
    }

}
