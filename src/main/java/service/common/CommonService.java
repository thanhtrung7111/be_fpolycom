package service.common;

import java.util.List;

public interface CommonService<DTORequest,DTOResponse,ID> {

    public DTOResponse postData(DTORequest request);

    public DTOResponse updateData(DTORequest request);

    public DTOResponse deleteData(DTORequest request);

    public List<DTOResponse> getAllData();

    public DTOResponse getDetailData(ID id);
}
