package com.rplara.demoparkapi.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioResponseDto {
    // classe do dto responsável pelo response
    private Long id;
    private String username;
    private String role;
}
