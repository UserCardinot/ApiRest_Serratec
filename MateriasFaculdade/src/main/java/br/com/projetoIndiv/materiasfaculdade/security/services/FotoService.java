package br.com.projetoIndiv.materiasfaculdade.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.projetoIndiv.materiasfaculdade.security.entities.Estudante;
import br.com.projetoIndiv.materiasfaculdade.security.entities.Foto;
import br.com.projetoIndiv.materiasfaculdade.security.repositories.FotoRepository;

@Service
public class FotoService {

    @Autowired
    FotoRepository fotoRepository;

    public Foto cadastrarFoto(MultipartFile foto, Estudante estudante) throws Exception {
        Foto fotinha = new Foto();

        fotinha.setDados(foto.getBytes());
        fotinha.setTipo(foto.getContentType());
        fotinha.setNome(foto.getOriginalFilename());
        fotinha.setEstudante(estudante);

        return fotoRepository.save(fotinha);
    }
}