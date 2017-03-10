package com.example.hanawa.smarterhometest.view;

import com.example.hanawa.smarterhometest.enums.ErrorEnum;

/**
* Created by Hanawa on 2016/10/24
*/

public interface LightControlView{

    void showSuccessMsg();

    void showErrorMsg(ErrorEnum code);

}