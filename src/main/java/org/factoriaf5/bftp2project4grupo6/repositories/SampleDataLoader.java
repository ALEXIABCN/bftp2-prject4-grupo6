package org.factoriaf5.bftp2project4grupo6.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class SampleDataLoader {

    private GameRepository gameRepository;

    @Autowired
    public SampleDataLoader(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @PostConstruct
    public void loadSampleData() {
        gameRepository.saveAll(List.of(
                new Game("Wii Sports", "19,99", "Sports", "https://media.vandal.net/ivandal/1200x630/5727/200612933043_1.jpg"),
                new Game("Super Mario Bros.", "14,99", "Platform", "https://www.nintenderos.com/wp-content/uploads/2009/09/NSMB_Wii.jpg"),
                new Game("Mario Kart Wii", "9,99", "Racing", "https://i.pinimg.com/originals/9c/19/76/9c1976ae0d1370ad3b1513351aa12d92.jpg"),
                new Game("Wii Sports Resort", "9,99", "Sports", "https://uvejuegos.com/img/caratulas/31529/wii_sports_resort.jpg"),
                new Game("Pokemon Red/Pokemon Blue", "29,99", "Role-Playing", "https://romsjuegos.com/wp-content/uploads/pokemon-red-and-blue-version-gameboy-cover.png"),
                new Game("Tetris", "4,99", "Puzzle", "https://uvejuegos.com/img/caratulas/53374/297584-tetris-ultimate-xbox-one-front-cover.png")
        ));
    }
}

