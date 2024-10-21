package br.com.projetoIndiv.materiasfaculdade.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.projetoIndiv.materiasfaculdade.security.dto.EnderecoResponseDTO;

@Component
public class Util {
    public EnderecoResponseDTO getEndereco(String cep) {
        RestTemplate Template = new RestTemplate();
        return Template.getForObject("https://viacep.com.br/ws/{cep}/json", EnderecoResponseDTO.class, cep);
    }
}