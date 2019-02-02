package bg.sofia.fmi.uni.clubhub.service;

import bg.sofia.fmi.uni.clubhub.entity.ClubEntity;
import bg.sofia.fmi.uni.clubhub.model.Club;
import bg.sofia.fmi.uni.clubhub.model.Customer;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface IClubService {

    Optional<Club> findByID(UUID id);

    Club createNew(Club customer);

    void deleteById(UUID id);
}