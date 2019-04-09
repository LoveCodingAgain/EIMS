package com.lx.eims.service.impl.project;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.eims.entity.project.Project;
import com.lx.eims.entity.project.ProjectCount;
import com.lx.eims.mapper.project.ProjectMapper;
import com.lx.eims.service.ProjectService;
import com.lx.eims.util.PageUtils;
import com.lx.eims.util.QueryUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-04-01
 * time: 20:16
 * description:项目业务层实现
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Override
    public PageUtils getPrincipal(String principal, Map<String, Object> params) {
        IPage<Project> projectPage=this.page(
                new QueryUtils<Project>().getPage(params),
                new QueryWrapper<Project>().eq("principal", principal)
        );
        return new PageUtils(projectPage);
    }

    @Override
    public PageUtils getProjects(Map<String, Object> params) {
        String key = (String)params.get("key");
        IPage<Project> projectIPage=this.page(
                new QueryUtils<Project>().getPage(params),
                new QueryWrapper<Project>().like(StringUtils.isNotBlank(key),"project_id", key)
        );
        return new PageUtils(projectIPage);
    }

    @Override
    public PageUtils getFinished(Map<String, Object> params) {
        IPage<Project> projectIPage=this.page(
                new QueryUtils<Project>().getPage(params),
                new QueryWrapper<Project>().eq("pro_status",1)
        );
        return new PageUtils(projectIPage);
    }

    @Override
    public PageUtils getUnfinished(Map<String, Object> params) {
        IPage<Project> projectIPage=this.page(
                new QueryUtils<Project>().getPage(params),
                new QueryWrapper<Project>().eq("pro_status",0)
        );
        return new PageUtils(projectIPage);
    }

    @Override
    public List<ProjectCount> getProjectType() {
        return this.baseMapper.projectGroup();
    }

    @Override
    public List<Project> projectList() {
        List<Project> projectList=this.baseMapper.selectList(null);
        return projectList;
    }

    @Override
    public int updateProject(Project project) {
        return this.baseMapper.update(project,
                new QueryWrapper<Project>().eq("project_id", project.getProjectId()));
    }


}
