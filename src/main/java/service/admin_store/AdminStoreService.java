package service.admin_store;

import java.util.List;

public interface AdminStoreService <DTORequest, DTOResponse>{
    public List<DTORequest> getAllStore();
}
