package service.product;

import dto.product.ProductApproveRequestDTO;
import dto.product.ProductApproveResponeDTO;
import dto.user_account.AdminUserAccountRequestDTO;
import dto.user_account.AdminUserAccountResponseDTO;
import service.common.CommonService;

import java.util.List;

public interface ProductService <DTORequest,DTOResponse> {
    public List<DTORequest> getAll();


    ProductApproveResponeDTO lockProduct(ProductApproveRequestDTO request);

    ProductApproveResponeDTO unlockProduct(ProductApproveRequestDTO request);
}
