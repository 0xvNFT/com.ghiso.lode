package com.ghiso.lode;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ghiso.lode.fragment.MoneyInputDialogFragment;
import com.ghiso.lode.fragment.Option1Fragment;
import com.ghiso.lode.fragment.Option2Fragment;
import com.ghiso.lode.fragment.Option3Fragment;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MoneyInputDialogFragment.MoneyInputListener, View.OnClickListener {
    private TextView option1TextView;
    private TextView option2TextView;
    private TextView option3TextView;
    private Fragment option1Fragment;
    private Fragment option2Fragment;
    private Fragment option3Fragment;
    private TextView totalMoneyTextView;
    private Button placeBetButton;
    private double totalMoney = 0.0;
    private SharedPreferences sharedPreferences;
    private EditText moneyField;

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


        sharedPreferences = getSharedPreferences("MoneyInput", 0);
        totalMoneyTextView = findViewById(R.id.totalMoneyTextView);
        totalMoney = sharedPreferences.getFloat("TotalMoney", 0);
        totalMoneyTextView.setText(String.format(Locale.getDefault(), "%.2f", totalMoney));

        moneyField = findViewById(R.id.moneyField);

        if (savedInstanceState == null && shouldShowMoneyInputDialog()) {
            showMoneyInputDialog();
        }
        ImageView x1ImageView = findViewById(R.id.x1);
        ImageView x3ImageView = findViewById(R.id.x3);
        ImageView x5ImageView = findViewById(R.id.x5);
        ImageView x10ImageView = findViewById(R.id.x10);
        Button resetButton = findViewById(R.id.resetButton);

        x1ImageView.setOnClickListener(this);
        x3ImageView.setOnClickListener(this);
        x5ImageView.setOnClickListener(this);
        x10ImageView.setOnClickListener(this);
        resetButton.setOnClickListener(this);

        totalMoneyTextView = findViewById(R.id.totalMoneyTextView);
        placeBetButton = findViewById(R.id.placeBet);

        placeBetButton.setOnClickListener(view -> {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
            if (currentFragment instanceof Option1Fragment) {
                Option1Fragment option1Fragment = (Option1Fragment) currentFragment;
                if (option1Fragment.areNumbersSelected()) {
                    String betAmountText = moneyField.getText().toString();
                    if (betAmountText.isEmpty()) {
                        Toast.makeText(this, "Please enter a bet amount", Toast.LENGTH_SHORT).show();
                    } else {
                        double betAmount = Double.parseDouble(betAmountText);
                        if (betAmount > totalMoney) {
                            Toast.makeText(this, "Bet amount exceeds available funds", Toast.LENGTH_SHORT).show();
                        } else {
                            double updatedTotalMoney = totalMoney - betAmount;
                            totalMoney = updatedTotalMoney;
                            totalMoneyTextView.setText(String.format(Locale.getDefault(), "%.2f", updatedTotalMoney));
                            option1Fragment.generateWinningNumber();
                        }
                    }
                } else {
                    Toast.makeText(this, "Please select numbers before placing a bet", Toast.LENGTH_SHORT).show();
                }
            } else if (currentFragment instanceof Option2Fragment) {
                Option2Fragment option2Fragment = (Option2Fragment) currentFragment;
                option2Fragment.showWinningNumber();
                if (option2Fragment.checkNumberMatch()) {
                    String betAmountText = moneyField.getText().toString();
                    if (betAmountText.isEmpty()) {
                        Toast.makeText(this, "Please enter a bet amount", Toast.LENGTH_SHORT).show();
                    } else {
                        double betAmount = Double.parseDouble(betAmountText);
                        double updatedTotalMoney = totalMoney + betAmount;
                        totalMoney = updatedTotalMoney;
                        totalMoneyTextView.setText(String.format(Locale.getDefault(), "%.2f", updatedTotalMoney));
                    }
                } else {
                    String betAmountText = moneyField.getText().toString();
                    if (betAmountText.isEmpty()) {
                        Toast.makeText(this, "Please enter a bet amount", Toast.LENGTH_SHORT).show();
                    } else {
                        double betAmount = Double.parseDouble(betAmountText);
                        double updatedTotalMoney = totalMoney - betAmount;
                        totalMoney = updatedTotalMoney;
                        totalMoneyTextView.setText(String.format(Locale.getDefault(), "%.2f", updatedTotalMoney));
                    }
                }
            }
        });


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

    private boolean shouldShowMoneyInputDialog() {
        return !sharedPreferences.contains("TotalMoney");
    }

    private void showMoneyInputDialog() {
        MoneyInputDialogFragment moneyInputDialogFragment = MoneyInputDialogFragment.newInstance();
        moneyInputDialogFragment.setMoneyInputListener(this);
        moneyInputDialogFragment.show(getSupportFragmentManager(), "money_input_dialog");
    }

    @Override
    public void onMoneyInputConfirmed(double money) {
        totalMoney += money;
        totalMoneyTextView.setText(String.format(Locale.getDefault(), "%.2f", totalMoney));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("TotalMoney", (float) totalMoney);
        editor.apply();

        Bundle bundle = new Bundle();
        bundle.putDouble("totalMoney", totalMoney);

        Option2Fragment option2Fragment = new Option2Fragment();
        option2Fragment.setArguments(bundle);

        Option3Fragment option3Fragment = new Option3Fragment();
        option3Fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, option2Fragment)
                .addToBackStack(null)
                .commit();

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, option3Fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.x1) {
            multiplyNumber(1.0);
        } else if (v.getId() == R.id.x3) {
            multiplyNumber(3.0);
        } else if (v.getId() == R.id.x5) {
            multiplyNumber(5.0);
        } else if (v.getId() == R.id.x10) {
            multiplyNumber(10.0);
        } else if (v.getId() == R.id.resetButton) {
            moneyField.setText("");
        }
    }

    private void multiplyNumber(double multiplier) {
        String inputText = moneyField.getText().toString();
        if (!inputText.isEmpty()) {
            double moneyInput = Double.parseDouble(inputText);
            double result;
            if (multiplier == 1.0) {
                result = moneyInput + multiplier;
            } else {
                result = moneyInput * multiplier;
            }
            if (result > totalMoney) {
                result = totalMoney;
            } else if (result < 0) {
                result = 0;
            }
            moneyField.setText(String.format(Locale.getDefault(), "%.2f", result));
        } else {
            moneyField.setText(String.format(Locale.getDefault(), "%.2f", multiplier));
        }
    }

    private double getMoneyField() {
        String moneyText = moneyField.getText().toString();
        return moneyText.isEmpty() ? 0.0 : Double.parseDouble(moneyText);
    }
}