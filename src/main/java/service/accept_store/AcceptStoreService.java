package service.accept_store;

import dto.accept_store.AcceptStoreRequestDTO;
import dto.accept_store.AcceptStoreResponseDTO;

import java.util.List;

public interface AcceptStoreService<DTORequest,DTOResponse> {
    public List<DTORequest> getAll();

    AcceptStoreResponseDTO lockStore (AcceptStoreRequestDTO request);

    AcceptStoreResponseDTO unlockStore (AcceptStoreRequestDTO request);

}
