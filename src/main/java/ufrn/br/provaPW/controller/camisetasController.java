package ufrn.br.provaPW.controller;

import ufrn.br.provaPW.model.camisetas;
import ufrn.br.provaPW.repository.camisetasRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.ArrayList;

@Controller
public class camisetasController {

    private final camisetasRepository repository;

    public camisetasController(camisetasRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/camisetas")
    public String listarCamisetas(Model model, HttpSession session) {
        List<camisetas> camisetasList = repository.findByIsDeletedNull();

        List<camisetas> carrinho = (List<camisetas>) session.getAttribute("carrinho");
        if (carrinho == null) {
            int quantidadeCarrinho = carrinho.size();
        }
        model.addAttribute("quantidadeCarrinho", carrinho.size());
        model.addAttribute("camisetas", camisetasList);
        return "camisetas"; // Retorna o nome da view (camisetas.html)
    }
}