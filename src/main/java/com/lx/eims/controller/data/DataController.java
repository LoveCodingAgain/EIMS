package com.lx.eims.controller.data;
import com.lx.eims.entity.data.Data;
import com.lx.eims.entity.project.Project;
import com.lx.eims.service.DataService;
import com.lx.eims.util.DateUtils;
import com.lx.eims.util.Message;
import com.lx.eims.util.PageUtils;
import com.lx.eims.util.ShiroUtils;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-04-09
 * time: 0:09
 * description:数据管理明细
 */
@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;
    /**
     * 数据路由
     * @return
     */
    @RequestMapping("/details.html")
    public String details(){
        return "data/details.html";
    }

    /**
     * 查询全部数据
     * @return
     */
    @RequestMapping("/search")
    @ResponseBody
    public Message query(@RequestParam Map<String, Object> params){
        PageUtils page=dataService.getDatas(params);
        return Message.ok().put("page",page);
    }

    /**
     * 数据导出
     * @param response
     */
    @RequestMapping("/export")
    public void export(HttpServletResponse response) {
        String fileName = "导出数据报表";
        try {
            // 设置请求头
            response.setHeader("Content-type", "application/vnd.ms-excel");
            // 解决导出文件名中文乱码
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + ".xls");
            // 记录数据
            Data data = new Data();
            data.setOperator(ShiroUtils.getStaff().getUsername());
            data.setFormsName(fileName + ".xls");
            data.setRemark("导出数据报表备注");
            data.setOperatorTime(new Date());
            dataService.save(data);
            doExport(response.getOutputStream(),dataService.dataList());
        } catch (Exception e) {
            e.getMessage();
        }
    }
    private void createTitle(HSSFWorkbook workbook, HSSFSheet sheet){
        // 新建一行
        HSSFRow row = sheet.createRow(0);
        // 设置列宽
        row.setHeightInPoints(15);
        sheet.setColumnWidth(1, 10*256);
        sheet.setColumnWidth(2, 10*256);
        sheet.setColumnWidth(3, 10*256);
        sheet.setColumnWidth(4, 20*256);
        sheet.setColumnWidth(5, 20*256);
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
        cell.setCellValue("操作员");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellValue("导出报表名称");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        cell.setCellValue("导出备注");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(4);
        cell.setCellValue("导出时间");
        cell.setCellStyle(cellStyle);
    }

    /**
     * 导出
     * @param out
     * @param datatList
     * @throws IOException
     */
    private void doExport(OutputStream out, List<Data> datatList) throws IOException {
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet("导出数据报表");
        createTitle(workbook, sheet);
        // 新增数据行,设置单元格
        int rowNum = 1;
        for(Data data:datatList){
            // 创建单元格的行
            HSSFRow row = sheet.createRow(rowNum);
            // 存放数据和设置单元格样式
            row.createCell(0).setCellValue(data.getDataId());
            row.createCell(1).setCellValue(data.getOperator());
            row.createCell(2).setCellValue(data.getFormsName());
            row.createCell(3).setCellValue(data.getRemark());
            row.createCell(4).setCellValue(DateUtils.format(data.getOperatorTime(),DateUtils.DATE_TIME_PATTERN));
            rowNum++;
        }
        // 设置sheet的名称和单元格内容
        workbook.setSheetName(0,"导出数据报表");
        workbook.write(out);
        workbook.close();
    }
}
