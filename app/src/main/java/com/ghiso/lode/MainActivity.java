package com.ghiso.lode;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ghiso.lode.fragment.Option1Fragment;
import com.ghiso.lode.fragment.Option2Fragment;
import com.ghiso.lode.fragment.Option3Fragment;

public class MainActivity extends AppCompatActivity {
    private TextView option1TextView;
    private TextView option2TextView;
    private TextView option3TextView;
    private Fragment option1Fragment;
    private Fragment option2Fragment;
    private Fragment option3Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView titleTextView = findViewById(R.id.titleTextView);

        option3TextView = findViewById(R.id.option3TextView);
        option1Fragment = new Option1Fragment();
        option2TextView = findViewById(R.id.option2TextView);
        option2Fragment = new Option2Fragment();
        option1TextView = findViewById(R.id.option1TextView);
        option3Fragment = new Option3Fragment();


        titleTextView.setText(R.string.nh);

        option1TextView.setOnClickListener(v -> selectOption(option1TextView, option1Fragment));
        option2TextView.setOnClickListener(v -> selectOption(option2TextView, option2Fragment));
        option3TextView.setOnClickListener(v -> selectOption(option3TextView, option3Fragment));
        selectOption(option1TextView, option1Fragment);
    }

    private void selectOption(TextView selectedOption, Fragment selectedFragment) {
        option1TextView.setBackgroundResource(R.drawable.option_selector);
        option2TextView.setBackgroundResource(R.drawable.option_selector);
        option3TextView.setBackgroundResource(R.drawable.option_selector);

        option1TextView.setTextColor(ContextCompat.getColor(this, R.color.option_text_color));
        option2TextView.setTextColor(ContextCompat.getColor(this, R.color.option_text_color));
        option3TextView.setTextColor(ContextCompat.getColor(this, R.color.option_text_color));

        selectedOption.setBackgroundResource(R.drawable.option_selected_background);
        selectedOption.setTextColor(Color.WHITE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, selectedFragment);
        fragmentTransaction.commit();

    }
}


