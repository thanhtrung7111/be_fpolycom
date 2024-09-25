package security;

import entity.enum_package.RoleType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import service.AdministrationService;
import service.JWTService;
import service.UserAccountService;

import java.io.IOException;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JWTService jwtService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    AdministrationService administrationService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader= request.getHeader("Authorization");
        String token = null;
        String username = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            username= jwtService.extractUsername(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            String[] detachResult = username.split("&");
            UserDetails userDetails = null;
            if(detachResult[1].equals(RoleType.USER.name())) {
                userDetails = userAccountService.loadUserByUsername(detachResult[0]);
            } else if (detachResult[1].equals(RoleType.ADMIN.name())) {
                userDetails = administrationService.loadUserByUsername(detachResult[0]);
            } else if (detachResult[1].equals(RoleType.SHIPPER.name())) {
                userDetails = administrationService.loadUserByUsername(detachResult[0]);
            }else if (detachResult[1].equals(RoleType.STORE.name())) {
                userDetails = administrationService.loadUserByUsername(detachResult[0]);
            }

            if(jwtService.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }
        filterChain.doFilter(request,response);
    }
}
