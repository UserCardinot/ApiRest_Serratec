package br.com.projetoIndiv.materiasfaculdade.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoIndiv.materiasfaculdade.security.dto.EnderecoResponseDTO;
import br.com.projetoIndiv.materiasfaculdade.utils.Util;

@Service
public class EnderecoService {

    @Autowired
    Util util;

    public EnderecoResponseDTO buscarEndereco(String cep) {

        EnderecoResponseDTO viaCep = util.getEndereco(cep);
        EnderecoResponseDTO endereco = new EnderecoResponseDTO();

        endereco.setCep(viaCep.getCep());
        endereco.setLogradouro(viaCep.getLogradouro());
        endereco.setEstado(viaCep.getEstado());
        endereco.setBairro(viaCep.getBairro());
        endereco.setLocalidade(viaCep.getLocalidade());

        return endereco;
    }
}