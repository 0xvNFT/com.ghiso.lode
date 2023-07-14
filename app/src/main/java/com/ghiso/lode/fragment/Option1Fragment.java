package com.ghiso.lode.fragment;

import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.ghiso.lode.R;

import java.util.Locale;

public class Option1Fragment extends Fragment implements View.OnClickListener, MoneyInputDialogFragment.MoneyInputListener {
    private FrameLayout[] numberFrameLayouts;
    private ImageView[] numberImageViews;
    private TextView[] numberTextViews;

    private FrameLayout[] number00FrameLayouts;
    private ImageView[] number00ImageViews;
    private TextView[] number00TextViews;

    private TextView totalMoneyTextView;
    private double totalMoney = 0.0;
    private SharedPreferences sharedPreferences;
    public Option1Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_option1, container, false);

        //FIRST
        numberFrameLayouts = new FrameLayout[]{
                rootView.findViewById(R.id.number0FrameLayout),
                rootView.findViewById(R.id.number1FrameLayout),
                rootView.findViewById(R.id.number2FrameLayout),
                rootView.findViewById(R.id.number3FrameLayout),
                rootView.findViewById(R.id.number4FrameLayout),
                rootView.findViewById(R.id.number5FrameLayout),
                rootView.findViewById(R.id.number6FrameLayout),
                rootView.findViewById(R.id.number7FrameLayout),
                rootView.findViewById(R.id.number8FrameLayout),
                rootView.findViewById(R.id.number9FrameLayout)
        };

        numberImageViews = new ImageView[]{
                rootView.findViewById(R.id.image0ImageView),
                rootView.findViewById(R.id.image1ImageView),
                rootView.findViewById(R.id.image2ImageView),
                rootView.findViewById(R.id.image3ImageView),
                rootView.findViewById(R.id.image4ImageView),
                rootView.findViewById(R.id.image5ImageView),
                rootView.findViewById(R.id.image6ImageView),
                rootView.findViewById(R.id.image7ImageView),
                rootView.findViewById(R.id.image8ImageView),
                rootView.findViewById(R.id.image9ImageView)
        };

        numberTextViews = new TextView[]{
                rootView.findViewById(R.id.number0TextView),
                rootView.findViewById(R.id.number1TextView),
                rootView.findViewById(R.id.number2TextView),
                rootView.findViewById(R.id.number3TextView),
                rootView.findViewById(R.id.number4TextView),
                rootView.findViewById(R.id.number5TextView),
                rootView.findViewById(R.id.number6TextView),
                rootView.findViewById(R.id.number7TextView),
                rootView.findViewById(R.id.number8TextView),
                rootView.findViewById(R.id.number9TextView)
        };
        //SECOND
        number00FrameLayouts = new FrameLayout[]{
                rootView.findViewById(R.id.number00FrameLayout),
                rootView.findViewById(R.id.number01FrameLayout),
                rootView.findViewById(R.id.number02FrameLayout),
                rootView.findViewById(R.id.number03FrameLayout),
                rootView.findViewById(R.id.number04FrameLayout),
                rootView.findViewById(R.id.number05FrameLayout),
                rootView.findViewById(R.id.number06FrameLayout),
                rootView.findViewById(R.id.number07FrameLayout),
                rootView.findViewById(R.id.number08FrameLayout),
                rootView.findViewById(R.id.number09FrameLayout)
        };

        number00ImageViews = new ImageView[]{
                rootView.findViewById(R.id.image00ImageView),
                rootView.findViewById(R.id.image01ImageView),
                rootView.findViewById(R.id.image02ImageView),
                rootView.findViewById(R.id.image03ImageView),
                rootView.findViewById(R.id.image04ImageView),
                rootView.findViewById(R.id.image05ImageView),
                rootView.findViewById(R.id.image06ImageView),
                rootView.findViewById(R.id.image07ImageView),
                rootView.findViewById(R.id.image08ImageView),
                rootView.findViewById(R.id.image09ImageView)
        };

        number00TextViews = new TextView[]{
                rootView.findViewById(R.id.number00TextView),
                rootView.findViewById(R.id.number01TextView),
                rootView.findViewById(R.id.number02TextView),
                rootView.findViewById(R.id.number03TextView),
                rootView.findViewById(R.id.number04TextView),
                rootView.findViewById(R.id.number05TextView),
                rootView.findViewById(R.id.number06TextView),
                rootView.findViewById(R.id.number07TextView),
                rootView.findViewById(R.id.number08TextView),
                rootView.findViewById(R.id.number09TextView)
        };

        for (FrameLayout frameLayout : numberFrameLayouts) {
            frameLayout.setOnClickListener(this);
        }

        for (FrameLayout frameLayout : number00FrameLayouts) {
            frameLayout.setOnClickListener(this);
        }
        sharedPreferences = requireActivity().getSharedPreferences("MoneyInput", 0);
        totalMoneyTextView = rootView.findViewById(R.id.totalMoneyTextView);
        totalMoney = sharedPreferences.getFloat("TotalMoney", 0);
        totalMoneyTextView.setText(String.format(Locale.getDefault(), "%.2f", totalMoney));

        if (savedInstanceState == null && shouldShowMoneyInputDialog()) {
            showMoneyInputDialog();
        }


        return rootView;
    }

    private boolean shouldShowMoneyInputDialog() {
        return !sharedPreferences.contains("TotalMoney");
    }

    private void showMoneyInputDialog() {
        MoneyInputDialogFragment moneyInputDialogFragment = MoneyInputDialogFragment.newInstance();
        moneyInputDialogFragment.setMoneyInputListener(this);
        moneyInputDialogFragment.show(getChildFragmentManager(), "money_input_dialog");
    }

    @Override
    public void onMoneyInputConfirmed(double money) {
        totalMoney += money;
        totalMoneyTextView.setText(String.format(Locale.getDefault(), "%.2f", totalMoney));
    }

    @Override
    public void onClick(View view) {
        boolean isSelected = view.isSelected();

        for (FrameLayout frameLayout : numberFrameLayouts) {
            frameLayout.setSelected(false);
        }

        for (FrameLayout frameLayout : number00FrameLayouts) {
            frameLayout.setSelected(false);
        }

        for (int i = 0; i < numberImageViews.length; i++) {
            updateImageViewBackground(numberImageViews[i], numberTextViews[i], false);
        }

        for (int i = 0; i < number00ImageViews.length; i++) {
            updateImageViewBackground(number00ImageViews[i], number00TextViews[i], false);
        }

        view.setSelected(!isSelected);

        for (int i = 0; i < numberFrameLayouts.length; i++) {
            if (view == numberFrameLayouts[i]) {
                updateImageViewBackground(numberImageViews[i], numberTextViews[i], !isSelected);
                break;
            }
        }

        for (int i = 0; i < number00FrameLayouts.length; i++) {
            if (view == number00FrameLayouts[i]) {
                updateImageViewBackground(number00ImageViews[i], number00TextViews[i], !isSelected);
                break;
            }
        }
    }

    private void updateImageViewBackground(ImageView imageView, TextView textView, boolean selected) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setStroke(4, ContextCompat.getColor(requireContext(), R.color.stroke_color));

        if (selected) {
            gradientDrawable.setColors(new int[]{ContextCompat.getColor(requireContext(), R.color.gradient_start_color),
                    ContextCompat.getColor(requireContext(), R.color.gradient_end_color)});
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        } else {
            gradientDrawable.setColor(ContextCompat.getColor(requireContext(), R.color.unselected_color));
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
        }

        imageView.setBackground(gradientDrawable);
    }
}

