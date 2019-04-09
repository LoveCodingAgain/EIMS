package com.lx.eims.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.eims.entity.project.Project;
import com.lx.eims.entity.project.ProjectCount;
import com.lx.eims.util.PageUtils;
import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-04-01
 * time: 20:09
 * description:项目业务层
 */
public interface ProjectService extends IService<Project> {
    /**
     * 根据项目负责人查询
     * @param params
     * @return
     */
    PageUtils getPrincipal(String principal, Map<String, Object> params);

    /**
     * 查询所有项目
     * @param params
     * @return
     */
    PageUtils getProjects(Map<String, Object> params);

    /**
     * 查询已完成项目
     * @param params
     * @return
     */
    PageUtils getFinished(Map<String, Object> params);

    /**
     * 查询未完成项目
     * @param params
     * @return
     */
    PageUtils getUnfinished(Map<String, Object> params);

    /**
     * 项目类目查询
     * @return
     */
    List<ProjectCount> getProjectType();

    /**
     * 全部项目列表
     * @return
     */
    List<Project> projectList();

    /**
     * 更新项目信息
     * @param project
     * @return
     */
    int updateProject(Project project);
}
