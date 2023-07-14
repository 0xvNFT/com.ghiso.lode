package com.ghiso.lode.fragment;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.ghiso.lode.R;

import java.util.Locale;

public class Option1Fragment extends Fragment implements View.OnClickListener {
    private FrameLayout[] numberFrameLayouts;
    private ImageView[] numberImageViews;
    private TextView[] numberTextViews;
    private FrameLayout[] number00FrameLayouts;
    private ImageView[] number00ImageViews;
    private TextView[] number00TextViews;

    private TextView totalMoneyTextView;
    private double totalMoney = 10000;
    private SharedPreferences sharedPreferences;
    private Button allButton, oddButton, evenButton, eraseButton,
            all2Button, odd2Button, even2Button, erase2Button;
    ImageView x1,x3,x5,x10;
    int x_a,x_b,a;
    int monet, a_monkey;
    Button cuoc,resset;


    public Option1Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_option1, container, false);
        x1=rootView.findViewById(R.id.x1);
        x3=rootView.findViewById(R.id.x3);
        x5=rootView.findViewById(R.id.x5);
        x10=rootView.findViewById(R.id.x10);

        cuoc=rootView.findViewById(R.id.placeBet);
        resset=rootView.findViewById(R.id.resetButton);
        totalMoneyTextView = rootView.findViewById(R.id.totalMoneyTextView);

       monet=2500;
        a_monkey=monet;
        totalMoneyTextView.setText("0");
        x1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a_monkey=monet;
                x3.setVisibility(View.GONE);
                x5.setVisibility(View.GONE);
                x10.setVisibility(View.GONE);
                totalMoneyTextView.setText(""+a_monkey);
            }
        });
        x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a_monkey=monet*3;
                x1.setVisibility(View.GONE);
                x5.setVisibility(View.GONE);
                x10.setVisibility(View.GONE);
                totalMoneyTextView.setText(""+a_monkey);
            }
        });
        x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a_monkey=monet*5;
                x1.setVisibility(View.GONE);
                x3.setVisibility(View.GONE);
                x10.setVisibility(View.GONE);
                totalMoneyTextView.setText(""+a_monkey);
            }
        });
        x10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a_monkey=monet*10;
                x1.setVisibility(View.GONE);
                x3.setVisibility(View.GONE);
                x5.setVisibility(View.GONE);
                totalMoneyTextView.setText(""+a_monkey);
            }
        });

        cuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        resset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x1.setVisibility(View.VISIBLE);
                x3.setVisibility(View.VISIBLE);
                x5.setVisibility(View.VISIBLE);
                x10.setVisibility(View.VISIBLE);
                totalMoneyTextView.setText(""+0);

            }
        });


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

        for (FrameLayout frameLayout1 : number00FrameLayouts) {
            frameLayout1.setOnClickListener(this);
        }

//        sharedPreferences = requireActivity().getSharedPreferences("MoneyInput", 0);
//        totalMoneyTextView = rootView.findViewById(R.id.totalMoneyTextView);
//        totalMoney = sharedPreferences.getFloat("TotalMoney", 0);
//        totalMoneyTextView.setText(String.format(Locale.getDefault(), "%.2f", totalMoney));



        // buttons
        allButton = rootView.findViewById(R.id.all);
        oddButton = rootView.findViewById(R.id.odd);
        evenButton = rootView.findViewById(R.id.even);
        eraseButton = rootView.findViewById(R.id.erase);
        all2Button = rootView.findViewById(R.id.all2);
        odd2Button = rootView.findViewById(R.id.odd2);
        even2Button = rootView.findViewById(R.id.even2);
        erase2Button = rootView.findViewById(R.id.erase2);

        // buttons old

        allButton.setOnClickListener(this);
        oddButton.setOnClickListener(this);
        evenButton.setOnClickListener(this);
        eraseButton.setOnClickListener(this);

        // buttons units
        all2Button.setOnClickListener(this);
        odd2Button.setOnClickListener(this);
        even2Button.setOnClickListener(this);
        erase2Button.setOnClickListener(this);

        showAllNumbers();
        for (FrameLayout frameLayout : number00FrameLayouts) {
            frameLayout.setVisibility(View.VISIBLE);
        }

        return rootView;
    }

//    private boolean shouldShowMoneyInputDialog() {
//        return !sharedPreferences.contains("TotalMoney");
//    }


    @SuppressLint("ResourceType")
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
                int finalI = i;
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



        if (view == allButton ) {
            showAllNumbers();
        } else if (view == oddButton) {
            showNumbers(true);
        } else if (view == evenButton) {
            showNumbers(false);
        } else if (view == eraseButton) {
        }
        if (view == all2Button) {
            for (FrameLayout frameLayout : number00FrameLayouts) {
                frameLayout.setVisibility(View.VISIBLE);
            }
        } else if (view == odd2Button) {
            shownumbers02(true);
        } else if (view == even2Button) {
            shownumbers02(false);
        } else if ( view == erase2Button) {

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


    }
    private  void shownumbers02(boolean showUnit){
        for (int i = 0; i < number00FrameLayouts.length; i++) {
            FrameLayout frameLayout = number00FrameLayouts[i];
            frameLayout.findViewById(getNumberTextViewId(i + 10));

            if (showUnit && (i + 10) % 2 == 0) {
                frameLayout.setVisibility(View.GONE);
            } else if (!showUnit && (i + 10) % 2 != 0) {
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
