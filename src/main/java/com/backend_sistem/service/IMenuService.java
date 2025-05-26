package com.backend_sistem.service;


import com.backend_sistem.model.table.Menu;


import java.util.List;

public interface IMenuService extends ICRUDService<Menu, Integer> {

    List<Menu> getMenusByUsername(String username);
}
