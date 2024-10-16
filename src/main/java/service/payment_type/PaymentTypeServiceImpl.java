package service.payment_type;

import dao.PaymentTypeRepository;
import dto.payment_type.PaymentTypeMapper;
import dto.payment_type.PaymentTypeRequestDTO;
import dto.payment_type.PaymentTypeResponseDTO;
import entity.PaymentType;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {

    @Autowired
    PaymentTypeRepository paymentTypeRepository;

    @Override
    public PaymentTypeResponseDTO postData(PaymentTypeRequestDTO requestDTO) {
        PaymentType paymentType = PaymentTypeMapper.INSTANCE.toPaymentType(requestDTO);
        paymentType.setCreatedDate(new Date());
        paymentType.setId(null);
        paymentType.setDeleted(false);
        return PaymentTypeMapper.INSTANCE.toPaymentTypeResponseDto(paymentTypeRepository.save(paymentType));
    }

    @Override
    public PaymentTypeResponseDTO updateData(PaymentTypeRequestDTO requestDTO) {
        PaymentType paymentType = paymentTypeRepository.findById(requestDTO.getPaymentTypeCode()).orElseThrow(() -> new DataNotFoundException("Du lieu khong ton tai!"));
        paymentType.setName(requestDTO.getName());
        paymentType.setImage(requestDTO.getImage());
        paymentType.setUpdatedDate(new Date());
        return PaymentTypeMapper.INSTANCE.toPaymentTypeResponseDto(paymentTypeRepository.save(paymentType));
    }

    @Override
    public PaymentTypeResponseDTO deleteData(PaymentTypeRequestDTO requestDTO) {
        PaymentType paymentType = paymentTypeRepository.findById(requestDTO.getPaymentTypeCode()).orElseThrow(() -> new DataNotFoundException("Du lieu khong ton tai!"));
        paymentType.setDeleted(true);
        paymentType.setDeletedDate(new Date());
        return PaymentTypeMapper.INSTANCE.toPaymentTypeResponseDto(paymentTypeRepository.save(paymentType));
    }

    @Override
    public List<PaymentTypeResponseDTO> getAllData() {
        return PaymentTypeMapper.INSTANCE.toPaymentTypeResponseDtoList(paymentTypeRepository.findAll());
    }

    @Override
    public PaymentTypeResponseDTO getDetailData(Long aLong) {
        return null;
    }
}
