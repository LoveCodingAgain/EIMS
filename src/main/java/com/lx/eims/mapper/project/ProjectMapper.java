package com.lx.eims.mapper.project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.eims.entity.project.Project;
import com.lx.eims.entity.project.ProjectCount;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @author: lixing
 * date: 2019-04-01
 * time: 20:10
 * description:项目Mapper
 */
@Repository
public interface ProjectMapper extends BaseMapper<Project> {
    /**
     *项目类目查询
     */
    List<ProjectCount> projectGroup();
}
