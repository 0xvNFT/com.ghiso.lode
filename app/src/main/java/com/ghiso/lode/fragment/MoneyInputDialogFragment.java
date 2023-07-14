package com.ghiso.lode.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ghiso.lode.R;

import java.util.Objects;

public class MoneyInputDialogFragment extends DialogFragment {
    private EditText moneyEditText;
    private Button confirmButton;
    private MoneyInputListener moneyInputListener;
    private SharedPreferences sharedPreferences;

    public MoneyInputDialogFragment() {
    }

    public static MoneyInputDialogFragment newInstance() {
        return new MoneyInputDialogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);
        sharedPreferences = requireActivity().getSharedPreferences("MoneyInput", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_money_input_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moneyEditText = view.findViewById(R.id.moneyEditText);
        confirmButton = view.findViewById(R.id.confirmButton);

        confirmButton.setOnClickListener(v -> {
            String moneyString = moneyEditText.getText().toString().trim();
            if (!TextUtils.isEmpty(moneyString)) {
                double money = Double.parseDouble(moneyString);
                saveMoneyInput(money);
                moneyInputListener.onMoneyInputConfirmed(money);
                dismiss();
            }
        });
        moneyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateInput();
            }
        });
        double savedMoney = sharedPreferences.getFloat("MoneyValue", 0);
        moneyEditText.setText(String.valueOf(savedMoney));
        validateInput();
    }

    @Override
    public void onResume() {
        super.onResume();
        Dialog dialog = getDialog();
        if (dialog != null) {
            WindowManager.LayoutParams layoutParams = Objects.requireNonNull(dialog.getWindow()).getAttributes();
            layoutParams.width = getResources().getDimensionPixelSize(R.dimen.dialog_width);
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(layoutParams);
        }
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

    public void setMoneyInputListener(MoneyInputListener listener) {
        this.moneyInputListener = listener;
    }

    private void validateInput() {
        String moneyString = moneyEditText.getText().toString().trim();
        if (!TextUtils.isEmpty(moneyString)) {
            double money = Double.parseDouble(moneyString);
            if (money < 10 || money > 5000) {
                moneyEditText.setError("Nhập giá trị tiền từ 10 đến 5000");
                confirmButton.setEnabled(false);
            } else {
                moneyEditText.setError(null);
                confirmButton.setEnabled(true);
            }
        } else {
            confirmButton.setEnabled(false);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    private void saveMoneyInput(double money) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("TotalMoney", (float) money);
        editor.apply();
    }

    public interface MoneyInputListener {
        void onMoneyInputConfirmed(double money);
    }

}