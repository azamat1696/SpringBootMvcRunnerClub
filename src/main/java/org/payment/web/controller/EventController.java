package org.payment.web.controller;

import jakarta.validation.Valid;
import org.payment.web.dto.EventDto;
import org.payment.web.models.Event;
import org.payment.web.service.ClubService;
import org.payment.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    private EventService eventService;
    private ClubService clubService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @GetMapping({"/events","/events/index"})
    public String eventList(Model model){
       List<EventDto> events = eventService.findAllEvents();
       model.addAttribute("events",events);
       return "events/index";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model){
        Event event = new Event();
        model.addAttribute("clubId",clubId);
        model.addAttribute("event",event);
        return "events/create";
    }

    @PostMapping("/events/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId,
                             @Valid @ModelAttribute("event") EventDto eventDto,
                              BindingResult result,
                              Model model){
        if (result.hasErrors()){
            model.addAttribute("event",eventDto);
            return "events/create";
        }
        eventService.createEvent(clubId,eventDto);
        return "redirect:/clubs/"+clubId;
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") Long eventId,Model model){
       EventDto event = eventService.findByEventId(eventId);
       model.addAttribute("event",event);
       return "events/edit";
    }
    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") Long eventId,
                              @Valid @ModelAttribute("event") EventDto event,
                              BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("event",event);
            return "events/edit";
        }
        EventDto eventDto = eventService.findByEventId(eventId);
        event.setId(eventId);
        event.setClub(eventDto.getClub());
        eventService.updateEvent(eventDto);
        return "redirect:/events/index";
    }
    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId,Model model){
         EventDto event = eventService.findByEventId(eventId);
         model.addAttribute("club",event.getClub());
         model.addAttribute("event",event);
        return "events/detail";
    }
    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId){
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }

}
