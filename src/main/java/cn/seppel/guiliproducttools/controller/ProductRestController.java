package cn.seppel.guiliproducttools.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.seppel.guiliproducttools.converter.ProductExcelVO2ProductDTOConverter;
import cn.seppel.guiliproducttools.dto.ProductDTO;
import cn.seppel.guiliproducttools.service.ProductService;
import cn.seppel.guiliproducttools.utils.EasyPoiUtil;
import cn.seppel.guiliproducttools.utils.ResultVOUtil;
import cn.seppel.guiliproducttools.vo.ResultVO;
import cn.seppel.guiliproducttools.vo.excel.ProductExcelVO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.util.List;

/**
 * @description:
 * @author: Huangsp
 * @create: 2019-09-22
 **/
@RestController
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product/api/upload", method = RequestMethod.POST)
    public void upload(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception {
        ImportParams params = new ImportParams();
        List<ProductExcelVO> productExcelVOList = ExcelImportUtil.importExcel(
                file.getInputStream(),
                ProductExcelVO.class, params);
        List<ProductDTO> productDTOS = ProductExcelVO2ProductDTOConverter.convert(productExcelVOList);
        this.productService.estimate(productDTOS);
        EasyPoiUtil.exportExcel(productDTOS, ProductDTO.class, "product-upload.xls", response);
    }
}
