package com.hfad.myappproductomparison;

public interface IContract {

    interface IView {
        void showInfo();
    }

    interface IPresenter {
        void loadInfo();
    }

    interface IModel {
        String getInfo();
    }
}


