package service.discount;

import dao.DiscountRepository;
import dto.discount.DiscountMapper;
import dto.discount.DiscountRequestDTO;
import dto.discount.DiscountResponseDTO;
import entity.Discount;
import exeception_handler.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
@Autowired
DiscountRepository discountRepository;

    @Override
    public DiscountResponseDTO postData(DiscountRequestDTO discountRequestDTO) {
        discountRequestDTO.setBeginDate(new Date());
        Discount discount = discountRepository.save(DiscountMapper.INSTANCE.toDiscount(discountRequestDTO));
        return DiscountMapper.INSTANCE.toDiscountResponseDTO(discount);
    }

    @Override
    public DiscountResponseDTO updateData(DiscountRequestDTO discountRequestDTO) {
        Discount discount = discountRepository.findById(Long.valueOf(discountRequestDTO.getDiscountCode())).orElseThrow(()-> new DataNotFoundException("Data not found"));
        discount.setBeginDate(new Date());

        return DiscountMapper.INSTANCE.toDiscountResponseDTO(discountRepository.save(discount));
    }

    @Override
    public DiscountResponseDTO deleteData(DiscountRequestDTO discountRequestDTO) {
        Discount discount = discountRepository.findById(Long.valueOf(discountRequestDTO.getDiscountCode())).orElseThrow(()-> new DataNotFoundException("Data not found"));
        discount.setDeleted(true);
        discount.setDeletedDate(new Date());
        return DiscountMapper.INSTANCE.toDiscountResponseDTO(discountRepository.save(discount));
    }

    @Override
    public List<DiscountResponseDTO> getAllData() {
        return DiscountMapper.INSTANCE.toDiscountResponseDTOList(discountRepository.findAll());
    }

    @Override
    public DiscountResponseDTO getDetailData(Long aLong) {
        return null;
    }
}
