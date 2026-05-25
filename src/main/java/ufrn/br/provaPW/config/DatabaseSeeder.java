package ufrn.br.provaPW.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ufrn.br.provaPW.model.camisetas;
import ufrn.br.provaPW.repository.camisetasRepository;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final camisetasRepository repository;

    public DatabaseSeeder(camisetasRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            camisetas c1 = new camisetas();
            c1.setNome("Camiseta Pima Premium");
            c1.setMarca("APEX Shirts");
            c1.setDescricao("Confeccionada em algodão Pima premium com toque ultra macio e modelagem clássica estruturada.");
            c1.setPreco(new BigDecimal("179.90"));
            c1.setTamanho("M");
            c1.setImgUrl("/images/camisa-ov1.png");
            c1.setCodigoSku("APX-1001");
            c1.setIsDeleted(null);

            camisetas c2 = new camisetas();
            c2.setNome("Camiseta Heavyweight Streetwear");
            c2.setMarca("APEX Shirts");
            c2.setDescricao("Algodão de alta gramatura com caimento reto perfeito para um estilo streetwear autêntico.");
            c2.setPreco(new BigDecimal("219.90"));
            c2.setTamanho("G");
            c2.setImgUrl("/images/camisa-ov2.png");
            c2.setCodigoSku("APX-1002");
            c2.setIsDeleted(null);

            camisetas c3 = new camisetas();
            c3.setNome("Camiseta Oversized Acid Wash");
            c3.setMarca("APEX Shirts");
            c3.setDescricao("Estilo urbano moderno com lavagem ácida exclusiva, garantindo maciez e caimento despojado.");
            c3.setPreco(new BigDecimal("249.90"));
            c3.setTamanho("GG");
            c3.setImgUrl("/images/camisa-ov3.png");
            c3.setCodigoSku("APX-1003");
            c3.setIsDeleted(null);

            camisetas c4 = new camisetas();
            c4.setNome("Camiseta Oversized Vintage Black");
            c4.setMarca("APEX Shirts");
            c4.setDescricao("Modelagem ampla premium inspirada no design clássico dos anos 90 com algodão sustentável.");
            c4.setPreco(new BigDecimal("199.90"));
            c4.setTamanho("M");
            c4.setImgUrl("/images/camisa-ov4.png");
            c4.setCodigoSku("APX-1004");
            c4.setIsDeleted(null);

            camisetas c5 = new camisetas();
            c5.setNome("Camiseta Oversized Off-White");
            c5.setMarca("APEX Shirts");
            c5.setDescricao("Cor off-white versátil e corte quadrado contemporâneo, ideal para composição de looks casuais.");
            c5.setPreco(new BigDecimal("189.90"));
            c5.setTamanho("P");
            c5.setImgUrl("/images/camisa-ov5.png");
            c5.setCodigoSku("APX-1005");
            c5.setIsDeleted(null);

            camisetas c6 = new camisetas();
            c6.setNome("Camiseta Classic Estampada");
            c6.setMarca("APEX Shirts");
            c6.setDescricao("Camiseta com estampa minimalista nas costas e logo sutil no peito.");
            c6.setPreco(new BigDecimal("150.00"));
            c6.setTamanho("P");
            c6.setImgUrl("/images/camisa-ov1.png");
            c6.setCodigoSku("APX-1006");
            // Salvando uma camiseta deletada logicamente para verificação da tela admin
            c6.setIsDeleted(System.currentTimeMillis());

            repository.save(c1);
            repository.save(c2);
            repository.save(c3);
            repository.save(c4);
            repository.save(c5);
            repository.save(c6);

            System.out.println("Banco de dados populado com camisetas iniciais com sucesso!");
        }
    }
}
