package cn.seppel.guiliproducttools.service.excelverifyhandler;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import cn.seppel.guiliproducttools.dto.ProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @description:
 * @author: Huangsp
 * @create: 2019-09-21
 **/
@Service
public class ProductExcelVerifyHandler implements IExcelVerifyHandler<ProductDTO> {


    @Override
    public ExcelVerifyHandlerResult verifyHandler(ProductDTO productDTO) {
        return null;
    }

    public boolean validSpec(ProductDTO productDTO) {
        String spec = productDTO.getSpec();
        if (!StringUtils.hasText(spec)) {
            return false;
        }
        String[] a = checkSpec(spec);
        if (a == null || a.length < 2 || a.length > 3) {
            return false;
        }
        return true;
    }

    public String[] checkSpec(String spec) {
        String s = spec.replaceAll("[^0-9|.]", " ").trim();
        if (StringUtils.hasText(s)) {
            return s.split("\\s+");
        }
        return null;
    }
}
