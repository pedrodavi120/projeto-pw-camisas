package ufrn.br.provaPW.controller;

import ufrn.br.provaPW.model.camisetas;
import ufrn.br.provaPW.dto.camisetaDTO;
import ufrn.br.provaPW.service.camisetasService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Date;

@Controller
public class camisetasController {

    private final camisetasService service;

    public camisetasController(camisetasService service) {
        this.service = service;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model, HttpSession session) {
        List<camisetas> camisetasList = service.listarAtivos();
        
        List<camisetas> carrinho = (List<camisetas>) session.getAttribute("carrinho");
        int quantidadeCarrinho = (carrinho == null) ? 0 : carrinho.size();
        
        model.addAttribute("quantidadeCarrinho", quantidadeCarrinho);
        model.addAttribute("camisetas", camisetasList);
        return "index"; // Retorna o nome da view index.html (Questão 4)
    }

    @GetMapping("/camisetas")
    public String listarCamisetas() {
        return "redirect:/index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<camisetas> camisetasList = service.listarTodos();
        model.addAttribute("camisetas", camisetasList);
        return "admin"; // Retorna o nome da view admin.html (Questão 5)
    }

    @GetMapping("/cadastro")
    public String cadastrar(Model model) {
        model.addAttribute("camisetaDto", new camisetaDTO());
        return "cadastro"; // Retorna o nome da view cadastro.html (Questão 6)
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Retorna o nome da view login.html
    }

    @GetMapping("/editar")
    public String editar(@RequestParam Long id, Model model) {
        camisetas camiseta = service.buscarPorId(id);

        camisetaDTO dto = new camisetaDTO();
        dto.setId(camiseta.getId());
        dto.setNome(camiseta.getNome());
        dto.setMarca(camiseta.getMarca());
        dto.setDescricao(camiseta.getDescricao());
        dto.setPreco(camiseta.getPreco());
        dto.setTamanho(camiseta.getTamanho());
        dto.setImgUrl(camiseta.getImgUrl());
        dto.setCodigoSku(camiseta.getCodigoSku());
        
        model.addAttribute("camisetaDto", dto);
        return "editar"; // Retorna o nome da view editar.html (Questão 7)
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("camisetaDto") camisetaDTO dto, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            // Retorna para o formulário de origem correspondente
            if (dto.getId() == null) {
                return "cadastro";
            } else {
                return "editar";
            }
        }

        camisetas camiseta;
        if (dto.getId() == null) {
            camiseta = new camisetas();
            // Selecionar imagem aleatória (Questão 6/8)
            List<String> imagens = Arrays.asList(
                "/images/camisa-ov1.png",
                "/images/camisa-ov2.png",
                "/images/camisa-ov3.png",
                "/images/camisa-ov4.png",
                "/images/camisa-ov5.png"
            );
            String randomImg = imagens.get(new Random().nextInt(imagens.size()));
            camiseta.setImgUrl(randomImg);
        } else {
            camiseta = service.buscarPorId(dto.getId());
            if (dto.getImgUrl() != null && !dto.getImgUrl().isEmpty()) {
                camiseta.setImgUrl(dto.getImgUrl());
            }
        }

        camiseta.setNome(dto.getNome());
        camiseta.setMarca(dto.getMarca());
        camiseta.setDescricao(dto.getDescricao());
        camiseta.setPreco(dto.getPreco());
        camiseta.setTamanho(dto.getTamanho());
        camiseta.setCodigoSku(dto.getCodigoSku());

        service.salvar(camiseta);
        redirectAttributes.addFlashAttribute("mensagem", "Camiseta salva com sucesso!");
        return "redirect:/admin"; // Redireciona para o admin (Questão 8)
    }

    @GetMapping("/detalhe/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        camisetas camiseta = service.buscarPorId(id); // Lança erro se não achar (Questão 9)
        model.addAttribute("item", camiseta);
        return "detalhe";
    }

    @GetMapping("/deletar")
    public String deletar(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        camisetas camiseta = service.buscarPorId(id);
        camiseta.setIsDeleted(new Date()); // Soft-delete (Questão 10)
        service.salvar(camiseta);
        redirectAttributes.addFlashAttribute("mensagem", "Camiseta deletada com sucesso!");
        return "redirect:/index";
    }

    @GetMapping("/restaurar")
    public String restaurar(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        camisetas camiseta = service.buscarPorId(id);
        camiseta.setIsDeleted(null); // Restaurar (Questão 10)
        service.salvar(camiseta);
        redirectAttributes.addFlashAttribute("mensagem", "Camiseta restaurada com sucesso!");
        return "redirect:/index";
    }

    @GetMapping("/adicionarCarrinho")
    public String adicionarCarrinho(@RequestParam Long id, HttpSession session) {
        camisetas camiseta = service.buscarPorId(id);
        List<camisetas> carrinho = (List<camisetas>) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new ArrayList<>();
        }
        carrinho.add(camiseta);
        session.setAttribute("carrinho", carrinho); // Adiciona na sessão (Questão 11)
        return "redirect:/index";
    }

    @GetMapping("/verCarrinho")
    public String verCarrinho(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        List<camisetas> carrinho = (List<camisetas>) session.getAttribute("carrinho");
        if (carrinho == null || carrinho.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagem", "Não existem itens no seu carrinho de compras.");
            return "redirect:/index"; // Redireciona se carrinho vazio (Questão 12)
        }

        BigDecimal total = BigDecimal.ZERO;
        for (camisetas c : carrinho) {
            if (c.getPreco() != null) {
                total = total.add(c.getPreco());
            }
        }

        model.addAttribute("total", total);
        return "carrinho";
    }

    @GetMapping("/finalizarCompra")
    public String finalizarCompra(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate(); // Invalida a sessão (Questão 13)
        redirectAttributes.addFlashAttribute("mensagem", "Compra finalizada com sucesso!");
        return "redirect:/index";
    }
}

    