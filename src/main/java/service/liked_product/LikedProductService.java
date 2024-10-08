package service.liked_product;

import dto.liked_product.LikedProductRequestDTO;
import dto.liked_product.LikedProductResponseDTO;

import java.util.List;

public interface LikedProductService {



    public List<LikedProductResponseDTO> getAllLikedProduct(LikedProductRequestDTO requestDTO);


    public LikedProductResponseDTO postLikedProduct(LikedProductRequestDTO requestDTO);


    public LikedProductResponseDTO postUnlikedProduct(LikedProductRequestDTO requestDTO);


}
