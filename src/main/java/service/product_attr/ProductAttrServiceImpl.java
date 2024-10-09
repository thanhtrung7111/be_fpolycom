package service.product_attr;

import dao.ProductAttrRepository;
import dto.product_attr.ProductAttrMapper;
import dto.product_attr.ProductAttrRequestDTO;
import dto.product_attr.ProductAttrResponseDTO;
import dto.province.ProvinceMapper;
import entity.ProductAttr;
import entity.Province;
import exeception_handler.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductAttrServiceImpl implements ProductAttrService {
    ProductAttrRepository productAttrRepository;


    @Override
    public ProductAttrResponseDTO postData(ProductAttrRequestDTO productAttrRequestDTO) {
        ProductAttr saved = productAttrRepository.save(ProductAttrMapper.INSTANCE.toProductAttr(productAttrRequestDTO));
        return ProductAttrMapper.INSTANCE.toProductAttrResponseDTO(saved);
    }

    @Override
    public ProductAttrResponseDTO updateData(ProductAttrRequestDTO productAttrRequestDTO) {
        ProductAttr saved = productAttrRepository.findById(Long.valueOf(productAttrRequestDTO.getProductAttrCode())).orElseThrow(()->new DataNotFoundException("Khong tim thay du lieu"));
        saved.setUpdatedDate(new Date());
        return ProductAttrMapper.INSTANCE.toProductAttrResponseDTO(productAttrRepository.save(saved));
    }

    @Override
    public ProductAttrResponseDTO deleteData(ProductAttrRequestDTO productAttrRequestDTO) {
        ProductAttr saved = productAttrRepository.findById(Long.valueOf(productAttrRequestDTO.getProductAttrCode())).orElseThrow(()->new DataNotFoundException("Khong tim thay du lieu"));
        saved.setDeleted(true);
        saved.setDeletedDate(new Date());
        return ProductAttrMapper.INSTANCE.toProductAttrResponseDTO(productAttrRepository.save(saved));
    }

    @Override
    public List<ProductAttrResponseDTO> getAllData() {
        return ProductAttrMapper.INSTANCE.toList(productAttrRepository.findAll());
    }

    @Override
    public ProductAttrResponseDTO getDetailData(Long aLong) {
        return null;
    }
}
