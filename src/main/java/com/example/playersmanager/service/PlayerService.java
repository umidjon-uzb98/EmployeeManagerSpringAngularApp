package com.example.playersmanager.service;

import com.example.playersmanager.exception.UserNotFoundException;
import com.example.playersmanager.model.Player;
import com.example.playersmanager.repo.PlayerRepo;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PlayerService {
    private final PlayerRepo playerRepo;

    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public Player addPlayer(Player player) {
        player.setPlayerCode(UUID.randomUUID().toString());
        return playerRepo.save(player);
    }

    public List<Player> findAllPlayer() {
        return playerRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Player updatePlayer(Player player) {
        return playerRepo.save(player);
    }

    public Player findPlayerById(Long id) {
        return playerRepo.findById(id)
                .orElseThrow(()->new UserNotFoundException("PLayer by id "+id+" was not found"));
    }

    public void deletePlayer(Long id) {
        playerRepo.deletePlayerById(id);
    }


}
