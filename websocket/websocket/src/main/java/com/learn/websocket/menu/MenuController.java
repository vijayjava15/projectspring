package com.learn.websocket.menu;

import com.learn.websocket.exception.ResponseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "menu")
public class MenuController {


    @Autowired
    MenuService menuService;

    @PostMapping("/save")
    public Object saveMenu(@RequestBody Menu menu){
      return menuService.saveMenu(menu);
    }


    @GetMapping("/getMenus")
    public Object fetchMenus(){
        return menuService.fetchMenus();
    }
}
