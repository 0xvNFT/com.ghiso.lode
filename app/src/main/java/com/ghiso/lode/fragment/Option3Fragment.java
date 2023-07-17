package com.ghiso.lode.fragment;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.ghiso.lode.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Option3Fragment extends Fragment {

    private RadioGroup radioGroup;
    private TextView[] numberTextViews;
    public Option3Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_option3, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        radioGroup = view.findViewById(R.id.radioGroup);

        numberTextViews = new TextView[]{
                view.findViewById(R.id.num01),
                view.findViewById(R.id.num02),
                view.findViewById(R.id.num03),
                view.findViewById(R.id.num04),
                view.findViewById(R.id.num05),
                view.findViewById(R.id.num06),
                view.findViewById(R.id.num07),
                view.findViewById(R.id.num08),
                view.findViewById(R.id.num09),
                view.findViewById(R.id.num10),
                view.findViewById(R.id.num11),
                view.findViewById(R.id.num12),
                view.findViewById(R.id.num13),
                view.findViewById(R.id.num14),
                view.findViewById(R.id.num15),
                view.findViewById(R.id.num16),
                view.findViewById(R.id.num17),
                view.findViewById(R.id.num18),
                view.findViewById(R.id.num19),
                view.findViewById(R.id.num20),
                view.findViewById(R.id.num21),
                view.findViewById(R.id.num22),
                view.findViewById(R.id.num23),
                view.findViewById(R.id.num24),
                view.findViewById(R.id.num25),
                view.findViewById(R.id.num26),
                view.findViewById(R.id.num27),
                view.findViewById(R.id.num28),
                view.findViewById(R.id.num29),
                view.findViewById(R.id.num30),
                view.findViewById(R.id.num31),
                view.findViewById(R.id.num32),
                view.findViewById(R.id.num33),
                view.findViewById(R.id.num34),
                view.findViewById(R.id.num35),
                view.findViewById(R.id.num36),
                view.findViewById(R.id.num37),
                view.findViewById(R.id.num38),
                view.findViewById(R.id.num39),
                view.findViewById(R.id.num40),
                view.findViewById(R.id.num41),
                view.findViewById(R.id.num42),
                view.findViewById(R.id.num43),
                view.findViewById(R.id.num44),
                view.findViewById(R.id.num45),
                view.findViewById(R.id.num46),
                view.findViewById(R.id.num47),
                view.findViewById(R.id.num48),
                view.findViewById(R.id.num49),
                view.findViewById(R.id.num50),
                view.findViewById(R.id.num51),
                view.findViewById(R.id.num52),
                view.findViewById(R.id.num53),
                view.findViewById(R.id.num54),
                view.findViewById(R.id.num55),
                view.findViewById(R.id.num56),
                view.findViewById(R.id.num57),
                view.findViewById(R.id.num58),
                view.findViewById(R.id.num59),
                view.findViewById(R.id.num60)
        };

        radioGroup.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            RadioButton radioButton = view.findViewById(checkedId);
            if (radioButton.isChecked()) {
                selectRandomNumbers();
            }
        });
    }

    private void selectRandomNumbers() {
        List<Integer> selectedNumbers = new ArrayList<>();
        Random random = new Random();

        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        boolean isEvenSelected = radioButtonId == R.id.evenRadioButton;
        boolean isOddSelected = radioButtonId == R.id.oddRadioButton;

        List<Integer> availableNumbers = new ArrayList<>();
        if (isEvenSelected) {
            availableNumbers = generateEvenNumbersList();
        } else if (isOddSelected) {
            availableNumbers = generateOddNumbersList();
        } else {
            availableNumbers = generateAllNumbersList();
        }

        while (selectedNumbers.size() < 5) {
            int randomNumberIndex = random.nextInt(availableNumbers.size());
            int randomNumber = availableNumbers.get(randomNumberIndex);
            selectedNumbers.add(randomNumber);
            availableNumbers.remove(randomNumberIndex);
        }

        for (int i = 0; i < numberTextViews.length; i++) {
            TextView textView = numberTextViews[i];
            int number = i + 1;

            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setStroke(4, ContextCompat.getColor(requireContext(), R.color.stroke_color));
            gradientDrawable.setShape(GradientDrawable.RECTANGLE);

            if (selectedNumbers.contains(number)) {
                gradientDrawable.setColors(new int[]{
                        ContextCompat.getColor(requireContext(), R.color.gradient_start_color),
                        ContextCompat.getColor(requireContext(), R.color.gradient_end_color)
                });
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            } else {
                gradientDrawable.setColor(ContextCompat.getColor(requireContext(), R.color.unselected_color));
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
            }

            textView.setText(String.format("%02d", number));
            textView.setBackground(gradientDrawable);
        }
    }

    private List<Integer> generateEvenNumbersList() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 2; i <= 60; i += 2) {
            numbers.add(i);
        }
        return numbers;
    }

    private List<Integer> generateOddNumbersList() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 59; i += 2) {
            numbers.add(i);
        }
        return numbers;
    }

    private List<Integer> generateAllNumbersList() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            numbers.add(i);
        }
        return numbers;
    }

}
