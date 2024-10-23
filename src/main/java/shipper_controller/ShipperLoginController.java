package shipper_controller;

import dto.auth_shipper.ShipperLoginRequestDTO;
import dto.auth_shipper.ShipperLoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import service.auth_shipper.ShipperLoginService;
import service.common.JWTService;
import service.data_return.DataReturnService;

@RestController
@CrossOrigin("*")
public class ShipperLoginController {
    @Autowired
    ShipperLoginService shipperLoginService;

    @Autowired
    JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    DataReturnService dataReturnService;

    @PostMapping("/shipper/shipper-login")
    public ResponseEntity<Object> authenticateAndGetToken(@RequestBody ShipperLoginRequestDTO authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserLogin(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            ShipperLoginResponseDTO rs = shipperLoginService.getShipper(authRequest.getUserLogin());
            String token = jwtService.generateToken(authentication.getName());
            rs.setToken(token);
            return ResponseEntity.ok().body(dataReturnService.success(rs));
        } else {
            return ResponseEntity.ok().body("Fail");
        }
    }
}
