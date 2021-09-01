package com.hfad.myappproductomparison;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "VAS9_TAG";

    EditText etPriceA;
    EditText etPriceB;
    EditText etNumberA;
    EditText etNumberB;
    TextView tvCalculateA;
    TextView tvCalculateB;
    TextView tvError;
    View vBackgroundA;
    View vBackgroundB;
    float valuePriceA;
    float valuePriceB;
    float valueNumberA;
    float valueNumberB;
    float resultA;
    float resultB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Start!");

        etPriceA = findViewById(R.id.editTextPriceA);
        etPriceB = findViewById(R.id.editTextPriceB);
        etNumberA = findViewById(R.id.etQuantityA);
        etNumberB = findViewById(R.id.etQuantityB);
        vBackgroundA = findViewById(R.id.vBackgroundA);
        vBackgroundB = findViewById(R.id.vBackgroundB);
        tvCalculateA = findViewById(R.id.tvCalculateA);
        tvCalculateB = findViewById(R.id.tvCalculateB);
        tvError = findViewById(R.id.tvError);

        etPriceA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    Log.d(TAG, "onTextChanged: PriceA");
                    valuePriceA = Float.parseFloat(etPriceA.getText().toString());
                    calculate();
                } catch (NullPointerException exception) {
                    Log.d(TAG, "onTextChanged: Null PriceA");
                    showError();
                } catch (NumberFormatException exception) {
                    Log.d(TAG, "onTextChanged: Не удалось распарсить PriceA");
                    showError();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        etPriceB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    Log.d(TAG, "onTextChanged: PriceB");
                    valuePriceB = Float.parseFloat(etPriceB.getText().toString());
                    calculate();
                } catch (NullPointerException exception) {
                    Log.d(TAG, "onTextChanged: Null PriceB");
                    showError();
                } catch (NumberFormatException exception) {
                    Log.d(TAG, "onTextChanged: Не удалось распарсить PriceB");
                    showError();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        etNumberA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    Log.d(TAG, "onTextChanged: NumberA");
                    valueNumberA = Float.parseFloat(etNumberA.getText().toString());
                    calculate();
                } catch (NullPointerException exception) {
                    Log.d(TAG, "onTextChanged: Null NumberA");
                    showError();
                } catch (NumberFormatException exception) {
                    Log.d(TAG, "onTextChanged: Не удалось распарсить NumberA");
                    showError();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        etNumberB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    Log.d(TAG, "onTextChanged: NumberB");
                    valueNumberB = Float.parseFloat(etNumberB.getText().toString());
                    calculate();
                } catch (NullPointerException exception) {
                    Log.d(TAG, "onTextChanged: Null NumberB");
                    showError();
                } catch (NumberFormatException exception) {
                    Log.d(TAG, "onTextChanged: Не удалось распарсить NumberB");
                    showError();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void calculate() {

        if (valueNumberA != 0 || valueNumberB != 0) {
            resultA = valuePriceA / valueNumberA;
            resultB = valuePriceB / valueNumberB;

            tvCalculateA.setText(String.valueOf(resultA));
            tvCalculateB.setText(String.valueOf(resultB));

            tvError.setText(null);
            tvError.setVisibility(View.GONE);
            showResult();
        } else {
            resultA = 0;
            resultB = 0;
            tvCalculateA.setText(String.valueOf(resultA));
            tvCalculateB.setText(String.valueOf(resultB));
            showError();
        }
    }

    private void showResult() {
        float result = resultA - resultB;
        if (result == 0) {
            vBackgroundA.setBackgroundResource(R.drawable.gradient_background_green_white);
            vBackgroundB.setBackgroundResource(R.drawable.gradient_background_white_green);
        } else if (result > 0) {
            vBackgroundA.setBackgroundResource(R.drawable.gradient_background_green_white);
            vBackgroundB.setBackgroundResource(R.drawable.gradient_background_white_red);
        } else if (result < 0) {
            vBackgroundA.setBackgroundResource(R.drawable.gradient_background_red_white);
            vBackgroundB.setBackgroundResource(R.drawable.gradient_background_white_green);
        }
    }

    private void showError() {
        vBackgroundA.setBackgroundResource(R.color.white);
        vBackgroundB.setBackgroundResource(R.color.white);
        tvError.setText("Введите корректные значения!");
        tvError.setVisibility(View.VISIBLE);
        Log.d(TAG, "showError: Введите корректные значения!");
    }
}