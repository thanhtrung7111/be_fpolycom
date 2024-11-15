package service.store_group_mess;

import dao.GroupStoreMessageRepository;
import dao.StoreRepository;
import dao.UserAccountRepository;
import dto.store_group_mess.StoreGroupMessMapper;
import dto.store_group_mess.StoreGroupMessRequestDTO;
import dto.store_group_mess.StoreGroupMessResponseDTO;
import entity.GroupMessageStore;
import entity.Store;
import entity.UserAccount;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StoreGroupMessServiceImpl implements StoreGroupMessService {
    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    GroupStoreMessageRepository groupStoreMessageRepository;

    @Override
    public StoreGroupMessResponseDTO postNewGroup(StoreGroupMessRequestDTO requestDTO) {
        UserAccount userAccount = userAccountRepository.findById(requestDTO.getUserCode()).orElseThrow(()->new DataNotFoundException("Nguoi dung khong ton tai!"));
        Store store = storeRepository.findById(requestDTO.getStoreCode()).orElseThrow(()->new DataNotFoundException("Cua hang khong ton tai!"));
        GroupMessageStore groupMessageStore = GroupMessageStore.builder().store(store).userAccount(userAccount).createdDate(new Date()).deleted(false).deletedDate(null).updatedDate(null).build();
        groupStoreMessageRepository.save(groupMessageStore);
        return StoreGroupMessMapper.INSTANCE.toStoreGroupMessResponseDto(groupMessageStore);
    }

    @Override
    public List<StoreGroupMessResponseDTO> getAllGroupByStore(Long storeCode) {
        return StoreGroupMessMapper.INSTANCE.toStoreGroupMessResponseDtoList(groupStoreMessageRepository.findAllByStoreCode(storeCode));
    }

    @Override
    public List<StoreGroupMessResponseDTO> getAllGroupByUser(Long userCode) {
        return StoreGroupMessMapper.INSTANCE.toStoreGroupMessResponseDtoList(groupStoreMessageRepository.findAllByUserCode(userCode));
    }
}
