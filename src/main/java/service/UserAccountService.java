package service;

import dao.PasswordRecoverRepository;
import dao.PaymentWalletUserRepository;
import dao.TokenRegisterRepository;
import dao.UserAccountRepository;
import dto.user_auth.ChangePasswordRequestDTO;
import dto.user_auth.ForgotPasswordRequestDTO;
import dto.user_account.*;
import entity.*;
import entity.enum_package.UserStatus;
import exeception_handler.DataNotFoundException;
import exeception_handler.NotRightException;
import exeception_handler.TokenExpiredException;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import security.UserInfoDetails;
import service.auth_user.AuthUserService;
import service.common.EncodingService;
import service.common.MailService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserAccountService implements UserDetailsService {

    @Autowired
    UserAccountRepository userAccountRepository;


    @Autowired
    TokenRegisterRepository tokenRegisterRepository;

    @Autowired
    EncodingService encodingService;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    MailService mailService;

    @Autowired
    PasswordRecoverRepository passwordRecoverRepository;

    @Autowired
    PaymentWalletUserRepository paymentWalletUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> userDetail = userAccountRepository.findByUserLoginAndStatus(username, UserStatus.active);
        return userDetail.map(UserInfoDetails::new).orElseThrow(() -> new UsernameNotFoundException("Thoong tin dang nhap sai hoac nguoi dung chua xac thuc email!"));
    }


    public UserAccountRegisterResponseDTO registerAccount(UserAccountRegisterRequestDTO request) throws MessagingException {
        UserAccount userAccount = UserAccountMapper.INSTANCE.toUserAccount(request);
        userAccount.setPassword(new BCryptPasswordEncoder().encode(userAccount.getPassword()));
        userAccount.setUserStatus(UserStatus.pending);
        userAccount.setCreatedDate(new Date());
        UserAccount saved = userAccountRepository.save(userAccount);
        LocalDateTime curentDateTime = LocalDateTime.now();
        LocalDateTime expiredDateTime = curentDateTime.plusMinutes(15);
        TokenRegister tokenRegister = TokenRegister.builder().token(UUID.randomUUID().toString()).userAccount(saved).createdDate(new Date()).expiredDate(expiredDateTime).build();
        tokenRegisterRepository.save(tokenRegister);
        mailService.sendMail(request.getEmail(), "Xac nhan dang ki", "<!DOCTYPE html>\n" +
                "<html lang='en'>\n" +
                "  <head>\n" +
                "    <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0' />\n" +
                "    <title>Document</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div\n" +
                "      style='\n" +
                "        padding: 10px;\n" +
                "        border: 1px solid rgb(215, 215, 215);\n" +
                "        border-radius: 5px;\n" +
                "      '\n" +
                "    >\n" +
                "      <h3 style='color: rgb(48, 48, 48); margin-top: 0px'>FpolyCom</h3>\n" +
                "      <p>Thank you for register!</p>\n" +
                "      <a\n" +
                "        href='http://localhost:5173/confirm_account/"+tokenRegister.getToken()+"'\n" +
                "        style='\n" +
                "          width: fit-content;\n" +
                "          display: block;\n" +
                "          border: 1px solid gray;\n" +
                "          text-decoration: none;\n" +
                "          color: white;\n" +
                "          background-color: rgb(30, 30, 30);\n" +
                "          padding: 10px;\n" +
                "        '\n" +
                "        >Confirm your account</a\n" +
                "      >\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>\n");
        return UserAccountMapper.INSTANCE.toUserAccountRegisterResponseDto(saved);
    }

    public UserAccountRegisterResponseDTO confirmAccount(String token) {
        UserAccount userAccount = userAccountRepository.findByTokenRegister(token).orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng đăng kí!"));
        if (userAccount.getTokenRegister().getExpiredDate().isBefore(LocalDateTime.now())) {
            tokenRegisterRepository.delete(userAccount.getTokenRegister());
            throw new TokenExpiredException("Token đã hết hạn");
        }
        tokenRegisterRepository.delete(userAccount.getTokenRegister());
        userAccount.setUserStatus(UserStatus.active);
        userAccount.setTokenRegister(null);
        userAccountRepository.save(userAccount);
        paymentWalletUserRepository.save(PaymentWalletUser.builder().createdDate(new Date()).updatedDate(null).deletedDate(null).deleted(false).userAccount(userAccount).balance(0.0).build());
        return UserAccountMapper.INSTANCE.toUserAccountRegisterResponseDto(userAccount);
    }


    public UserAccountRegisterResponseDTO changePassword(ChangePasswordRequestDTO changePasswordRequestDTO) {
        if (!authUserService.isValidUserLogin(changePasswordRequestDTO.getUserLogin())) {
            throw new NotRightException("Bạn khong có quyền truy cập thao tác treen nguoiw dung nay!");
        }
        String username = authUserService.extractUserlogin(changePasswordRequestDTO.getUserLogin());
        UserAccount userAccount = userAccountRepository.findByUserLogin(username).orElseThrow(() -> new UsernameNotFoundException("Khong ton tai nguoi dung!"));


        if (!new BCryptPasswordEncoder().matches(changePasswordRequestDTO.getPasswordCurrent(), userAccount.getPassword())) {
            throw new DataNotFoundException("Mat khau hien tai khong dung!");
        }

        userAccount.setPassword(new BCryptPasswordEncoder().encode(changePasswordRequestDTO.getPasswordNew()));
        userAccountRepository.save(userAccount);
        return UserAccountMapper.INSTANCE.toUserAccountRegisterResponseDto(userAccount);
    }

    public String forgotPassword(String email) throws MessagingException {
        UserAccount userAccount = userAccountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Khong tim tha nguoi dung"));
        if(userAccount.getPasswordRecover()!=null){
            passwordRecoverRepository.delete(userAccount.getPasswordRecover());
        }
        String token = UUID.randomUUID().toString();
        PasswordRecover passwordRecover = PasswordRecover.builder().tokenRecover(token).userAccount(userAccount).expiredDate(LocalDateTime.now().plusMinutes(20)).build();
        passwordRecoverRepository.save(passwordRecover);
        mailService.sendMail(userAccount.getEmail(), "Recover password", "<!DOCTYPE html>\n" +
                "<html lang='en'>\n" +
                "  <head>\n" +
                "    <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0' />\n" +
                "    <title>Document</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div\n" +
                "      style='\n" +
                "        padding: 10px;\n" +
                "        border: 1px solid rgb(215, 215, 215);\n" +
                "        border-radius: 5px;\n" +
                "      '\n" +
                "    >\n" +
                "      <h3 style='color: rgb(48, 48, 48); margin-top: 0px'>FpolyCom</h3>\n" +
                "      <p>Recover Your Passwo!</p>\n" +
                "      <a\n" +
                "        href='http://localhost:5173/recover_password/"+passwordRecover.getTokenRecover()+"'\n" +
                "        style='\n" +
                "          width: fit-content;\n" +
                "          display: block;\n" +
                "          border: 1px solid gray;\n" +
                "          text-decoration: none;\n" +
                "          color: white;\n" +
                "          background-color: rgb(30, 30, 30);\n" +
                "          padding: 10px;\n" +
                "        '\n" +
                "        >Revocer Password</a\n" +
                "      >\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>\n");
        return "Đã send link thay doi " + token;
    }

    public UserAccountRegisterResponseDTO changePasswordForgot(ForgotPasswordRequestDTO requestDTO) {
        UserAccount userAccount = userAccountRepository.findByTokenPasswordRecover(requestDTO.getTokenRecover()).orElseThrow(() -> new UsernameNotFoundException("Khong tim thay nguoi dung trong passwordRecover"));
        userAccount.setPassword(new BCryptPasswordEncoder().encode(requestDTO.getPasswordNew()));
        userAccountRepository.save(userAccount);
        passwordRecoverRepository.delete(userAccount.getPasswordRecover());
        return UserAccountMapper.INSTANCE.toUserAccountRegisterResponseDto(userAccount);
    }

    public UserAccountRegisterResponseDTO getForgotPassword(String token) throws MessagingException {
        UserAccount userAccount = userAccountRepository.findByTokenPasswordRecover(token).orElseThrow(() -> new UsernameNotFoundException("Khong tim passwordRecover nguoi dung"));
        return UserAccountMapper.INSTANCE.toUserAccountRegisterResponseDto(userAccount);
    }

    public UserAccountChangeResponseDTO getInfoUser(String token) throws MessagingException {
        if (!authUserService.isValidUserLogin(token)) {
            throw new NotRightException("Bạn khong có quyền truy cập thao tác treen nguoiw dung nay!");
        }
        String username = authUserService.extractUserlogin(token);
        UserAccount userAccount = userAccountRepository.findByUserLogin(username).orElseThrow(() -> new UsernameNotFoundException("Nguoi dung khong ton tai!"));
        return UserAccountMapper.INSTANCE.toUserAccountChangeResponseDto(userAccount);
    }


    public UserAccountChangeResponseDTO changeInfomationUser(UserAccountChangeRequestDTO request) {
        if (!authUserService.isValidUserLogin(request.getUserLogin())) {
            throw new NotRightException("Bạn khong có quyền truy cập thao tác treen nguoiw dung nay!");
        }
        UserAccount userAccount = userAccountRepository.findByUserLogin(encodingService.decode(request.getUserLogin())).orElseThrow(() -> new UsernameNotFoundException("Tai khoan nguoi dung khong ton tai!"));
        if (request.getName() != null && !request.getName().isBlank()) {
            userAccount.setName(request.getName());
        }

        if (request.getPhone() != null && !request.getPhone().isBlank()) {
            userAccount.setPhone(request.getPhone());
        }

        if (request.getAddressDetail() != null && !request.getAddressDetail().isBlank()) {
            userAccount.setAddressDetail(request.getAddressDetail());
        }

        if (request.getAddress() != null && !request.getAddress().isBlank()) {
            userAccount.setAddress(request.getAddress());
        }

        if (request.getImage() != null && !request.getImage().isBlank()) {
            userAccount.setImage(request.getImage());
        }

        if (request.getBannerImage() != null && !request.getBannerImage().isBlank()) {
            userAccount.setBannerImage(request.getBannerImage());
        }

        if (request.getDateOfBirth() != null) {
            userAccount.setDateOfBirth(request.getDateOfBirth());
        }

        if (request.getGender() != null) {
            userAccount.setGender(request.getGender());
        }

        if (request.getProvinceCode() != null) {
            userAccount.setProvince(Province.builder().id(request.getProvinceCode()).build());
        }

        if (request.getDistrictCode() != null) {
            userAccount.setDistrict(District.builder().id(request.getDistrictCode()).build());
        }


        if (request.getWardCode() != null) {
            userAccount.setWard(Ward.builder().id(request.getWardCode()).build());
        }
        userAccountRepository.saveAndFlush(userAccount);


        return UserAccountMapper.INSTANCE.toUserAccountChangeResponseDto(userAccount);
    }


}
