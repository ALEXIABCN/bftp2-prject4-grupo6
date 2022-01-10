package org.factoriaf5.bftp2project4grupo6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.factoriaf5.bftp2project4grupo6.repositories.Game;
import org.factoriaf5.bftp2project4grupo6.repositories.GameRepository;

import java.util.List;

@Controller
public class GameController {

    private GameRepository gameRepository;

    @Autowired
    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/games")
    String listGames(Model model) {
        List<Game> games = (List<Game>) gameRepository.findAll();
        model.addAttribute("title", "Game list");
        model.addAttribute("games", games);
        return "games/all";
    }
    @GetMapping("/games/new")
    String getForm(Model model) {
        return "games/new";
    }
}