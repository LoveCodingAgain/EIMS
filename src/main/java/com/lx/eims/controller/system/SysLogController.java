package com.lx.eims.controller.system;
import com.lx.eims.entity.system.SysLog;
import com.lx.eims.service.SysLogService;
import com.lx.eims.util.DateUtils;
import com.lx.eims.util.Message;
import com.lx.eims.util.PageUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-03-19
 * time: 23:43
 * description:系统日志控制器
 */
@Controller
@RequestMapping("/sys")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;
    /**
     * 系统日志页面
     * @return
     */
    @RequestMapping("/log.html")
    public String log(){
        return "admin/log.html";
    }

    /**
     * 日志全部列表
     */
    @RequestMapping("/log/list")
    @ResponseBody
    public Message list(@RequestParam Map<String, Object> params){
        PageUtils page=sysLogService.queryPage(params);
        return Message.ok().put("page", page);
    }

    /**
     * 日志条件查询
     * @param params
     * @return
     */
    @RequestMapping("/log/condition")
    @ResponseBody
    public Message condition(@RequestParam Map<String, Object> params){
        PageUtils conditionPage=sysLogService.queryByOperate((String)params.get("operation"),params);
        return Message.ok().put("page", conditionPage);
    }

    @RequestMapping("/log/date")
    @ResponseBody
    public Message dateLog(@RequestParam Map<String, Object> dateParams){
        PageUtils datePage=sysLogService.queryByDate((String)dateParams.get("startDate"), (String)dateParams.get("endDate"), dateParams);
        return Message.ok().put("page", datePage);
    }
    /**
     * 日志数据导出
     * @return
     */
    @RequestMapping("/log/export")
    @ResponseBody
    public void export(HttpServletResponse response){
        String fileName="日志数据报表";
        try {
            // 设置请求头
            response.setHeader("Content-type","application/vnd.ms-excel");
            // 解决导出文件名中文乱码
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+".xls");
            // 导出数据
            doExport(response.getOutputStream(), sysLogService.logList());
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
        sheet.setColumnWidth(3, 50*256);
        sheet.setColumnWidth(4, 60*256);
        sheet.setColumnWidth(5, 15*256);
        sheet.setColumnWidth(6, 20*256);
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
        cell.setCellValue("用户名");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellValue("用户操作");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        cell.setCellValue("调用方法");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(4);
        cell.setCellValue("传入参数");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(5);
        cell.setCellValue("时间处理(ms)");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(6);
        cell.setCellValue("员工IP");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(7);
        cell.setCellValue("访问时刻");
        cell.setCellStyle(cellStyle);

    }

    /**
     * 将输出流数据写入到Excel中
     * @param out
     * @param list
     */
    private void doExport(OutputStream out, List<SysLog> list) throws IOException {
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet("日志报表");
        createTitle(workbook, sheet);
        // 新增数据行,设置单元格
        int rowNum = 1;
        for(SysLog sysLog:list){
            // 创建单元格的行
            HSSFRow row = sheet.createRow(rowNum);
            // 存放数据和设置单元格样式
            row.createCell(0).setCellValue(sysLog.getId());
            row.createCell(1).setCellValue(sysLog.getUsername());
            row.createCell(2).setCellValue(sysLog.getOperation());
            row.createCell(3).setCellValue(sysLog.getMethod());
            row.createCell(4).setCellValue(sysLog.getParams());
            row.createCell(5).setCellValue(sysLog.getTime());
            row.createCell(6).setCellValue(sysLog.getIp());
            row.createCell(7).setCellValue(DateUtils.format(sysLog.getCreateDate(),DateUtils.DATE_TIME_PATTERN));
            rowNum++;
        }
            // 设置sheet的名称和单元格内容
            workbook.setSheetName(0,"日志报表");
            workbook.write(out);
            workbook.close();
    }
}
