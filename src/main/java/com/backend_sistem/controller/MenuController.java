package com.backend_sistem.controller;

import com.backend_sistem.dto.MenuDTO;
import com.backend_sistem.model.table.Menu;
import com.backend_sistem.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final IMenuService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @PostMapping("/user")
    public ResponseEntity<List<MenuDTO>> getMenusByUser(@RequestBody String username){
        List<Menu> menus = service.getMenusByUsername(username);
        List<MenuDTO> menuDTOS = menus.stream().map(e -> modelMapper.map(e, MenuDTO.class)).toList();
        return ResponseEntity.ok(menuDTOS);
    }

   // private MenuDTO convertToDto(Menu obj) {   return modelMapper.map(obj, MenuDTO.class); }

}
