package service.admin_product;
import java.util.List;


public interface AdminProductService <DTORequest,DTOResponse>{
    public List<DTORequest> getAllProduct();
}
