package com.lx.eims.entity.project;
import lombok.Getter;
import lombok.Setter;
/**
 * @author: lixing
 * date: 2019-04-02
 * time: 11:54
 * description:项目类型统计
 */
@Setter
@Getter
public class ProjectCount {
    /**
     * 项目类型
     */
    private String  proType;
    /**
     * 项目统计
     */
    private Long proCount;

    @Override
    public String toString() {
        return "ProjectCount{" +
                "proType='" + proType + '\'' +
                ", proCount=" + proCount +
                '}';
    }
}
