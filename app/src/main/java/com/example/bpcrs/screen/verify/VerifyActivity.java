package com.example.bpcrs.screen.verify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chaos.view.PinView;
import com.example.bpcrs.R;
import com.example.bpcrs.screen.home.HomeActivity;
import com.example.bpcrs.screen.login.LoginActivity;

public class VerifyActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvOTP;
    private TextView tvTimeOtp;
    private PinView pvOTP;
    private Button btVerify;
    private EditText edtPhoneNumber;
    private ProgressBar progressBar;

    private static final String OTP = "Enter the 4 digit OTP";
    private static final String btOTP = "Verify OTP";
    private static final String sendOTP = "Send OTP";

    private CountDownTimer mCountDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        initData();


        btVerify.setOnClickListener(this);
    }

    public void initData() {
        tvOTP = findViewById(R.id.tv_otp);
        tvTimeOtp = findViewById(R.id.tv_timeOTP);
        pvOTP = findViewById(R.id.pv_otp);
        btVerify = findViewById(R.id.bt_verify);
        edtPhoneNumber = findViewById(R.id.edt_phoneNumber);
        progressBar = findViewById(R.id.progressBar2);
    }

    @Override
    public void onClick(View view) {
        switch (btVerify.getText().toString()) {
            case btOTP:
                String otp = pvOTP.getText().toString();
                if (otp.equals("1234")) {
                    pvOTP.setLineColor(Color.GREEN);
                    tvOTP.setText("OTP Verified");
                    tvOTP.setTextColor(Color.GREEN);

                    mCountDownTimer.cancel();
                    moveToHome();
                } else {
                    pvOTP.setLineColor(Color.RED);
                    tvOTP.setText("Incorrect OTP");
                    tvOTP.setTextColor(Color.RED);
                    btVerify.setText("Retry");
                }
                break;
            case "Retry":
                pvOTP.setText("");
                pvOTP.setLineColor(Color.BLUE);
                tvOTP.setText(OTP);
                tvOTP.setTextColor(Color.BLACK);
                btVerify.setText(btOTP);
                break;
            case sendOTP:
                String phone = edtPhoneNumber.getText().toString();
                if (!TextUtils.isEmpty(phone)) {
                    edtPhoneNumber.setVisibility(View.GONE);
                    pvOTP.setVisibility(View.VISIBLE);
                    btVerify.setText(btOTP);

                    tvOTP.setText(OTP);
                    tvOTP.setTextColor(Color.BLACK);

                    timeOTP();
                } else {
                    tvOTP.setTextColor(Color.RED);
                    tvOTP.setText("Please enter your phone number");
                }
                break;
        }

    }

    public void moveToHome() {
        mCountDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long l) {
                btVerify.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(VerifyActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        };
        mCountDownTimer.start();
    }

    public void timeOTP() {
        tvTimeOtp.setVisibility(View.VISIBLE);
        mCountDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                int time = (int) (l / 1000);
                tvTimeOtp.setText(time + "");
            }

            @Override
            public void onFinish() {

            }
        };
        mCountDownTimer.start();

    }
}
