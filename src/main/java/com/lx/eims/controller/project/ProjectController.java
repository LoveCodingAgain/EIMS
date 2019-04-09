package com.lx.eims.controller.project;
import com.lx.eims.entity.data.Data;
import com.lx.eims.entity.project.Project;
import com.lx.eims.entity.project.ProjectCount;
import com.lx.eims.exception.GlobalException;
import com.lx.eims.service.DataService;
import com.lx.eims.service.ProjectService;
import com.lx.eims.util.DateUtils;
import com.lx.eims.util.Message;
import com.lx.eims.util.PageUtils;
import com.lx.eims.util.ShiroUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-04-01
 * time: 18:41
 * description:项目控制器
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private DataService dataService;
    /**
     * 查询页面路由
      * @return
     */
    @RequestMapping("/search.html")
    public String search(){
        return "project/search.html";
    }

    /**
     * 新增页面路由
     * @return
     */
    @RequestMapping("/add.html")
    public String add(){
        return "project/add.html";
    }

    /**
     * 更新页面路由
     * @return
     */
    @RequestMapping("/update.html")
    public String update(){
        return "project/update.html";
    }

    /**
     * 项目核算路由
     * @return
     */
    @RequestMapping("/check.html")
    public String check(){
        return "project/check.html";
    }
    /**
     * 项目负责人查询
     * @param params
     * @return
     */
    @RequestMapping(value="/search/principal")
    @ResponseBody
    public Message principal(@Valid @RequestParam Map<String, Object> params){
        PageUtils principalPage=projectService.getPrincipal((String)params.get("principal"), params);
        return Message.ok().put("page", principalPage);
    }

    /**
     * 全部项目
     * @param params
     * @return
     */
    @RequestMapping(value = "/search/all")
    @ResponseBody
    public Message all(@RequestParam Map<String, Object> params){
        PageUtils projectPage=projectService.getProjects(params);
        return Message.ok().put("page", projectPage);
    }

    /**
     * 已完成项目查询
     * @param params
     * @return
     */
    @RequestMapping(value="/search/finished")
    @ResponseBody
    public Message finished(@RequestParam Map<String, Object> params){
        PageUtils projectPage=projectService.getFinished(params);
        return Message.ok().put("page", projectPage);
    }

    /**
     * 未完成项目查询
     * @param params
     * @return
     */
    @RequestMapping(value="/search/unfinished")
    @ResponseBody
    public Message unFinished(@RequestParam Map<String, Object> params){
        PageUtils projectPage=projectService.getUnfinished(params);
        return Message.ok().put("page", projectPage);
    }

    /**
     * 项目类型统计
     * @return
     */
    @RequestMapping(value="/charts/type",produces = "application/json")
    @ResponseBody
    public List<ProjectCount> projectCount(){
        List<ProjectCount> typeList=projectService.getProjectType();
        return typeList;
    }

    /**
     * 新增项目
     * @param project
     * @return
     */
    @RequestMapping(value="/save/project")
    @ResponseBody
    public Message saveProject(@RequestBody Project project){
        boolean result=projectService.save(project);
        if(!result){
            throw new DuplicateKeyException("新增项目失败,已有重复项目及负责人!");
        }
        return Message.ok();
    }

    /**
     * 修改项目
     * @param project
     * @return
     */
    @RequestMapping(value="/update/project", method=RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Message updateProject(@RequestBody Project project){
        int result=projectService.updateProject(project);
        if(result<1){
            throw new GlobalException("抱歉,修改项目失败,请重试!");
        }
        return Message.ok();
    }

    /**
     * 项目数据导出
     * @param response
     */
    @RequestMapping(value="/data/export")
    public void export(HttpServletResponse response){
        String fileName="项目数据报表";
        try {
            // 设置请求头
            response.setHeader("Content-type","application/vnd.ms-excel");
            // 解决导出文件名中文乱码
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+".xls");
            // 记录数据
            Data data=new Data();
            data.setOperator(ShiroUtils.getStaff().getUsername());
            data.setFormsName(fileName+".xls");
            data.setRemark("项目数据报表导出备注");
            data.setOperatorTime(new Date());
            dataService.save(data);
            // 导出数据
            doExport(response.getOutputStream(),projectService.projectList());
        } catch (Exception e) {
            e.getMessage();
        }
    }
    /**
     * 设置表头
     * @param workbook
     * @param sheet
     */
    private void createTitle(HSSFWorkbook workbook, HSSFSheet sheet){
        // 新建一行
        HSSFRow row = sheet.createRow(0);
        // 设置列宽
        row.setHeightInPoints(15);
        sheet.setColumnWidth(1, 30*256);
        sheet.setColumnWidth(2, 20*256);
        sheet.setColumnWidth(3, 40*256);
        sheet.setColumnWidth(4, 20*256);
        sheet.setColumnWidth(5, 10*256);
        sheet.setColumnWidth(6, 30*256);
        sheet.setColumnWidth(7, 30*256);
        // 设置单元格样式
        HSSFCellStyle cellStyle=workbook.createCellStyle();
        HSSFFont font=workbook.createFont();
        font.setBold(true);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        cellStyle.setFont(font);
        // 设置表头
        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("序号");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        cell.setCellValue("项目名称");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellValue("项目负责人");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        cell.setCellValue("项目描述");
        cell.setCellStyle(cellStyle);


        cell = row.createCell(4);
        cell.setCellValue("项目类型");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(5);
        cell.setCellValue("项目状态");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(6);
        cell.setCellValue("开始时间");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(7);
        cell.setCellValue("结束时间");
        cell.setCellStyle(cellStyle);
    }

    private void doExport(OutputStream out, List<Project> projectList) throws IOException {
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet("项目报表");
        createTitle(workbook, sheet);
        // 新增数据行,设置单元格
        int rowNum = 1;
        for(Project project:projectList){
            // 创建单元格的行
            HSSFRow row = sheet.createRow(rowNum);
            // 存放数据和设置单元格样式
            row.createCell(0).setCellValue(project.getProjectId());
            row.createCell(1).setCellValue(project.getProName());
            row.createCell(2).setCellValue(project.getPrincipal());
            row.createCell(3).setCellValue(project.getProDesci());
            row.createCell(4).setCellValue(project.getProType());
            row.createCell(5).setCellValue(project.getProStatus());
            row.createCell(6).setCellValue(DateUtils.format(project.getStartTime(),DateUtils.DATE_TIME_PATTERN));
            row.createCell(7).setCellValue(DateUtils.format(project.getEndTime(),DateUtils.DATE_TIME_PATTERN));
            rowNum++;
        }
        // 设置sheet的名称和单元格内容
        workbook.setSheetName(0,"项目报表");
        workbook.write(out);
        workbook.close();
    }
}
