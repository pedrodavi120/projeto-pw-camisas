package ufrn.br.provaPW.controller;

import ufrn.br.provaPW.model.camisetas;
import ufrn.br.provaPW.repository.camisetasRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Arrays;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import ufrn.br.provaPW.dto.camisetaDTO;

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
        int quantidadeCarrinho = (carrinho == null) ? 0 : carrinho.size();
        model.addAttribute("quantidadeCarrinho", quantidadeCarrinho);
        model.addAttribute("camisetas", camisetasList);
        return "camisetas"; // Retorna o nome da view (camisetas.html)
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<camisetas> camisetasList = repository.findAll();
        model.addAttribute("camisetas", camisetasList);
        return "admin"; // Retorna o nome da view (admin.html)
    }

    @GetMapping("/cadastro")
    public String cadastrar(Model model) {
        model.addAttribute("camisetaDto", new camisetaDTO());
        return "cadastro"; // Retorna o nome da view (cadastro.html)
    }

    @GetMapping("/editar")
    public String editar (@RequestParam Long id, Model model){
        camisetas camiseta = repository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        camisetaDTO camisetaDto = new camisetaDTO();
        camisetaDto.setId(camiseta.getId());
        camisetaDto.setNome(camiseta.getNome());
        camisetaDto.setMarca(camiseta.getMarca());
        camisetaDto.setDescricao(camiseta.getDescricao());
        camisetaDto.setPreco(camiseta.getPreco());
        camisetaDto.setTamanho(camiseta.getTamanho());
        camisetaDto.setCodigo(camiseta.getCodigo());
        model.addAttribute("camisetaDto", camisetaDto);

        return "cadastro"; // Retorna o nome da view (cadastro.html)
    }
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("camisetaDto") camisetaDTO camisetaDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "cadastro"; // Retorna o nome da view (cadastro.html) com mensagens de erro
        }

        camisetas camiseta;
        if (camisetaDto.getId()== null){
            camiseta = new camisetas();
            List<String> imagens = Arrays.asList("images/camisa-ov1.png", "images/camisa-ov2.png", "images/camisa-ov3.png");
            String imagemUrl = imagens.get(new Random().nextInt(imagens.size()));
            camiseta.setImagemUrl(imagemUrl);
        }else {
            camiseta = repository.findById(camisetaDto.getId()).orElseThrow(() -> new IllegalArgumentException());
        }
        camiseta.setNome(camisetaDto.getNome());
        camiseta.setMarca(camisetaDto.getMarca());
        camiseta.setDescricao(camisetaDto.getDescricao());
        camiseta.setPreco(camisetaDto.getPreco());
        camiseta.setTamanho(camisetaDto.getTamanho());
        camiseta.setCodigo(camisetaDto.getCodigo());
        repository.save(camiseta);
        redirectAttributes.addFlashAttribute("mensagem", "Camiseta salva com sucesso!");
        return "redirect:/admin"; // Redireciona para a página de administração
    }
}
    