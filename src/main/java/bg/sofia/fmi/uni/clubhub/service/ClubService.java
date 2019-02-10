package bg.sofia.fmi.uni.clubhub.service;


import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bg.sofia.fmi.uni.clubhub.convertion.DataConverter;
import bg.sofia.fmi.uni.clubhub.entity.ClubEntity;
import bg.sofia.fmi.uni.clubhub.model.Club;
import bg.sofia.fmi.uni.clubhub.repository.ClubRepository;


@Service
public class ClubService implements IClubService{

    private final ClubRepository clubRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClubService(ClubRepository clubRepository, PasswordEncoder passwordEncoder) {
        this.clubRepository = clubRepository;
        this.passwordEncoder = passwordEncoder;
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
        clubEntity.setPassword(passwordEncoder.encode(clubEntity.getPassword()));

        return toModel(clubRepository.save(clubEntity));
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        clubRepository.deleteById(id);
    }

    @Override
    public List<Club> getAllClubs() {
        return clubRepository.findAll().stream().map(DataConverter::toModel).collect(Collectors.toList());
    }
}
