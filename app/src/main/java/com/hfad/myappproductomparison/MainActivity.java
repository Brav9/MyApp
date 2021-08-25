package com.hfad.myappproductomparison;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {



    EditText etPriceA; // переименовать в етПрайсА
    EditText etPriceB;
    EditText etNumberA;
    EditText etNumberB;
    Button btnCalculate;
    View vBackgroundA;
    View vBackgroundB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPriceA = findViewById(R.id.editTextPriceA);
        etPriceB = findViewById(R.id.editTextPriceB);
        etNumberA = findViewById(R.id.etQuantityA);
        etNumberB = findViewById(R.id.etQuantityB);
        btnCalculate = findViewById(R.id.btnCalculate);
        vBackgroundA = findViewById(R.id.vBackgroundA);
        vBackgroundB = findViewById(R.id.vBackgroundB);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String textPriceA = etPriceA.getText().toString();
                    float valuePriceA = Float.parseFloat(textPriceA);

                    String textPriceB = etPriceB.getText().toString();
                    float valuePriceB = Float.parseFloat(textPriceB);

                    String textNumberA = etNumberA.getText().toString();
                    float valueNumberA = Float.parseFloat(textNumberA);

                    String textNumberB = etNumberB.getText().toString();
                    float valueNumberB = Float.parseFloat(textNumberB);

                    if (valueNumberA == 0 || valueNumberB == 0) {
                        throw new ArithmeticException();
                    }

                    float resultA = (valuePriceA / valueNumberA);
                    float resultB = (valuePriceB / valueNumberB);

                    //Сравнение товаров с последующим изменением заднего фона
                    if (resultA < resultB) {
                        vBackgroundA.setBackgroundResource(R.drawable.gradient_background_green_white);
                        vBackgroundB.setBackgroundResource(R.drawable.gradient_background_white_red);
                    } else if (resultA > resultB) {
                        vBackgroundA.setBackgroundResource(R.drawable.gradient_background_red_white);
                        vBackgroundB.setBackgroundResource(R.drawable.gradient_background_white_green);

                    } else if (resultA == resultB) {
                        vBackgroundA.setBackgroundResource(R.drawable.gradient_background_green_white);
                        vBackgroundB.setBackgroundResource(R.drawable.gradient_background_white_green);
                    } else {
                        vBackgroundA.setBackgroundResource(R.color.white);
                        vBackgroundB.setBackgroundResource(R.color.white);
                    }

                } catch (NumberFormatException exception) {
                    showError("Введите корректные значения!");
                } catch (ArithmeticException exception) {
                    showError("Количество не должно равняться нулю!");
                }
            }
        });
    }

    private void showError(String message) {
        vBackgroundA.setBackgroundResource(R.drawable.gradient_background_red_white);
        vBackgroundB.setBackgroundResource(R.drawable.gradient_background_white_red);
        Toast toast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}