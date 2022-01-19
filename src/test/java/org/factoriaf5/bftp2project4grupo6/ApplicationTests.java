package org.factoriaf5.bftp2project4grupo6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
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

    @Autowired
    MockMvc mockMvc;

    @Autowired
    GameRepository gameRepository;

    @BeforeEach
    void setUp() {
        gameRepository.deleteAll();
    }


    @Test
    @WithMockUser
    void loadsTheHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    @WithMockUser
    void returnsTheExistingGames() throws Exception {

        Game game = gameRepository.save(new Game("Call of duty", "19,99","Accion"));

        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(view().name("games/all"))
                .andExpect(model().attribute("games", hasItem(game)));
    }


    @Test
    @WithMockUser
    void returnsAFormToAddNewGame() throws Exception {
        mockMvc.perform(get("/games/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("games/edit"))
                .andExpect(model().attributeExists("game"))
                .andExpect(model().attribute("title", "Create new game"));
    }
    @Test
    @WithMockUser
    void returnsAFormToEditGames() throws Exception {
        Game game = gameRepository.save(new Game("Nintendog", "19.99", "sports"));
        mockMvc.perform(get("/games/edit/" + game.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("games/edit"))
                .andExpect(model().attribute("game", game))
                .andExpect(model().attribute("title", "Edit game"));
    }
    @Test
    @WithMockUser
    void allowsToDeleteAGame() throws Exception {
        Game game = gameRepository.save(new Game("Nintendog", "19,99", "sports"));
        mockMvc.perform(get("/games/delete/" + game.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/games"));

        assertThat(gameRepository.findById(game.getId()), equalTo(Optional.empty()));
    }

    @Test
    @WithMockUser
    void anonymousUsersCannotCreateAGame() throws Exception {
        mockMvc.perform(post("/games/new")
                        .param("title", "Nintendog")
                        .param("price", "19,99")
                        .param("category", "sports")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser
    void anonymousUsersCannotEditAGame() throws Exception {
        mockMvc.perform(get("/games/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser
    void anonymousUsersCannotDeleteAGame() throws Exception {
        mockMvc.perform(get("/games/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}
