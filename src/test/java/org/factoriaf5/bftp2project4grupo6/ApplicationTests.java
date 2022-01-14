package org.factoriaf5.bftp2project4grupo6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.factoriaf5.bftp2project4grupo6.repositories.Game;
import org.factoriaf5.bftp2project4grupo6.repositories.GameRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

    @BeforeEach
    void setUp() {
        gameRepository.deleteAll();
    }

    @Autowired
    MockMvc mockMvc;

    @Test
    void loadsTheHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }
    @Autowired
    GameRepository gameRepository;

    @Test
    void returnsTheExistingGames() throws Exception {

        Game game = gameRepository.save(new Game("Call of duty", "19,99","Accion"));

        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(view().name("games/all"))
                .andExpect(model().attribute("games", hasItem(game)));
    }


    @Test
    void returnsAFormToAddNewGame() throws Exception {
        mockMvc.perform(get("/games/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("games/edit"))
                .andExpect(model().attributeExists("game"))
                .andExpect(model().attribute("title", "Create new game"));
    }
    @Test
    void returnsAFormToEditGames() throws Exception {
        Game game = gameRepository.save(new Game("Nintendog", "19.99", "sports"));
        mockMvc.perform(get("/games/edit/" + game.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("games/edit"))
                .andExpect(model().attribute("game", game))
                .andExpect(model().attribute("title", "Edit game"));
    }
    @Test
    void allowsToDeleteAGame() throws Exception {
        Game game = gameRepository.save(new Game("Nintendog", "19,99", "sports"));
        mockMvc.perform(get("/games/delete/" + game.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/games"));

        assertThat(gameRepository.findById(game.getId()), equalTo(Optional.empty()));
    }
}
