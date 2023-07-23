package com.vnd.mco2restructure.menu;

import com.vnd.mco2restructure.model.items.Item;

public interface ItemEnum<T extends Item> {
    public T enumToItem();
}
