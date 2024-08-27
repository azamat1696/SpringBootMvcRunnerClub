package org.payment.web.controller;

import jakarta.validation.Valid;
import org.payment.web.dto.ClubDto;
import org.payment.web.models.Club;
import org.payment.web.models.UserEntity;
import org.payment.web.security.SecurityUtil;
import org.payment.web.service.ClubService;
import org.payment.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;
    private UserService userService;

    @Autowired
    public ClubController(ClubService clubService,UserService userService) {
        this.clubService = clubService;
        this.userService = userService;
    }

    @GetMapping({"/clubs", "/clubs/index"})
    public String listClubs(Model model) {
        UserEntity user = new UserEntity();
        List<ClubDto> clubs = clubService.findAllClubs();
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByEmail(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("clubs", clubs);
        return "clubs/index";
    }
    @GetMapping("/clubs/new")
    public String createClubForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
       return "clubs/create";
    }
    @PostMapping("/clubs/new")
    public String createClub(@Valid @ModelAttribute("club") ClubDto clubDto,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("club", clubDto);
            return "clubs/create";
        }
        clubService.saveClub(clubDto);
        return "redirect:/clubs/index";
    }
    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId") long clubId, Model model) {
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);
        return "clubs/detail";
    }
    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") long clubId, Model model) {
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);
        return "clubs/edit";
    }
    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") long clubId,
                             @Valid @ModelAttribute("club") ClubDto clubDto,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            model.addAttribute("club", clubDto);
            return "clubs/edit";
        }
        clubDto.setId(clubId);
        clubService.updateClub(clubDto);
        return "redirect:/clubs/index";
    }
    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") long clubId) {
        clubService.deleteClub(clubId);
        return "redirect:/clubs/index";
    }
    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query") String query, Model model) {
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);
        return "clubs/index";
    }
}
