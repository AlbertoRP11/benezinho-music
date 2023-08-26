package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Artista;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArtistaRepository implements Repository<Artista, Long>{

    private static List<Artista> artistas;

    static{
        artistas = new ArrayList<>();

        Artista matue = new Artista(1L, "Matuê");

        Artista postMalone = new Artista(2L, "Post Malone");

        Artista kayblack = new Artista(3L, "Kayblack");

        artistas.addAll(Arrays.asList(matue, postMalone));

    }

    @Override
    public List<Artista> findAll() {
        return artistas;
    }

    @Override
    public Artista findById(Long id) {
        for(int i=0; i<artistas.size(); i++){
            if (artistas.get(i).getId().equals(id)){
                return artistas.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Artista> findByName(String texto) {
        return artistas.stream().filter( a -> a.getNome().equalsIgnoreCase( texto ) ).toList();
    }

    @Override
    public Artista persist(Artista artista) {
        artista.setId(artistas.size()+1L);
        artistas.add(artista);
        return artista;
    }
}
