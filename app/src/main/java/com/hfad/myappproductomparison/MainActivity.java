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
    EditText etCalculateA;
    EditText etCalculateB;
    //Button btnCalculate;
    View vBackgroundA;
    View vBackgroundB;
    //float textNumberA;
    //float textNumberB;
    float valuePriceA;
    float valuePriceB;
    float resultA;
    float resultB;
    TextWatcher textWatcher;
    String textPriceA;
    String textNumberA;
    float valueNumberA;
    String textPriceB;
    String textNumberB;
    float valueNumberB;
    TextView tvCalculateA;
    TextView tvCalculateB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPriceA = findViewById(R.id.editTextPriceA);
        etPriceB = findViewById(R.id.editTextPriceB);
        etNumberA = findViewById(R.id.etQuantityA);
        etNumberB = findViewById(R.id.etQuantityB);
        //btnCalculate = findViewById(R.id.btnCalculate);
        vBackgroundA = findViewById(R.id.vBackgroundA);
        vBackgroundB = findViewById(R.id.vBackgroundB);
        tvCalculateA = findViewById(R.id.tvCalculateA);
        tvCalculateB = findViewById(R.id.tvCalculateB);

        etPriceA.addTextChangedListener(new CustomTextWatcher());
        etPriceB.addTextChangedListener(new CustomTextWatcher());
        etNumberA.addTextChangedListener(new CustomTextWatcher());
        etNumberB.addTextChangedListener(new CustomTextWatcher());

        //Добавить отображение результата расчета по каждому товару ниже.
        //Авторасчет - убрать кнопку "Расчитать". ВМесто этого повесить на поля ввода слушатели^
        //онТекстЧенд;еЛистенер. При изменении текста ну;но менять переменну.? в которотой [раниться значение]
    }

    public class CustomTextWatcher implements TextWatcher {

        private boolean mWasEdited = false;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {
                if (!etPriceA.getText().toString().equals("") && !etNumberA.getText().toString().equals("")) {
                    textPriceA = etPriceA.getText().toString();
                    valuePriceA = Float.parseFloat(textPriceA);
                    textNumberA = etNumberA.getText().toString();
                    valueNumberA = Float.parseFloat(textNumberA);
                    tvCalculateA.setText(String.valueOf(valuePriceA / valueNumberA));

                } else if (!etPriceB.getText().toString().equals("") && !etNumberB.getText().toString().equals("")) {
                    textPriceB = etPriceB.getText().toString();
                    valuePriceB = Float.parseFloat(textPriceB);
                    textNumberB = etNumberB.getText().toString();
                    valueNumberB = Float.parseFloat(textNumberB);
                    tvCalculateB.setText(String.valueOf(valuePriceB / valueNumberB));
                }
            } catch (NullPointerException exception) {
                showError("Введите корректные значения!");
            }

        }


        @Override
        public void afterTextChanged(Editable s) {
            try {
                resultA = (valuePriceA / valueNumberA);
                resultB = (valuePriceB / valueNumberB);
                if (mWasEdited) {
                    return;
                }
                if (valueNumberA == 0 || valueNumberB == 0) {
                    throw new ArithmeticException();
                } else if (resultA < resultB) {
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

            } catch (
                    NumberFormatException exception) {
                showError("Введите корректные значения!");
            } catch (
                    ArithmeticException exception) {
                showError("Количество не должно равняться нулю!");
            }

        }
        K
        private void showError(String message) {
            vBackgroundA.setBackgroundResource(R.drawable.gradient_background_red_white);
            vBackgroundB.setBackgroundResource(R.drawable.gradient_background_white_red);
            Toast toast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}