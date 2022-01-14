package org.factoriaf5.bftp2project4grupo6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.factoriaf5.bftp2project4grupo6.repositories.Game;
import org.factoriaf5.bftp2project4grupo6.repositories.GameRepository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/games/edit/{id}")
    String editGame(Model model, @PathVariable Long id) {
        Game game = gameRepository.findById(id).get();
        model.addAttribute("game", game);
        model.addAttribute("title", "Edit game");
        return "games/edit";
    }

    @PostMapping("/games/new")
    String addGame(@ModelAttribute Game game) {
        gameRepository.save(game);
        return "redirect:/games";
    }

    @GetMapping("/games/new")  //partimos de la url que es la dirección del navegador (local host...)
    String getForm(Model model) {
        Game game = new Game();
        model.addAttribute("game", game);
        model.addAttribute("title", "Create new game");
        return "games/edit";  //le pide que enseñe el html que está dentro de la carpeta games
    }
    @GetMapping("/games/delete/{id}")
    String remove(@PathVariable Long id){  //en vez de remove, podriamos llamarlo como quisieramos deleteGame...
        gameRepository.deleteById(id);
        return "redirect:/games";  //al redireccionar, pedimos que nos lleve al localhost:8080/games como en la linea 44
    }
}
