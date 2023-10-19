package org.example.untils.Vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.entity.Resources;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceVO extends Resources {
    List<ResourceVO> children;
}
