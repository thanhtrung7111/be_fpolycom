package service.user_cart;

import dto.user_cart.UserCartRequestDTO;
import dto.user_cart.UserCartResponseDTO;

import java.util.List;

public interface UserCartService {



    public List<UserCartResponseDTO> getAllCartByUser(UserCartRequestDTO requestDTO);


    public UserCartResponseDTO postCartByUser(UserCartRequestDTO requestDTO);

    public UserCartResponseDTO updateCartByUser(UserCartRequestDTO requestDTO);

    public UserCartResponseDTO deleteCartByUser(UserCartRequestDTO requestDTO);
}
