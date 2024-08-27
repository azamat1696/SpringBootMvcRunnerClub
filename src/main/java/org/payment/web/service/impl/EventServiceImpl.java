package org.payment.web.service.impl;

import org.payment.web.dto.EventDto;
import org.payment.web.models.Club;
import org.payment.web.models.Event;
import org.payment.web.repository.ClubRepository;
import org.payment.web.repository.EventRepository;
import org.payment.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.payment.web.mapper.EventMapper.mapToEvent;
import static org.payment.web.mapper.EventMapper.mapToEventDto;

@Service
public class EventServiceImpl implements EventService {
    private EventRepository _eventRepository;
    private ClubRepository _clubRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository,ClubRepository clubRepository){
         this._clubRepository = clubRepository;
         this._eventRepository = eventRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = _clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        _eventRepository.save(event);
    }


    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = _eventRepository.findAll();
        return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public EventDto findByEventId(Long eventId) {
        Event event = _eventRepository.findById(eventId).get();
        return mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        _eventRepository.save(event);
    }

    @Override
    public void deleteEvent(long eventId) {
        _eventRepository.deleteById(eventId);
    }
}