package com.lx.eims.entity.assets;
import lombok.Getter;
import lombok.Setter;
/**
 * author: lixing
 * date: 2019-04-01
 * time: 11:01
 * description:类目统计个数
 */
@Setter
@Getter
public class AssetsCount {
    /**
     * 大类目
     */
    private String largeCategory;
    /**
     * 大类目统计个数
     */
    private Long categoryCount;
}
