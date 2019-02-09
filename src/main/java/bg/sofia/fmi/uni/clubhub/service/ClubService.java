package bg.sofia.fmi.uni.clubhub.service;


import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toModel;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.sofia.fmi.uni.clubhub.convertion.DataConverter;
import bg.sofia.fmi.uni.clubhub.entity.ClubEntity;
import bg.sofia.fmi.uni.clubhub.model.Club;
import bg.sofia.fmi.uni.clubhub.repository.ClubRepository;


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
    @Transactional
    public Club createNew(Club club) {
        ClubEntity  clubEntity = DataConverter.toEntity(club);
        clubEntity.setId(UUID.randomUUID());

        return toModel(clubRepository.save(clubEntity));
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        clubRepository.deleteById(id);
    }
}
