package com.ghiso.lode.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ghiso.lode.R;

import java.util.Random;
public class Option1Fragment extends Fragment {




    ImageView x1, x3, x5, x10;
    int  a=0, a_dum,moli;
    int monet, a_monkey;
    private Button allButton, oddButton, evenButton, eraseButton,
            all2Button, odd2Button, even2Button, erase2Button;
    Button cuoc, resset;
    private static TextView[] text_Old = new TextView[10];
    private static TextView[] text_Unit = new TextView[10];
    TextView selectedOld = null;
    TextView selectedUnit = null;
    String selected_old,selected_Unit, sum, so_dum;
    Random random=new Random();
    TextView showtiime,showRandom;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_option1, container, false);

        x1 = rootView.findViewById(R.id.x1);
        x3 = rootView.findViewById(R.id.x3);
        x5 = rootView.findViewById(R.id.x5);
        x10 = rootView.findViewById(R.id.x10);



        // buttons
        allButton = rootView.findViewById(R.id.all);
        oddButton = rootView.findViewById(R.id.odd);
        evenButton = rootView.findViewById(R.id.even);
        eraseButton = rootView.findViewById(R.id.erase);

        all2Button = rootView.findViewById(R.id.all2);
        odd2Button = rootView.findViewById(R.id.odd2);
        even2Button = rootView.findViewById(R.id.even2);
        erase2Button = rootView.findViewById(R.id.erase2);
        showtiime=rootView.findViewById(R.id.a);
        showRandom=rootView.findViewById(R.id.b);

        for (int i = 0; i < 10; i++) {
            int textId = getResources().getIdentifier("number" + i + "TextView", "id", requireActivity().getPackageName());
            text_Old[i] = rootView.findViewById(textId);
        }
        for (int i = 0; i < 10; i++) {
            int id_Unit = getResources().getIdentifier("number" + String.format("%02d", i) + "TextView", "id", requireActivity().getPackageName());
            text_Unit[i] = rootView.findViewById(id_Unit);
        }
        //view click Old
        View.OnClickListener onclickOld = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView click_text = (TextView) v;
                // nut ddk chon , huy nut
                if (selectedOld == null) {
                    selectedOld = click_text;
                    selectedOld.setBackgroundResource(R.drawable.ellipse_selected);
                } else if (selectedOld == click_text) {
                    selectedOld.setBackgroundResource(R.drawable.ellipse_unselected);
                    selectedOld = null;

                } else {
                    selectedOld.setBackgroundResource(R.drawable.ellipse_unselected);
                    selectedOld = click_text;
                    selectedOld.setBackgroundResource(R.drawable.ellipse_selected);
                }
                // lay gia tri nut
                if (selectedOld != null) {
//                    selected_old = selectedOld.getText().toString();
                }else {
                    Toast.makeText(requireContext(), "Bạn chưa chọn hàng đơn chục", Toast.LENGTH_SHORT).show();

                }
            }
        };
        View.OnClickListener onclickUnit = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView click_text = (TextView) v;
                // nut ddk chon , huy nut
                if (selectedUnit == null) {
                    selectedUnit = click_text;
                    selectedUnit.setBackgroundResource(R.drawable.ellipse_selected);
                } else if (selectedUnit == click_text) {
                    selectedUnit.setBackgroundResource(R.drawable.ellipse_unselected);
                    selectedUnit = null;

                } else {
                    selectedUnit.setBackgroundResource(R.drawable.ellipse_unselected);
                    selectedUnit = click_text;
                    selectedUnit.setBackgroundResource(R.drawable.ellipse_selected);
                }
                // lay gia tri nut
                if (selectedUnit != null) {
                 selected_Unit = selectedUnit.getText().toString();
                }else {
                    Toast.makeText(requireContext(), "Bạn chưa chọn hàng đơn vị", Toast.LENGTH_SHORT).show();
                }


            }
        };


        for (TextView number_Old : text_Old) {
            number_Old.setOnClickListener(onclickOld);

        }
        for (TextView number_Unit : text_Unit) {
            number_Unit.setOnClickListener(onclickUnit);

        }
        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < text_Old.length; i++) {
                    text_Old[i].setVisibility(View.VISIBLE);

                }
            }
        });
        all2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < text_Unit.length; i++) {
                    text_Unit[i].setVisibility(View.VISIBLE);
                }
            }
        });
        oddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                le_Old(text_Old);
            }
        });
        odd2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                le_Unit(text_Unit);
            }
        });
        evenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chan_Old(text_Old);
            }
        });
        even2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chan_Unit(text_Unit);
            }
        });
        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<10;i++){
                    text_Old[i].setBackgroundResource(R.drawable.ellipse_unselected);
                }
            }
        });
        erase2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<10;i++){
                    selectedUnit = null;
                    text_Unit[i].setBackgroundResource(R.drawable.ellipse_unselected);
                }
            }
        });



        cuoc = rootView.findViewById(R.id.placeBet);
        resset = rootView.findViewById(R.id.resetButton);

        monet = 2500;
        a_monkey = monet;

        x1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a_monkey = monet;

                x3.setVisibility(View.GONE);
                x5.setVisibility(View.GONE);
                x10.setVisibility(View.GONE);}

        });
        x3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                a_monkey = monet * 3;

                x1.setVisibility(View.GONE);
                x5.setVisibility(View.GONE);
                x10.setVisibility(View.GONE);}

        });
        x5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                a_monkey = monet * 5;

                x1.setVisibility(View.GONE);
                x3.setVisibility(View.GONE);
                x10.setVisibility(View.GONE);}


        });
        x10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a_monkey = monet * 10;
                x1.setVisibility(View.GONE);
                x3.setVisibility(View.GONE);
                x5.setVisibility(View.GONE);
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
                showtiime.setVisibility(View.GONE);
                showRandom.setVisibility(View.GONE);
                x1.setEnabled(true);
                x3.setEnabled(true);
                x5.setEnabled(true);
                x10.setEnabled(true);

                for(int i=0; i<10;i++){
                    text_Unit[i].setBackgroundResource(R.drawable.ellipse_unselected);
                    text_Old[i].setBackgroundResource(R.drawable.ellipse_unselected);
                }
                for (TextView number_Old : text_Old) {
                    number_Old.setEnabled(true);

                }
                for (TextView number_Unit : text_Unit) {
                    number_Unit.setEnabled(true);

                }

            }
        });

        cuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedOld!=null && selectedUnit!=null){
                    redum();
                    showtiime.setVisibility(View.VISIBLE);
                    showRandom.setVisibility(View.VISIBLE);
                    cuoc.setVisibility(View.GONE);
                    resset.setVisibility(View.GONE);
                    cuoc.setVisibility(View.GONE);
                    resset.setVisibility(View.GONE);
                    x1.setEnabled(false);
                    x3.setEnabled(false);
                    x5.setEnabled(false);
                    x10.setEnabled(false);
                    for (TextView number_Old : text_Old) {
                        number_Old.setEnabled(false);

                    }
                    for (TextView number_Unit : text_Unit) {
                        number_Unit.setEnabled(false);

                    }
                    CountDownTimer countDownTimer=new CountDownTimer(20000,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            showtiime.setText("Times: "+ millisUntilFinished/1000+" s");
                        }

                        @Override
                        public void onFinish() {
                            sum=selected_old+selected_Unit;
                            Log.d("AAA =  ",sum);
                            showRandom.setText(""+so_dum);
                            if(so_dum.equals(sum)){
                                showtiime.setText("Chức mừng bạn giành chiến thắng");
                            }else {
                                showtiime.setText("Rất tiếc bạn thua rồi !");


                            }



                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    x1.setVisibility(View.VISIBLE);
                                    x3.setVisibility(View.VISIBLE);
                                    x5.setVisibility(View.VISIBLE);
                                    x10.setVisibility(View.VISIBLE);

                                    showRandom.setText("");
                                    showtiime.setText("");
                                    resset.setVisibility(View.VISIBLE);
                                }
                            },2500);
                        }
                    }.start();
                }else {
                    Toast.makeText(requireContext(), "Bạn chưa chọn số . Xiin vui lòng chọn lại !", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return rootView;
    }

    // xet hang chuc
    // xet ler
    private void le_Old(TextView[] text_Old) {
        for (int i = 0; i < text_Old.length; i++) {
            TextView textView = text_Old[i];
            if (i % 2 != 0) {
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.GONE);
            }
        }

    }

    // xet_chan
    private void Chan_Old(TextView[] text_Old) {
        for (int i = 0; i < text_Old.length; i++) {
            TextView textView = text_Old[i];
            if (i % 2 == 0) {
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.GONE);
            }
        }
    }
// set hang don vi
private void le_Unit(TextView[] text_Old) {
    for (int i = 0; i < text_Old.length; i++) {
        TextView textView = text_Old[i];
        if (i % 2 != 0) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

}

    // xet_chan
    private void Chan_Unit(TextView[] text_Old) {
        for (int i = 0; i < text_Old.length; i++) {
            TextView textView = text_Old[i];
            if (i % 2 == 0) {
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.GONE);
            }
        }
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




