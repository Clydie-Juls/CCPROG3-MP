package com.vnd.mco2restructure.callbacks;


import com.vnd.mco2restructure.model.Money;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This class act as a simple callback to pass the denomination
 */
public interface DenomCallback {
    /**
     * abstract method for the denomination to be processed and passed
     * @param denom - payment to pass
     */
    public void onCallBack(LinkedHashMap<Integer, ArrayList<Money>> denom);
}
