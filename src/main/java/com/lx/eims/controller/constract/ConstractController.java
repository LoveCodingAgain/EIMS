package com.lx.eims.controller.constract;
import com.lx.eims.entity.contract.Constract;
import com.lx.eims.entity.contract.Customer;
import com.lx.eims.entity.data.Data;
import com.lx.eims.exception.GlobalException;
import com.lx.eims.service.ConstractService;
import com.lx.eims.service.CustomerService;
import com.lx.eims.service.DataService;
import com.lx.eims.util.DateUtils;
import com.lx.eims.util.Message;
import com.lx.eims.util.PageUtils;
import com.lx.eims.util.ShiroUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * @author: lixing
 * date: 2019-04-02
 * time: 21:47
 * description:合同控制器
 */
@Controller
@RequestMapping("/constract")
public class ConstractController {

    @Autowired
    private ConstractService constractService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DataService dataService;
    /**
     * 合同查询页面
     * @return
     */
    @RequestMapping("/search.html")
    public String search(){
        return "constract/search.html";
    }

    /**
     * 合同新增页面
     * @return
     */
    @RequestMapping("/add.html")
    public String add(){
        return "constract/add.html";
    }

    /**
     * 合同更新页面
     * @return
     */
    @RequestMapping("/update.html")
    public String update(){
        return "constract/update.html";
    }

    /**
     * 查询全部合同
     * @param params
     * @return
     */
    @RequestMapping("/search/list")
    @ResponseBody
    public Message query(@RequestParam Map<String, Object> params){
        PageUtils page=constractService.getConstracts(params);
        return Message.ok().put("page", page);
    }

    /**
     * 更新合同
     * @param constract
     * @return
     */
    @RequestMapping(value="/update/constract", method= RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Message updateConstract(@RequestBody Constract constract){
        int result=constractService.updateConstract(constract);
        if(result<1){
            throw new GlobalException("抱歉,修改资产失败,请重试!");
        }
        return Message.ok();
    }
    /**
     * 新增合同
     * @param constract
     * @return
     */
    @RequestMapping(value="/add/constract" ,method= RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Message addConstract(@RequestBody Constract constract){
        constractService.save(constract);
        return  Message.ok();
    }

    /**
     * 新增客户
     * @param customer
     * @return
     */
    @RequestMapping(value="/add/customer" ,method= RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Message addCustomer(@RequestBody Customer customer){
        customerService.save(customer);
        return Message.ok();
    }

    /**
     * 合同导出
     */
    @RequestMapping("/export")
    public void export(HttpServletResponse response){
        String fileName = "合同信息报表";
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
            data.setRemark("导出合同信息报表备注");
            data.setOperatorTime(new Date());
            dataService.save(data);
            doExport(response.getOutputStream(),constractService.dataList());
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
        sheet.setColumnWidth(2, 20*256);
        sheet.setColumnWidth(3, 20*256);
        sheet.setColumnWidth(4, 20*256);
        sheet.setColumnWidth(5, 20*256);
        sheet.setColumnWidth(6, 10*256);
        sheet.setColumnWidth(7, 10*256);
        sheet.setColumnWidth(8, 20*256);
        sheet.setColumnWidth(9, 20*256);
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
        cell.setCellValue("合同甲方");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellValue("合同乙方");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        cell.setCellValue("合同内容");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(4);
        cell.setCellValue("合同类型");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(5);
        cell.setCellValue("合同金额");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(6);
        cell.setCellValue("合同状态");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(7);
        cell.setCellValue("开始时间");

        cell.setCellStyle(cellStyle);
        cell = row.createCell(8);
        cell.setCellValue("结束时间");
        cell.setCellStyle(cellStyle);
    }

    /**
     * 导出
     * @param out
     * @param datatList
     * @throws IOException
     */
    private void doExport(OutputStream out, List<Constract> datatList) throws IOException {
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet("合同信息报表");
        createTitle(workbook, sheet);
        // 新增数据行,设置单元格
        int rowNum = 1;
        for(Constract data:datatList){
            // 创建单元格的行
            HSSFRow row = sheet.createRow(rowNum);
            // 存放数据和设置单元格样式
            row.createCell(0).setCellValue(data.getConstractId());
            row.createCell(1).setCellValue(data.getCustomerUnit());
            row.createCell(2).setCellValue(data.getConstractNameSecond());
            row.createCell(3).setCellValue(data.getConstractContent());
            row.createCell(4).setCellValue(data.getConstractType());
            row.createCell(5).setCellValue(data.getConstractAmount().toString());
            row.createCell(6).setCellValue(data.getConstractStatus());
            row.createCell(7).setCellValue(DateUtils.format(data.getConstractSignTime(),DateUtils.DATE_TIME_PATTERN));
            row.createCell(8).setCellValue(DateUtils.format(data.getConstractEndTime(),DateUtils.DATE_TIME_PATTERN));
            rowNum++;
        }
        // 设置sheet的名称和单元格内容
        workbook.setSheetName(0,"合同信息报表");
        workbook.write(out);
        workbook.close();
    }
}

