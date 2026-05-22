package ufrn.br.provaPW.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.br.provaPW.model.camisetas;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface camisetasRepository extends JpaRepository<camisetas, Long> {
    List<camisetas> findByIsDeletedNull(); // Método para encontrar camisetas que não foram deletadas'
}
