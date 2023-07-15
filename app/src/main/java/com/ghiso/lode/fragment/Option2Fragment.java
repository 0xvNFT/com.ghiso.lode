package com.ghiso.lode.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ghiso.lode.R;

import java.util.Locale;
import java.util.Random;

public class Option2Fragment extends Fragment {

    TextView time;
    TextView random_number;
    ImageView x1, x3, x5, x10;
    Button cuoc, resset;
    EditText editText;
    String so,so_dum;
    int a_monkey,a_dum;
    int monet=2500;
    Random random=new Random();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_option2, container, false);
        time=rootView.findViewById(R.id.text_time);
        random_number=rootView.findViewById(R.id.random);
        x1=rootView.findViewById(R.id.x1);
        x3=rootView.findViewById(R.id.x3);
        x5=rootView.findViewById(R.id.x5);
        x10=rootView.findViewById(R.id.x10);
        cuoc=rootView.findViewById(R.id.placeBet);
        resset=rootView.findViewById(R.id.resetButton);
        editText=rootView.findViewById(R.id.editText);
        // set
        x1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a_monkey = monet;

                x3.setVisibility(View.GONE);
                x5.setVisibility(View.GONE);
                x10.setVisibility(View.GONE);
                Toast.makeText(requireContext(), "Bạn chọn đặt 2500 !", Toast.LENGTH_SHORT).show();
            }

        });
        x3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                a_monkey = monet * 3;

                x1.setVisibility(View.GONE);
                x5.setVisibility(View.GONE);
                x10.setVisibility(View.GONE);
                Toast.makeText(requireContext(), "Bạn chọn đặt 7500 !", Toast.LENGTH_SHORT).show();
            }

        });
        x5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                a_monkey = monet * 5;

                x1.setVisibility(View.GONE);
                x3.setVisibility(View.GONE);
                x10.setVisibility(View.GONE);
                Toast.makeText(requireContext(), "Bạn chọn đặt 12500 !", Toast.LENGTH_SHORT).show();}


        });
        x10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a_monkey = monet * 10;
                x1.setVisibility(View.GONE);
                x3.setVisibility(View.GONE);
                x5.setVisibility(View.GONE);
                Toast.makeText(requireContext(), "Bạn chọn đặt 25000 !", Toast.LENGTH_SHORT).show();
            }
        });


        resset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x1.setVisibility(View.VISIBLE);
                x3.setVisibility(View.VISIBLE);
                x5.setVisibility(View.VISIBLE);
                x10.setVisibility(View.VISIBLE);
                cuoc.setVisibility(View.VISIBLE);
                x1.setEnabled(true);
                x3.setEnabled(true);
                x5.setEnabled(true);
                x10.setEnabled(true);
                editText.setText("");
                editText.setHint("Nhập số cần chọn !");
            }
        });
        cuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                so=editText.getText().toString();
                if(so.equals("")){
                    Toast.makeText(requireContext(), "Bạn chưa lựa chọn số !", Toast.LENGTH_SHORT).show();
                } else {
                    cuoc.setVisibility(View.GONE);
                    resset.setVisibility(View.GONE);
                    x1.setEnabled(false);
                    x3.setEnabled(false);
                    x5.setEnabled(false);
                    x10.setEnabled(false);

                    CountDownTimer countDownTimer=new CountDownTimer(10000,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            time.setText(millisUntilFinished/1000+" s");
                        }

                        @Override
                        public void onFinish() {
                            redum();
                            if(so.equals(so_dum)){
                                time.setText("Chúc mừng bạn đã đoán số chính xác!");
                                random_number.setText("Kết Quả :"+so_dum);
                            }else {
                                time.setText("Bạn lựa chọn số chưa chính xác !");
                                random_number.setText("Kết Quả :"+so_dum);
                            }
                            resset.setVisibility(View.VISIBLE);
                        }
                    };
                    countDownTimer.start();
                }
                
            }
        });


    return rootView;
    }
    private  void redum(){
        a_dum=random.nextInt(99);
        if(a_dum>10){
            so_dum= String.valueOf(a_dum);
        }else if(a_dum>0){
            so_dum="0"+String.valueOf(a_dum);
        } else if (a_dum==0) {
            so_dum="00";
        }
    }

}
