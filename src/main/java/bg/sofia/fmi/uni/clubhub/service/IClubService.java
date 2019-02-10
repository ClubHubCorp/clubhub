package bg.sofia.fmi.uni.clubhub.service;

import bg.sofia.fmi.uni.clubhub.model.Club;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IClubService {

    Optional<Club> findByID(UUID id);

    Club createNew(Club customer);

    void deleteById(UUID id);

    List<Club> getAllClubs();
}