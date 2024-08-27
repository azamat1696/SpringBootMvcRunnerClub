package org.payment.web.service;

import org.payment.web.dto.ClubDto;
import org.payment.web.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    Club saveClub(ClubDto clubDto);
    ClubDto findClubById(long clubId);
    void updateClub(ClubDto clubDto);
    void deleteClub(long clubId);
    List<ClubDto> searchClubs(String query);
}
