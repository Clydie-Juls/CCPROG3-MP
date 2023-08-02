package com.vnd.mco2restructure.callbacks;


import com.vnd.mco2restructure.model.Money;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface DenomCallback {
    public void onCallBack(LinkedHashMap<Integer, ArrayList<Money>> denom);
}
