package com.hfad.myappproductomparison;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class User {
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

    public User() {
    }

    private void calculate() {

        if ((valueNumberA != 0 && valueNumberB != 0) && (valuePriceA != 0 && valuePriceB != 0)) {
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
            if (valueNumberA == 0 || valueNumberB == 0) {
                showError();
            }
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
