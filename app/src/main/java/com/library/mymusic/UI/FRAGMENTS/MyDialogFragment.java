package com.library.mymusic.UI.FRAGMENTS;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.library.mymusic.R;


public class MyDialogFragment extends DialogFragment {
    public static MyDialogFragment newInstance(String title) {
        MyDialogFragment alertFragment = new MyDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        alertFragment.setArguments(args);
        return alertFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Dialog dialog = new Dialog(getActivity());


        dialog.setContentView(R.layout.fragment_my_dialog);

        SeekBar seekBar1 = (SeekBar) dialog.findViewById(R.id.seekBar1);
        SeekBar seekBar2 = (SeekBar) dialog.findViewById(R.id.seekBar2);

        Button submit = (Button) dialog.findViewById(R.id.submitButton);

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }
}
