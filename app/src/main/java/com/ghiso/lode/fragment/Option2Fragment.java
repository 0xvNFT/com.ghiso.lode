package com.ghiso.lode.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ghiso.lode.R;

import java.util.Locale;
import java.util.Random;

public class Option2Fragment extends Fragment {
    private TextView winningNumberTextView;
    private EditText inputNumberEditText;

    public Option2Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_option2, container, false);

        winningNumberTextView = rootView.findViewById(R.id.winningNumberOption2);
        inputNumberEditText = rootView.findViewById(R.id.inputNumberOption2);

        generateWinningNumber();

        return rootView;
    }

    private void generateWinningNumber() {
        int min = 0;
        int max = 99;
        int winningNumber = new Random().nextInt((max - min) + 1) + min;
        winningNumberTextView.setText(String.format(Locale.getDefault(), "%02d", winningNumber));
    }

    public boolean checkNumberMatch() {
        String inputNumberText = inputNumberEditText.getText().toString();
        String winningNumberText = winningNumberTextView.getText().toString();
        if (!inputNumberText.isEmpty() && !winningNumberText.isEmpty()) {
            int inputNumber = Integer.parseInt(inputNumberText);
            int winningNumber = Integer.parseInt(winningNumberText);
            return inputNumber == winningNumber;
        }
        return false;
    }

    public void showWinningNumber() {
        if (getView() != null) {
            winningNumberTextView = getView().findViewById(R.id.winningNumberOption2);
            winningNumberTextView.setVisibility(View.VISIBLE);
            generateWinningNumber();
        }
    }

}
