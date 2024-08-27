package org.payment.web.mapper;

import org.payment.web.dto.ClubDto;
import org.payment.web.models.Club;

import java.util.stream.Collectors;

import static org.payment.web.mapper.EventMapper.mapToEventDto;

public class ClubMapper {

    public static ClubDto mapToClubDto(Club club){
        ClubDto clubDto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .updatedOn(club.getUpdatedOn())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .photoUrl(club.getPhotoUrl())
                .createdBy(club.getCreatedBy())
                .eventDtoList(club.getEvents().stream().map((event) -> mapToEventDto(event)).collect(Collectors.toList()))
                .build();
        return clubDto;
    }
    public static Club mapToClub(ClubDto clubDto){
        Club club = Club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .updatedOn(clubDto.getUpdatedOn())
                .content(clubDto.getContent())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .photoUrl(clubDto.getPhotoUrl())
                .build();
        return club;
    }
}
