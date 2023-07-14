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
        //FIRST
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
        boolean isNumberFrameLayout = false;
        boolean isNumber00FrameLayout = false;

        for (FrameLayout numberFrameLayout : numberFrameLayouts) {
            if (view == numberFrameLayout) {
                isNumberFrameLayout = true;
                break;
            }
        }
        for (FrameLayout number00FrameLayout : number00FrameLayouts) {
            if (view == number00FrameLayout) {
                isNumber00FrameLayout = true;
                break;
            }
        }
        if (isNumberFrameLayout) {
            for (int i = 0; i < numberFrameLayouts.length; i++) {
                FrameLayout frameLayout = numberFrameLayouts[i];
                frameLayout.setSelected(frameLayout == view && !isSelected);
                updateImageViewBackground(numberImageViews[i], numberTextViews[i], frameLayout.isSelected());
            }
        }
        if (isNumber00FrameLayout) {
            for (int i = 0; i < number00FrameLayouts.length; i++) {
                FrameLayout frameLayout = number00FrameLayouts[i];
                frameLayout.setSelected(frameLayout == view && !isSelected);
                updateImageViewBackground(number00ImageViews[i], number00TextViews[i], frameLayout.isSelected());
            }
        }

        if (isGroup1Button(view)) {
            handleGroup1ButtonClick(view);
        } else if (isGroup2Button(view)) {
            handleGroup2ButtonClick(view);
        } else if (view == eraseButton) {
            clearSelection(numberFrameLayouts, numberImageViews, numberTextViews);
            clearSelection(number00FrameLayouts, number00ImageViews, number00TextViews);
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

    private void handleGroup1ButtonClick(View view) {
        boolean isSelected = view.isSelected();
        clearSelection(numberFrameLayouts, numberImageViews, numberTextViews);

        if (view == allButton) {
            showAllNumbers(numberFrameLayouts);
        } else if (view == oddButton) {
            showOddNumbers(numberFrameLayouts);
        } else if (view == evenButton) {
            showEvenNumbers(numberFrameLayouts);
        } else if (view == eraseButton) {
            clearSelection(numberFrameLayouts, numberImageViews, numberTextViews);
        }

        view.setSelected(!isSelected);
    }

    private void handleGroup2ButtonClick(View view) {
        boolean isSelected = view.isSelected();
        clearSelection(number00FrameLayouts, number00ImageViews, number00TextViews);

        if (view == all2Button) {
            showAllNumbers(number00FrameLayouts);
        } else if (view == odd2Button) {
            showOddNumbers(number00FrameLayouts);
        } else if (view == even2Button) {
            showEvenNumbers(number00FrameLayouts);
        } else if (view == erase2Button) {
            clearSelection(number00FrameLayouts, number00ImageViews, number00TextViews);
        }

        view.setSelected(!isSelected);
    }

    private void showAllNumbers(FrameLayout[] frameLayouts) {
        for (FrameLayout frameLayout : frameLayouts) {
            frameLayout.setVisibility(View.VISIBLE);
        }
    }

    private void showOddNumbers(FrameLayout[] frameLayouts) {
        for (int i = 0; i < frameLayouts.length; i++) {
            FrameLayout frameLayout = frameLayouts[i];
            if (i % 2 == 0) {
                frameLayout.setVisibility(View.GONE);
            } else {
                frameLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    private void showEvenNumbers(FrameLayout[] frameLayouts) {
        for (int i = 0; i < frameLayouts.length; i++) {
            FrameLayout frameLayout = frameLayouts[i];
            if (i % 2 != 0) {
                frameLayout.setVisibility(View.GONE);
            } else {
                frameLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    private void clearSelection(FrameLayout[] frameLayouts, ImageView[] imageViews, TextView[] textViews) {
        for (int i = 0; i < frameLayouts.length; i++) {
            frameLayouts[i].setSelected(false);
            updateImageViewBackground(imageViews[i], textViews[i], false);
        }
    }

    private boolean isGroup1Button(View view) {
        return view == allButton || view == oddButton || view == evenButton;
    }

    private boolean isGroup2Button(View view) {
        return view == all2Button || view == odd2Button || view == even2Button;
    }

}
