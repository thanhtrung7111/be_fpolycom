package service.product;

import dto.product.ProductApproveRequestDTO;
import dto.product.ProductApproveResponeDTO;
import service.common.CommonService;

import java.util.List;

public interface ProductService extends CommonService<ProductApproveRequestDTO, ProductApproveResponeDTO,Long> {


    List<ProductApproveResponeDTO> updatePendingProductsToActive();
}
