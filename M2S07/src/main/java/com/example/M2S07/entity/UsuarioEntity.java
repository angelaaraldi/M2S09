package com.example.M2S07.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Data
public class UsuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_papel", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private Set<PerfilEntity> perfilEntityList;
    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private FuncionarioEntity funcionario;
    @ManyToOne
    @JoinColumn(name = "id_nutricionista")
    private NutricionistaEntity nutricionista;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfilEntityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
