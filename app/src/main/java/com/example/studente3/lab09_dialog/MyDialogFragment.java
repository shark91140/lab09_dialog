package com.example.studente3.lab09_dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment {


    private EditText m_et_Name;

    public MyDialogFragment() {
        // Required empty public constructor
    }

    public interface CallBaCK {
        void call(CharSequence username, int which);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_my_dialog, null);
        m_et_Name = (EditText) view.findViewById(R.id.et_userName);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CharSequence username = m_et_Name.getText();
                        ((MyDialogFragment.CallBaCK) getActivity()).call(username, which);
                    }
                }).setNegativeButton("sign out", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((MyDialogFragment.CallBaCK) getActivity()).call(null, which);
            }
        });
        return builder.create();
    }
}
