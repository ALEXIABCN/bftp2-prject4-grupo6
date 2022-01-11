package org.factoriaf5.bftp2project4grupo6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.factoriaf5.bftp2project4grupo6.repositories.Game;
import org.factoriaf5.bftp2project4grupo6.repositories.GameRepository;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    void returnsAFormToAddNewGames() throws Exception {
        mockMvc.perform(get("/games/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("games/new"));
    }

    @Test
    void allowsToCreateANewGame() throws Exception {
        mockMvc.perform(post("/game/new")
                        .param("title", "Wii Sports")
                        .param("price", "19,99")
                        .param("category", "sports")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/games"))
        ;

        List<Game> existingGames = (List<Game>) gameRepository.findAll();
        assertThat(existingGames, contains(allOf(
                hasProperty("title", equalTo("Wii Sports")),
                hasProperty("price", equalTo("19,99")),
                hasProperty("category", equalTo("sports"))
        )));
    }
}
