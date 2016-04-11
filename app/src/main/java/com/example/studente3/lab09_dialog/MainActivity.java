package com.example.studente3.lab09_dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements DialogInterface.OnClickListener,MyDialogFragment.CallBaCK {

    private TextView m_tv_message;

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    private int loginCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        m_tv_message = (TextView) findViewById(R.id.tv_message);
    }

    public void clickAlertDialog(View view) {

        new AlertDialog.Builder(this)
                .setMessage("我知道")
                .setPositiveButton("我知道", this)
                .show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        m_tv_message.setText("我知道");

    }

    public void clickAlertDialog_ok_no(View view) {
        AlertDialogYesNoListener listener = new AlertDialogYesNoListener();
        new AlertDialog.Builder(this)
                .setMessage("你好帥喔")
                .setPositiveButton("謝謝", listener)
                .setNegativeButton("狗腿", listener)
                .show();


    }

    public void Dialog_item(View view) {
        final String[] strings = getResources().getStringArray(R.array.String_array);
        new AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar)
                .setTitle("你好帥喔")
                .setItems(strings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText(strings[which]);
                    }
                }).show();
    }

    public void Dialog_MultiChoice(View view) {
        final String[] strings = getResources().getStringArray(R.array.String_array);
        final boolean[] booleans = new boolean[strings.length];
        new AlertDialog.Builder(this)
                .setTitle("你好酷")
                .setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        booleans[which] = isChecked;
                    }
                }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < strings.length; i++) {
                    if (booleans[i]) {
                        result.append(strings[i]).append("\n");
                    }
                }
                m_tv_message.setText(result);
            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_tv_message.setText("哀哀哀");
            }
        }).show();
    }

    private int m_choice;
    public void Dialog_SingleChoice(View view) {
        final String[] strings = getResources().getStringArray(R.array.String_array);
        new AlertDialog.Builder(this)
                .setTitle("你好帥")
                .setSingleChoiceItems(strings,0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_choice = which;
                    }
                }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_tv_message.setText(strings[m_choice]);
            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_tv_message.setText("安安");
            }
        }).show();
    }

    public void clickMyDialogFragment(View view) {
        DialogFragment dialog = new MyDialogFragment();
        dialog.show(getSupportFragmentManager(),"MyDialogFragment");
    }


    @Override
    public void call(CharSequence username, int which) {
        switch (which){
            case DialogInterface.BUTTON_POSITIVE:
                loginCount++;
                m_tv_message.setText("次數"+loginCount+"歡迎光臨"+username);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                m_tv_message.setText("登入取消");
                break;
        }
    }

    private class AlertDialogYesNoListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    m_tv_message.setText("謝謝");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    m_tv_message.setText("狗腿");
                    break;
            }
        }
    }
}
