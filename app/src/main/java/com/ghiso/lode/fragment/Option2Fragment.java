package com.ghiso.lode.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ghiso.lode.R;

import java.util.Locale;

public class Option2Fragment extends Fragment {
    private TextView totalMoneyTextView;
    private double totalMoney = 0.0;

    public Option2Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_option2, container, false);

        totalMoneyTextView = rootView.findViewById(R.id.totalMoneyTextView);

        Bundle bundle = getArguments();
        if (bundle != null) {
            totalMoney = bundle.getDouble("totalMoney");
        }

        totalMoneyTextView.setText(String.format(Locale.getDefault(), "%.2f", totalMoney));

        return rootView;
    }
}
