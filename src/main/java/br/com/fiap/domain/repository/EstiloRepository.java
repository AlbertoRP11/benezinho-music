package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Artista;
import br.com.fiap.domain.entity.Estilo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EstiloRepository implements Repository<Estilo, Long>{

    private static List<Estilo> estilos;

    static{
        estilos = new ArrayList<>();

        Estilo pop = new Estilo(1L, "Pop");

        Estilo rock = new Estilo(2L, "Rock");

        Estilo funk = new Estilo(3L, "Funk");

        estilos.addAll(Arrays.asList(pop, rock, funk));

    }
    @Override
    public List<Estilo> findAll() { return estilos;}

    @Override
    public Estilo findById(Long id) {
        for(int i=0; i<estilos.size(); i++){
            if (estilos.get(i).getId().equals(id)){
                return estilos.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Estilo> findByName(String texto) {

        return estilos.stream().filter( e -> e.getNome().equalsIgnoreCase( texto ) ).toList();
    }

    @Override
    public Estilo persist(Estilo estilo) {
        estilo.setId(estilos.size()+1L);
        estilos.add(estilo);
        return estilo;
    }
}
