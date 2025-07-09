package dev.java.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    //Adicionar Ninja
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.adicionarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED).body("ninja criado com sucesso: " + novoNinja.getNome());
    }

    //Procurar Ninja por ID
    @GetMapping("/{id}")
    //Optional: usado para indicar que o metodo pode ou não retornar um valor
    public ResponseEntity<?> buscarNinjaPorId(@PathVariable Long id) {

        NinjaDTO ninja = ninjaService.buscarNinjaPorId(id);
        if(ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com o id " + id + " não existe em nossos registros.");
        }
    }

    //Mostrar todos os ninjas
    @GetMapping("/mostrar")
    public ResponseEntity<List<NinjaDTO>> mostrarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.mostrarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    //Atualizar dados dos Ninjas
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninja) {
        NinjaDTO ninjaDTO = ninjaService.atualizarNinja(id, ninja);
        if(ninjaDTO != null) {
            return ResponseEntity.ok(ninjaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com o id " + id + " não existe em nossos registros.");
        }
    }

    //Deletar Ninja
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarNinja(@PathVariable Long id) {
        if(ninjaService.buscarNinjaPorId(id) != null) {
            ninjaService.deletarNinja(id);
            return ResponseEntity.ok("Ninja com o id " + id + " deletado com sucesso.");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ninja com o id " + id + " não foi encontrado.");
        }
    }
}
