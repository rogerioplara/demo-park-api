package com.rplara.demoparkapi.web.dto.mapper;

import com.rplara.demoparkapi.entity.Usuario;
import com.rplara.demoparkapi.web.dto.UsuarioCreateDto;
import com.rplara.demoparkapi.web.dto.UsuarioResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

/*
* O DTO é uma objeto de transferência de dados - data transfer object
* Server para selecionar, limitar e ajustar os dados passados de camada para camada.
* Com isso, é possível processar algum dado que deseja passar ou que recebeu, sem que interfira no sistema em si
*
* No exemplo abaixo, são implementados os mapeamentos da classe usuário, transformando de usuário para dto ou de dto para usuário.
*
*/

public class UsuarioMapper {

    // Método de conversão do dto para usuário
    public static Usuario toUsuario(UsuarioCreateDto createDto){
        return new ModelMapper().map(createDto, Usuario.class);
    }

    // Método de conversão do usuário para dto - alterando a string retornada do ROLE
    public static UsuarioResponseDto toDto(Usuario usuario){
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDto> props = new PropertyMap<Usuario, UsuarioResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };

        ModelMapper mapper =  new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(usuario, UsuarioResponseDto.class);
    }

    // Método para utilização de listas
    public static List<UsuarioResponseDto> toListDto(List<Usuario> usuarios){
        // o stream().map() vai transformar todos os usuários passados em um UsuarioResponseDto
        // aqui será usado uma função lambda
        return usuarios.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }


}
