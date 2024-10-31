package security;

import dao.VoucherUserRepository;
import entity.VoucherUser;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncUpdate {

    @Autowired
    VoucherUserRepository voucherUserRepository;


    @Async("taskExecutor")
    public void updateVoucherUser(String userLogin,Long voucherCode){
        VoucherUser voucherUser = voucherUserRepository.findVoucherUserByUserLoginAndVoucher(userLogin,voucherCode).orElseThrow(()->new DataNotFoundException("Du lieu khogn ton tai!"));
        voucherUserRepository.delete(voucherUser);
    }


}
