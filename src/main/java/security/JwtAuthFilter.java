package security;

import entity.enum_package.RoleType;
import exeception_handler.TokenExpiredException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import service.AdministrationService;
import service.common.JWTService;
import service.StoreService;
import service.UserAccountService;
import service.data_return.DataReturnService;

import java.io.IOException;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JWTService jwtService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    AdministrationService administrationService;


    @Autowired
    DataReturnService dataReturnService;

    @Autowired
    StoreService storeService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader= request.getHeader("Authorization");
            String token = null;
            String username = null;
            String roleLogin = null;



            if(authHeader != null && authHeader.startsWith("Bearer ")){
                token = authHeader.substring(7);
                username= jwtService.extractUsername(token);
                roleLogin= jwtService.extractRoleLogin(token);
            }

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                String[] detachResult = username.split("&");
                UserDetails userDetails = null;
                if(roleLogin.equals(RoleType.USER.name())) {
                    userDetails = userAccountService.loadUserByUsername(username);
                } else if (roleLogin.equals(RoleType.ADMIN.name())) {
                    userDetails = administrationService.loadUserByUsername(username);
                } else if (roleLogin.equals(RoleType.SHIPPER.name())) {
                    userDetails = administrationService.loadUserByUsername(username);
                }else if (roleLogin.equals(RoleType.STORE.name())) {
                    userDetails = storeService.loadUserByUsername(username);
                }

                if(userDetails != null && jwtService.validateToken(token,userDetails)){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }else{
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Token không hợp lệ
                    return;
                }

            }
            filterChain.doFilter(request,response);
        }catch (ExpiredJwtException e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token da het han!");
        }

    }
}
