package dev.java.CadastroDeNinjas.Ninjas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    @Autowired
    private NinjaService ninjaService;

    //Adicionar Ninja
    @PostMapping("/adicionar")
    public NinjaModel adicionarNinja( @RequestBody NinjaModel ninja) {
        return ninjaService.adicionarNinja(ninja);
    }

    //Procurar Ninja por ID
    @GetMapping("/{id}")
    //Optional: usado para indicar que o metodo pode ou não retornar um valor
    public Optional<NinjaModel> buscarNinjaPorId(@PathVariable Long id) {
        return ninjaService.buscarNinjaPorId(id);
    }

    //Mostrar todos os ninjas
    @GetMapping("/mostrar")
    public List<NinjaModel> mostrarNinjas() {
        return ninjaService.mostrarNinjas();
    }

    //Atualizar dados dos Ninjas
    @PutMapping("/{id}")
    public NinjaModel atualizarNinja(@PathVariable Long id, @RequestBody NinjaModel ninja) {
        return ninjaService.atualizarNinja(id, ninja);
    }

    //Deletar Ninja
    @DeleteMapping("/{id}")
    public void deletarNinja(@PathVariable Long id) {
        ninjaService.deletarNinja(id);
    }
}
