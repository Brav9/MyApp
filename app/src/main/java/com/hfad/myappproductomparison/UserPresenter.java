package com.hfad.myappproductomparison;

import android.view.View;

public class UserPresenter {
    private User user;
    private View view;

    public UserPresenter(View view) {
        this.user = new User();
        this.view = view;
    }

    public  void updateEtPriceA(String etPriceA) {
    }
}
