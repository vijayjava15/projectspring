package com.learn.websocket.menu;

import com.learn.websocket.exception.ResponseUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    MenuRepository menuRepository;

    @Override
    public Object saveMenu(Menu menu) {
        if (menu == null) {
            return ResponseUtility.BADREQUEST(null, "menu request is null");
        }

        if (StringUtils.isBlank(menu.getMenuName())) {
            return ResponseUtility.BADREQUEST(null, "menu name should not be blank");
        }

        if(menu.getId()==null) {
            Optional<Menu> optionalMenu =
                    menuRepository.findByMenuName(menu.getMenuName());

            if (optionalMenu.isPresent()) {
                return ResponseUtility.BADREQUEST(
                        null,
                        "menu with the name already exists"
                );
            }
        }

        Menu savedMenu = menuRepository.save(menu);
        String message = menu.getId()!=null? "menu updated successfully" : "menu saved successfully" ;
        return ResponseUtility.OK(savedMenu, message);
    }

    @Override
    public Object fetchMenus() {
      return ResponseUtility.OK(menuRepository.findAll(),"SUCESS");
    }
}
