package com.ghiso.lode.fragment;

import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private Button allButton, oddButton, evenButton, eraseButton,
            all2Button, odd2Button, even2Button, erase2Button;

    public Option1Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_option1, container, false);

        numberFrameLayouts = new FrameLayout[10];
        numberImageViews = new ImageView[10];
        numberTextViews = new TextView[10];
        number00FrameLayouts = new FrameLayout[10];
        number00ImageViews = new ImageView[10];
        number00TextViews = new TextView[10];

        for (int i = 0; i < 10; i++) {
            int numberId = getResources().getIdentifier("number" + i + "FrameLayout", "id", requireActivity().getPackageName());
            int imageId = getResources().getIdentifier("image" + i + "ImageView", "id", requireActivity().getPackageName());
            int textId = getResources().getIdentifier("number" + i + "TextView", "id", requireActivity().getPackageName());

            numberFrameLayouts[i] = rootView.findViewById(numberId);
            numberImageViews[i] = rootView.findViewById(imageId);
            numberTextViews[i] = rootView.findViewById(textId);
        }

        //SECOND
        for (int i = 0; i < 10; i++) {
            int numberId = getResources().getIdentifier("number" + String.format("%02d", i) + "FrameLayout", "id", requireActivity().getPackageName());
            int imageId = getResources().getIdentifier("image" + String.format("%02d", i) + "ImageView", "id", requireActivity().getPackageName());
            int textId = getResources().getIdentifier("number" + String.format("%02d", i) + "TextView", "id", requireActivity().getPackageName());

            number00FrameLayouts[i] = rootView.findViewById(numberId);
            number00ImageViews[i] = rootView.findViewById(imageId);
            number00TextViews[i] = rootView.findViewById(textId);
        }

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

        // buttons
        allButton = rootView.findViewById(R.id.all);
        oddButton = rootView.findViewById(R.id.odd);
        evenButton = rootView.findViewById(R.id.even);
        eraseButton = rootView.findViewById(R.id.erase);
        all2Button = rootView.findViewById(R.id.all2);
        odd2Button = rootView.findViewById(R.id.odd2);
        even2Button = rootView.findViewById(R.id.even2);
        erase2Button = rootView.findViewById(R.id.erase2);

        allButton.setOnClickListener(this);
        oddButton.setOnClickListener(this);
        evenButton.setOnClickListener(this);
        eraseButton.setOnClickListener(this);
        all2Button.setOnClickListener(this);
        odd2Button.setOnClickListener(this);
        even2Button.setOnClickListener(this);
        erase2Button.setOnClickListener(this);

        showAllNumbers();

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

        if (view == allButton || view == all2Button) {
            showAllNumbers();
        } else if (view == oddButton || view == odd2Button) {
            showNumbers(true);
        } else if (view == evenButton || view == even2Button) {
            showNumbers(false);
        } else if (view == eraseButton || view == erase2Button) {
            // NOT DONE YET
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

    private void showAllNumbers() {
        for (FrameLayout frameLayout : numberFrameLayouts) {
            frameLayout.setVisibility(View.VISIBLE);
        }

        for (FrameLayout frameLayout : number00FrameLayouts) {
            frameLayout.setVisibility(View.VISIBLE);
        }
    }

    private void showNumbers(boolean showOdd) {
        for (int i = 0; i < numberFrameLayouts.length; i++) {
            FrameLayout frameLayout = numberFrameLayouts[i];
            frameLayout.findViewById(getNumberTextViewId(i));

            if (showOdd && i % 2 == 0) {
                frameLayout.setVisibility(View.GONE);
            } else if (!showOdd && i % 2 != 0) {
                frameLayout.setVisibility(View.GONE);
            } else {
                frameLayout.setVisibility(View.VISIBLE);
            }
        }

        for (int i = 0; i < number00FrameLayouts.length; i++) {
            FrameLayout frameLayout = number00FrameLayouts[i];
            frameLayout.findViewById(getNumberTextViewId(i + 10));

            if (showOdd && (i + 10) % 2 == 0) {
                frameLayout.setVisibility(View.GONE);
            } else if (!showOdd && (i + 10) % 2 != 0) {
                frameLayout.setVisibility(View.GONE);
            } else {
                frameLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    private int getNumberTextViewId(int number) {
        int textViewId = getResources().getIdentifier("number" + number + "TextView", "id", requireActivity().getPackageName());
        return textViewId != 0 ? textViewId : R.id.number0TextView;
    }
}
