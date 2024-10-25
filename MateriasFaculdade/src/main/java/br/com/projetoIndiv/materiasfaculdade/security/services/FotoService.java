package br.com.projetoIndiv.materiasfaculdade.security.services;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projetoIndiv.materiasfaculdade.security.entities.Estudante;
import br.com.projetoIndiv.materiasfaculdade.security.entities.Foto;
import br.com.projetoIndiv.materiasfaculdade.security.repositories.FotoRepository;

@Service
public class FotoService {

    @Autowired
    FotoRepository fotoRepository;

    @Transactional
    public Foto cadastrarFoto(MultipartFile foto, Estudante estudante) throws IOException {
        Foto fotinha = new Foto();

        fotinha.setDados(foto.getBytes());
        fotinha.setTipo(foto.getContentType());
        fotinha.setNome(foto.getOriginalFilename());
        fotinha.setEstudante(estudante);
        fotinha.setUrl(addImgUri(estudante));

        return fotoRepository.save(fotinha);
    }

    private String addImgUri(Estudante estudante) {
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/auth/{id}/foto")
                .buildAndExpand(estudante.getId()).toUri();

        return uri.toString();
    }

    @Transactional(readOnly = true)
    public byte[] getFoto(Integer id) throws Exception {
        Foto newFoto = fotoRepository.buscarFotoByIdUser(id);

        if (newFoto == null) {
            throw new Exception("Foto não encontrado para usuario com id: " + id);
        }
        return newFoto.getDados();
    }
}