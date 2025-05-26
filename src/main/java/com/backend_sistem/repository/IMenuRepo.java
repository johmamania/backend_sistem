package com.backend_sistem.repository;


import com.backend_sistem.model.table.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMenuRepo extends IGenericRepo<Menu, Integer> {

    @Query(value = """
                  select sis_menu.* from sis_menu_role
                         inner join sis_menu  on sis_menu.id_menu = sis_menu_role.id_menu
                        inner join sis_user_role  on sis_user_role.id_role = sis_menu_role.id_role
                        inner join sis_user  on sis_user.id_user = sis_user_role.id_user
                        where sis_user.username =:username ORDER BY sis_menu_role.id_menu
            """, nativeQuery = true)
    List<Menu> getMenusByUsername(@Param("username") String username);

}
