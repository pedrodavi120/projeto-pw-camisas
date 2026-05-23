package ufrn.br.provaPW.service;

import org.springframework.stereotype.Service;
import ufrn.br.provaPW.exception.RecursoNaoEncontradoException;
import ufrn.br.provaPW.model.camisetas;
import ufrn.br.provaPW.repository.camisetasRepository;

import java.util.List;

@Service
public class camisetasService {

    private final camisetasRepository repository;

    public camisetasService(camisetasRepository repository) {
        this.repository = repository;
    }

    public List<camisetas> listarAtivos() {
        return repository.findByIsDeletedNull();
    }

    public List<camisetas> listarTodos() {
        return repository.findAll();
    }

    public camisetas buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Camiseta com ID " + id + " não encontrada no sistema."));
    }

    public camisetas salvar(camisetas camiseta) {
        return repository.save(camiseta);
    }
}
