package com.database.model;

import com.database.model.entity.UserAccount;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class UserPrincipal implements UserDetails {

    private UserAccount userAccount;

    @Autowired
    public UserPrincipal(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.concat(getPermissionsListWithGrandedAuth().stream(),
                getRolesListWithGrandedAuth().stream())
                .collect(Collectors.toList());
    }

    private List<SimpleGrantedAuthority> getPermissionsListWithGrandedAuth() {
        return userAccount.getPermissionsList()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private List<SimpleGrantedAuthority> getRolesListWithGrandedAuth() {
        return userAccount.getRolesList()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.userAccount.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userAccount.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.userAccount.isAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.userAccount.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.userAccount.isAccountExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.userAccount.isActive();
    }
}
