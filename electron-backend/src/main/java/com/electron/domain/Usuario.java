package com.electron.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.electron.domain.enums.Cargo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

    public Usuario(String email, String senha, String nome, Cargo cargo) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cargo = cargo;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @NotNull
    // @Size(min = 6, max = 14)
    private String senha;

    @NotNull
    private String nome;

    @Enumerated(EnumType.STRING)
    private Cargo cargo = Cargo.USUARIO;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.cargo == Cargo.ADMIN)
            return List.of(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("USUARIO"));
        else return List.of(new SimpleGrantedAuthority("USUARIO"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
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
