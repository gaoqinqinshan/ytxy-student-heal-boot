package org.example.untils.DTO;

import lombok.Data;

import java.util.List;


@Data
public class BindInfo {

    private Integer resourceId;

    private List<Integer> roleList;

}
