package com.br.sga.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails, Serializable {

    @Id
    @Column(name = "matricula", nullable = false)
    private String matricula;

    @Column(name = "nome")
    private String nome;

    @Column(name = "senha", nullable = false)
    private String senha;

    @ManyToMany
    @JoinTable(name = "usuario_role",
            joinColumns = @JoinColumn(name = "matricula"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Roles> roles;

    public Usuario(String matricula, String nome, String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
