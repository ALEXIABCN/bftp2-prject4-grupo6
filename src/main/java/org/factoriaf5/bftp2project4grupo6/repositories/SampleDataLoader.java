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
                new Game("Wii Sports", "19,99", "Sports"),
                new Game("Super Mario Bros.", "14,99", "Platform"),
                new Game("Mario Kart Wii", "9,99", "Racing"),
                new Game("Wii Sports Resort", "9,99", "Sports"),
                new Game("Pokemon Red/Pokemon Blue", "29,99", "Role-Playing"),
                new Game("Tetris", "4,99", "Puzzle")
        ));
    }
}

