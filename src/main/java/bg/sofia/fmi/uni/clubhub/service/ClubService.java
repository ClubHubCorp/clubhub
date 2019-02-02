package bg.sofia.fmi.uni.clubhub.service;


import bg.sofia.fmi.uni.clubhub.convertion.DataConverter;
import bg.sofia.fmi.uni.clubhub.entity.ClubEntity;
import bg.sofia.fmi.uni.clubhub.model.Club;
import bg.sofia.fmi.uni.clubhub.model.Customer;
import bg.sofia.fmi.uni.clubhub.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.xml.crypto.Data;
import java.util.Optional;
import java.util.UUID;

import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toModel;


@Service
public class ClubService implements IClubService{

    private ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }


    @Override
    public Optional<Club> findByID(UUID id) {
        return clubRepository.findById(id).map(DataConverter::toModel);
    }

    @Override
    public Club createNew(Club club) {
        ClubEntity  clubEntity = DataConverter.toEntity(club);
        clubEntity.setId(UUID.randomUUID());

        return toModel(clubRepository.save(clubEntity));
    }

    @Override
    public void deleteById(UUID id) {
        clubRepository.deleteById(id);
    }
}
