package com.learn.websocket.menu;

import com.learn.websocket.exception.ResponseUtility;

public interface MenuService {
    Object saveMenu(Menu menu);

    Object fetchMenus();
}
