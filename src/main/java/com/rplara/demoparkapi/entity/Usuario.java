package com.rplara.demoparkapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

// lombok para geração do boilerplate
@Getter @Setter @NoArgsConstructor
// mapeamento
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;
    @Column(name="password", nullable = false, length = 200)
    private String password;

    // utilização do parâmetro enumerado
    // o parâmetro enumtype.string define o enum com a string. enumtype.ordinal define como índices
    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false, length = 20)
    private Role role;

    // processo de auditoria
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;
    @Column(name = "criado_por")
    private String criadoPor;
    @Column(name = "modificado_por")
    private String modificadoPor;

    public enum Role{
        ROLE_ADMIN, ROLE_CLIENTE
    }

    // preferível a criação do equals e hashCode manualmente, dessa forma. O Lombok gera com o objeto inteiro, aqui é gerado somente id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                '}';
    }
}
