package dev.java.CadastroDeNinjas.Ninjas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    @Autowired
    private NinjaRepository ninjaRepository;

    @Autowired
    private NinjaMapper ninjaMapper;

    public NinjaDTO adicionarNinja(NinjaDTO ninjaDTO) {
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    public Optional<NinjaModel> buscarNinjaPorId(Long id) {
        return ninjaRepository.findById(id);
    }

    public List<NinjaModel> mostrarNinjas() {
        return ninjaRepository.findAll();
    }

    public NinjaModel atualizarNinja(Long id, NinjaModel atualizado) {
        if(!ninjaRepository.existsById(id)) {
            System.out.println("Ninja não encontrado.");
        }else {
            atualizado.setId(id);
        }
        return ninjaRepository.save(atualizado);
    }

    public void deletarNinja(Long id) {
        if(!ninjaRepository.existsById(id)) {
            System.out.println("Ninja não encontrado.");
        }else {
            ninjaRepository.deleteById(id);
        }
    }
}
