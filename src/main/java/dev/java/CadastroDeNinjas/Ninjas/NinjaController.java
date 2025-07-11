package dev.java.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    @Autowired
    private NinjaService ninjaService;

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }

    //Adicionar Ninja
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criaçao do ninja")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED).body("ninja criado com sucesso: " + novoNinja.getNome());
    }

    //Procurar Ninja por ID
    @GetMapping("/{id}")
    //Optional: usado para indicar que o metodo pode ou não retornar um valor
    public ResponseEntity<?> buscarNinjaPorId(@PathVariable Long id) {

        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if(ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com o id " + id + " não existe em nossos registros.");
        }
    }

    //Listar todos os ninjas
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista o ninja por Id", description = "Rota lista um ninja pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja nao encontrado")
    })
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id) {
        NinjaDTO ninja =  ninjaService.listarNinjasPorId(id);
        if (ninja !=null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o id: " + id + " nao existe nos nossos registros");
        }
    }

    //Atualizar dados dos Ninjas
    @PutMapping("/{id}")
    @Operation(summary = "Altera o ninja por Id", description = "Rota altera um ninja pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja nao encontrado, nao foi possivel alterar")
    })
    public ResponseEntity<?> atualizarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninja) {
        NinjaDTO ninjaDTO = ninjaService.atualizarNinja(id, ninja);
        if(ninjaDTO != null) {
            return ResponseEntity.ok(ninjaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com o id " + id + " não existe em nossos registros.");
        }
    }

    //Deletar Ninja
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id) {
        if(ninjaService.listarNinjasPorId(id) != null) {
            ninjaService.deletarNinjasPorId(id);
            return ResponseEntity.ok("Ninja com o id " + id + " deletado com sucesso.");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ninja com o id " + id + " não foi encontrado.");
        }
    }
}
