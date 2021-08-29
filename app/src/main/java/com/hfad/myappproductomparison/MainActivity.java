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

        etPriceA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    valuePriceA = Float.parseFloat(etPriceA.getText().toString());
                    Log.d(TAG, "onTextChanged: PriceA");
                } catch (NullPointerException exception) {
                    valuePriceA = 0;
                } catch (NumberFormatException exception) {
                    Log.d(TAG, "onTextChanged: null PriceA");
                    valuePriceA = 0;
                }

                calculate();
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

                    valuePriceB = Float.parseFloat(etPriceB.getText().toString());
                    Log.d(TAG, "onTextChanged: PriceB");
                } catch (NullPointerException exception) {
                    valuePriceB = 0;
                } catch (NumberFormatException exception) {
                    Log.d(TAG, "onTextChanged: null PriceB");
                    valuePriceB = 0;
                }
                calculate();
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
                    valueNumberA = Float.parseFloat(etNumberA.getText().toString());
                    Log.d(TAG, "onTextChanged: NumberA");
                } catch (NullPointerException exception) {
                    valueNumberA = 0;
                } catch (NumberFormatException exception) {
                    Log.d(TAG, "onTextChanged: null NumberA");
                    valueNumberA = 0;
                }
                calculate();
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
                    valueNumberB = Float.parseFloat(etNumberB.getText().toString());
                    Log.d(TAG, "onTextChanged: NumberB");
                } catch (NullPointerException exception) {
                    valueNumberB = 0;
                } catch (NumberFormatException exception) {
                    Log.d(TAG, "onTextChanged: null NumberB");
                    valueNumberB = 0;
                }
                calculate();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void calculate() {
        //  try {
        if (((valuePriceA > 0 && valueNumberA > 0) && (valuePriceB > 0 && valueNumberB > 0))
                && ((etPriceA != null && etPriceB != null) && (etNumberA != null && etNumberB != null))) {
            resultA = valuePriceA / valueNumberA;
            Log.d(TAG, "calculate: valuePriceA / valueNumberA");
            tvCalculateA.setText(String.valueOf(resultA));
            Log.d(TAG, "calculate: resultA");
            resultB = valuePriceB / valueNumberB;
            Log.d(TAG, "calculate: valuePriceB / valueNumberB");
            tvCalculateB.setText(String.valueOf(resultB));
            Log.d(TAG, "calculate: resultB");
        } else {
            resultA = 0;
            tvCalculateA.setText(String.valueOf(resultA));
            resultB = 0;
            tvCalculateB.setText(String.valueOf(resultB));
            Log.d(TAG, "calculate: tvCalculateA && tvCalculateB = 0");
        }
        background();

        //} catch (ArithmeticException exception) {
        //   Log.d(TAG, "calculate: ArithmeticException exception");
        //   showError("Введите корректные значения!");
        //}
    }

    public void background() {
        if ((resultA < resultB) && (valueNumberA > 0 && valueNumberB > 0)
                && (valuePriceA > 0 && valuePriceB > 0)) {
            vBackgroundA.setBackgroundResource(R.drawable.gradient_background_green_white);
            vBackgroundB.setBackgroundResource(R.drawable.gradient_background_white_red);
            Log.d(TAG, "background: gradient background resultA < resultB");
        } else if ((resultA > resultB) && (valueNumberA != 0 && valueNumberB != 0)
                && (valuePriceA != 0 && valuePriceB != 0)) {
            vBackgroundA.setBackgroundResource(R.drawable.gradient_background_red_white);
            vBackgroundB.setBackgroundResource(R.drawable.gradient_background_white_green);

            Log.d(TAG, "background: gradient background resultA > resultB");
        } else if ((resultA == resultB) && (valueNumberA != 0 && valueNumberB != 0)
                && (valuePriceA != 0 && valuePriceB != 0)) {
            vBackgroundA.setBackgroundResource(R.drawable.gradient_background_green_white);
            vBackgroundB.setBackgroundResource(R.drawable.gradient_background_white_green);

            Log.d(TAG, "background: gradient background resultA == resultB");
        } else {
            vBackgroundA.setBackgroundResource(R.color.white);
            vBackgroundB.setBackgroundResource(R.color.white);
            Log.d(TAG, "background - white");
        }
    }

    // private void showError(String message) {
    //   vBackgroundA.setBackgroundResource(R.drawable.gradient_background_red_white);
    //    vBackgroundB.setBackgroundResource(R.drawable.gradient_background_white_red);
    //   Toast toast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
    //   toast.show();
    // }
}