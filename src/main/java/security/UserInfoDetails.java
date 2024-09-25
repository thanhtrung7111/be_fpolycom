package security;

import entity.Administration;
import entity.Store;
import entity.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserInfoDetails implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorityList;

    public UserInfoDetails(UserAccount userAccount){
        this.username = userAccount.getUserLogin();
        this.password = userAccount.getPassword();
        this.authorityList = List.of(new SimpleGrantedAuthority("USER"));
    }

    public UserInfoDetails(Administration administration){
        this.username = administration.getUserLogin();
        this.password = administration.getPassword();
        this.authorityList = List.of(new SimpleGrantedAuthority("ADMIN"));
    }

    public UserInfoDetails(Store store){
        this.username = store.getUserAccount().getUserLogin();
        this.password = store.getPassword();
        this.authorityList = List.of(new SimpleGrantedAuthority("STORE"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
