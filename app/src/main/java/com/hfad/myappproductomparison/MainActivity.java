package com.hfad.myappproductomparison;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

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
    float resultA;
    float resultB;
    float valueNumberA;
    float valueNumberB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                } catch (NullPointerException exception) {
                    valuePriceA = 0;
                } catch (NumberFormatException | ArithmeticException ignored) {
                }

                Calculate();
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
                } catch (NullPointerException exception) {
                    valuePriceB = 0;
                } catch (NumberFormatException | ArithmeticException ignored) {
                }
                Calculate();
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
                } catch (NullPointerException exception) {
                    valueNumberA = 0;
                } catch (NumberFormatException | ArithmeticException ignored) {
                }
                Calculate();
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
                } catch (NullPointerException exception) {
                    valueNumberB = 0;
                } catch (NumberFormatException | ArithmeticException ignored) {
                }
                Calculate();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void Calculate() {
        resultA = valuePriceA / valueNumberA;
        resultB = valuePriceB / valueNumberB;
        tvCalculateA.setText(String.valueOf(resultA));
        tvCalculateB.setText(String.valueOf(resultB));
        try {
            if ((valueNumberA == 0 || valueNumberB == 0) && (valuePriceA != 0 || valuePriceB != 0)) {
                throw new ArithmeticException();
            } else if (resultA < resultB) {
                vBackgroundA.setBackgroundResource(R.drawable.gradient_background_green_white);
                vBackgroundB.setBackgroundResource(R.drawable.gradient_background_white_red);
            } else if (resultA > resultB) {
                vBackgroundA.setBackgroundResource(R.drawable.gradient_background_red_white);
                vBackgroundB.setBackgroundResource(R.drawable.gradient_background_white_green);

            } else if ((resultA == resultB) && (valueNumberA != 0 || valueNumberB != 0)) {
                vBackgroundA.setBackgroundResource(R.drawable.gradient_background_green_white);
                vBackgroundB.setBackgroundResource(R.drawable.gradient_background_white_green);
            } else {
                vBackgroundA.setBackgroundResource(R.color.white);
                vBackgroundB.setBackgroundResource(R.color.white);
            }

        } catch (NumberFormatException | ArithmeticException ignored) {
            showError("Введите корректные значения!");
        }
    }

    private void showError(String message) {
        vBackgroundA.setBackgroundResource(R.drawable.gradient_background_red_white);
        vBackgroundB.setBackgroundResource(R.drawable.gradient_background_white_red);
        Toast toast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}