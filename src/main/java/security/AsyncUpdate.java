package security;

import dao.OrderDetailRepository;
import dao.PaymentWallerStoreRepository;
import dao.ProductDetailRepository;
import dao.VoucherUserRepository;
import entity.Orders;
import entity.PaymentWalletStore;
import entity.ProductDetail;
import entity.VoucherUser;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsyncUpdate {

    @Autowired
    VoucherUserRepository voucherUserRepository;


    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    PaymentWallerStoreRepository paymentWallerStoreRepository;

    @Async("taskExecutor")
    public void updateVoucherUser(String userLogin,Long voucherCode){
        VoucherUser voucherUser = voucherUserRepository.findVoucherUserByUserLoginAndVoucher(userLogin,voucherCode).orElseThrow(()->new DataNotFoundException("Du lieu khogn ton tai!"));
        voucherUserRepository.delete(voucherUser);
    }
    @Async("taskExecutor")
    public void updateProductDetail(List<Orders> ordersList){
       ordersList.forEach(item->{
           item.getOrderDetailList().forEach(jtem->{
               ProductDetail productDetail = jtem.getProductDetail();
               productDetail.setQuantity(productDetail.getQuantity()-jtem.getQuantity());
               productDetailRepository.save(productDetail);
           });
       });
    }

    @Async("taskExecutor")
    public void updatePaymentWalletStore(Orders orders){
        PaymentWalletStore paymentWalletStore = paymentWallerStoreRepository.findPaymentWalletByStore(orders.getId()).orElseThrow(()->new DataNotFoundException("Vi khong ton tai"));
        paymentWalletStore.setBalance(paymentWalletStore.getBalance()+orders.getFinalTotal()-orders.getTotalAmountShip());
        paymentWallerStoreRepository.save(paymentWalletStore);
    }

}
