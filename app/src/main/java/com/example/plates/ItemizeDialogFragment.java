package com.example.plates;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemizeDialogFragment extends DialogFragment {

    EditText itemPrice;
    EditText itemName;
    ArrayList<CheckBox> people;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.fragment_itemize, null);
        itemPrice = dialogView.findViewById(R.id.item_price);
        itemName = dialogView.findViewById(R.id.item_name);
        people = new ArrayList<>();

        ArrayList<String> names = getArguments().getStringArrayList("names");

        LinearLayout ll = dialogView.findViewById(R.id.participants);
        for (String name: names) {
            CheckBox cb = new CheckBox(this.getContext());
            cb.setText(name);
            people.add(cb);
            ll.addView(cb);
        }

        builder.setView(dialogView)
                .setPositiveButton("Add Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        itemizeListener.onDialogPositiveClick(ItemizeDialogFragment.this);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        itemizeListener.onDialogNegativeClick(ItemizeDialogFragment.this);
                    }
                });

        return builder.create();
    }

    public interface ItemizeDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    private ItemizeDialogListener itemizeListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            itemizeListener = (ItemizeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException((""));
        }
    }

}
