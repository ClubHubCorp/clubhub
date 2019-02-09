package bg.sofia.fmi.uni.clubhub.service;

import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toEntity;
import static bg.sofia.fmi.uni.clubhub.convertion.DataConverter.toModel;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.sofia.fmi.uni.clubhub.convertion.DataConverter;
import bg.sofia.fmi.uni.clubhub.entity.ClubEntity;
import bg.sofia.fmi.uni.clubhub.entity.DiscountEntity;
import bg.sofia.fmi.uni.clubhub.model.Discount;
import bg.sofia.fmi.uni.clubhub.repository.ClubRepository;
import bg.sofia.fmi.uni.clubhub.repository.DiscountRepository;

@Service
public class DiscountService implements IDiscountService {

    private final DiscountRepository discountRepository;
    private final ClubRepository clubRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository, ClubRepository clubRepository) {
        this.discountRepository = discountRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    @Transactional
    public Discount createNew(Discount discount) {
        Optional<ClubEntity> club = clubRepository.findById(discount.getClubId());
        if (!club.isPresent()) {
            throw new RuntimeException("No such club found");
        }

        DiscountEntity entity = toEntity(discount);
        entity.setId(UUID.randomUUID());
        entity.setClub(club.get());

        return toModel(discountRepository.save(entity));
    }

    @Override
    public List<Discount> getAllDiscountForClub(UUID clubId) {
        return discountRepository.findAllByClubId(clubId).stream() //
                .map(DataConverter::toModel) //
                .filter(d -> d.getStartDate().before(new Date())) //
                .filter(d -> d.getEndDate().after(new Date())) //
                .collect(Collectors.toList());
    }

    @Override
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll().stream() //
                .map(DataConverter::toModel) //
                .filter(d -> d.getStartDate().before(new Date())) //
                .filter(d -> d.getEndDate().after(new Date())) //
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(UUID clubId, UUID discountId) {
        discountRepository.deleteByIdAndClubId(discountId, clubId);
    }
}
