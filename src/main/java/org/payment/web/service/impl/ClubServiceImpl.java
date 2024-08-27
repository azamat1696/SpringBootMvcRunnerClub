package org.payment.web.service.impl;

import org.payment.web.dto.ClubDto;
import org.payment.web.models.Club;
import org.payment.web.models.UserEntity;
import org.payment.web.repository.ClubRepository;
import org.payment.web.repository.UserRepository;
import org.payment.web.security.SecurityUtil;
import org.payment.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.payment.web.mapper.ClubMapper.mapToClub;
import static org.payment.web.mapper.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {

    private ClubRepository clubRepository;
    private UserRepository userRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository,UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }
    @Override
    public Club saveClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByEmail(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        return clubRepository.save(club);
    }
    @Override
    public ClubDto findClubById(long clubId) {
        Club club = clubRepository.findById(clubId).get();
        return mapToClubDto(club);
    }
    @Override
    public void updateClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByEmail(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }
    @Override
    public void deleteClub(long clubId) {
        clubRepository.deleteById(clubId);
    }
    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }

}
