package com.hty.springboot_shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Role {
    private Integer id;
    private String name;
    
    //权限集合
    private List<Pers> pers;
}
