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
        // Questão 4: Listar apenas itens não deletados (isDeleted == null)
        List<camisetas> camisetasList = service.listarAtivos();
        
        List<camisetas> carrinho = (List<camisetas>) session.getAttribute("carrinho");
        int quantidadeCarrinho = (carrinho == null) ? 0 : carrinho.size();
        
        // Questão 4 e 11: Exibir quantidade de itens no carrinho
        model.addAttribute("quantidadeCarrinho", quantidadeCarrinho);
        model.addAttribute("camisetas", camisetasList);
        return "index"; // Questão 4
    }

    @GetMapping("/camisetas")
    public String listarCamisetas() {
        return "redirect:/index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        // Questão 5: Listar todos os itens (mesmo deletados logicamente)
        List<camisetas> camisetasList = service.listarTodos();
        model.addAttribute("camisetas", camisetasList);
        return "admin"; // Questão 5
    }

    @GetMapping("/cadastro")
    public String cadastrar(Model model) {
        // Questão 6: Retornar formulário de cadastro usando DTO
        model.addAttribute("camisetaDto", new camisetaDTO());
        return "cadastro"; // Questão 6
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Retorna o nome da view login.html
    }

    @GetMapping("/editar")
    public String editar(@RequestParam Long id, Model model) {
        // Questão 7: Buscar dados no banco para preencher o formulário
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
        return "editar"; // Questão 7
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("camisetaDto") camisetaDTO dto, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        // Questão 8: Validar atributos e retornar erro se necessário
        if (result.hasErrors()) {
            if (dto.getId() == null) {
                return "cadastro";
            } else {
                return "editar";
            }
        }

        camisetas camiseta;
        if (dto.getId() == null) {
            camiseta = new camisetas();
            // Questão 6 e 8: Selecionar imagem aleatória da pasta static
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
            // Lógica para edição
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
        // Questão 8 e 19: Mensagem de sucesso via RedirectAttributes
        redirectAttributes.addFlashAttribute("mensagem", "Camiseta salva com sucesso!");
        return "redirect:/admin"; // Questão 8
    }

    @GetMapping("/detalhe/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        // Questão 9: Rota de detalhes dinâmicos com @PathVariable
        camisetas camiseta = service.buscarPorId(id); 
        model.addAttribute("item", camiseta);
        return "detalhe";
    }

    @GetMapping("/deletar")
    public String deletar(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        camisetas camiseta = service.buscarPorId(id);
        // Questão 10: Soft-delete gravando data atual (Long)
        camiseta.setIsDeleted(System.currentTimeMillis()); 
        service.salvar(camiseta);
        // Questão 19: Mensagem via RedirectAttributes
        redirectAttributes.addFlashAttribute("mensagem", "Camiseta deletada com sucesso!");
        return "redirect:/index"; // Questão 10
    }

    @GetMapping("/restaurar")
    public String restaurar(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        camisetas camiseta = service.buscarPorId(id);
        // Questão 10: Restaurar (isDeleted = null)
        camiseta.setIsDeleted(null); 
        service.salvar(camiseta);
        redirectAttributes.addFlashAttribute("mensagem", "Camiseta restaurada com sucesso!");
        return "redirect:/index"; // Questão 10
    }

    @GetMapping("/adicionarCarrinho")
    public String adicionarCarrinho(@RequestParam Long id, HttpSession session) {
        // Questão 11: Adicionar objeto na Sessão HTTP no atributo "carrinho"
        camisetas camiseta = service.buscarPorId(id);
        List<camisetas> carrinho = (List<camisetas>) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new ArrayList<>();
        }
        carrinho.add(camiseta);
        session.setAttribute("carrinho", carrinho); 
        return "redirect:/index"; // Questão 11
    }

    @GetMapping("/verCarrinho")
    public String verCarrinho(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        // Questão 12: Listar itens do "carrinho" da Sessão
        List<camisetas> carrinho = (List<camisetas>) session.getAttribute("carrinho");
        if (carrinho == null || carrinho.isEmpty()) {
            // Redireciona com mensagem se vazio
            redirectAttributes.addFlashAttribute("mensagem", "Não existem itens no seu carrinho de compras.");
            return "redirect:/index"; 
        }

        BigDecimal total = BigDecimal.ZERO;
        for (camisetas c : carrinho) {
            if (c.getPreco() != null) {
                total = total.add(c.getPreco());
            }
        }

        model.addAttribute("total", total);
        return "carrinho"; // Questão 12
    }

    @GetMapping("/finalizarCompra")
    public String finalizarCompra(HttpSession session, RedirectAttributes redirectAttributes) {
        // Questão 13: Invalidar Sessão
        session.invalidate(); 
        redirectAttributes.addFlashAttribute("mensagem", "Compra finalizada com sucesso!");
        return "redirect:/index"; // Questão 13
    }
}

    