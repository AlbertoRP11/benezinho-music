package br.com.fiap.domain.view;

import br.com.fiap.domain.entity.Artista;
import br.com.fiap.domain.entity.Estilo;
import br.com.fiap.domain.entity.Musica;
import br.com.fiap.domain.repository.ArtistaRepository;
import br.com.fiap.domain.repository.EstiloRepository;
import br.com.fiap.domain.repository.MusicaRepository;
import br.com.fiap.domain.service.MusicaService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MusicaView {

    List<Artista> artistas = new ArrayList<>();
    List<Estilo> estilos = new ArrayList<>();

    private MusicaService service = new MusicaService();

    public void menu(){
        int opcao;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog( "Escolha uma opção: \n" +
                    "[1] - Adicionar uma música \n" +
                    "[2] - Procurar música pelo nome \n" +
                    "[3] - Procurar música pelo id \n" +
                    "[4] - Encerrar programa"));
            switch(opcao){
                case 1 -> {
                    Musica musica = addMusica();
                    JOptionPane.showMessageDialog( null, musica );}
                case 2 -> {
                    String nome = JOptionPane.showInputDialog( "Nome da musica: " );
                    List<Musica> list = service.findByName( nome );
                    list.forEach( m -> {
                        JOptionPane.showMessageDialog( null, m );
                    } );}

                case 3 -> {
                    Long id = Long.valueOf(JOptionPane.showInputDialog( "Id da musica: " ));
                    Musica m = service.findById( id );
                    JOptionPane.showMessageDialog( null, m );
                }
                case 4 -> {}
                default -> {
                    System.out.println("Selecione uma opção válida!");
                }
            }
        } while (opcao!=4);
    }

    public Musica addMusica() {

        Musica m = null;

        ArtistaRepository artistaRepository = new ArtistaRepository();
        EstiloRepository estiloRepository = new EstiloRepository();

        artistas = artistaRepository.findAll();
        estilos = estiloRepository.findAll();

        if (artistas.size()>0 && estilos.size()>0){
            Artista artista = (Artista) JOptionPane.showInputDialog(null, "Escolha o artista", "Seleção de Artista", JOptionPane.QUESTION_MESSAGE, null, artistas.toArray(), artistas.get(0));
            if (Objects.isNull(artista)) return m;
            Estilo estilo = (Estilo) JOptionPane.showInputDialog(null, "Escolha o estilo", "Seleção de Estilo", JOptionPane.QUESTION_MESSAGE, null, estilos.toArray(), estilos.get(0));
            if (Objects.isNull(artista)) return m;

            String musica = "";
            do {
                musica = (JOptionPane.showInputDialog("Informe o nome da musica:"));
            } while (musica == "");

            m = new Musica();
            m.addArtista(artista).setEstilo(estilo).setNome(musica);
            Musica persist = service.persist(m);

            if (Objects.nonNull( persist ))
                JOptionPane.showMessageDialog(null, "Sucesso sua música foi salva!");
            else JOptionPane.showMessageDialog(null, "Não foi possível salvar a música!");
        }

        return m;
    }

    public List<Musica> findByName(String nome){return service.findByName(nome);}
}
