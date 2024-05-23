package org.example.friendexam.controller;

import lombok.RequiredArgsConstructor;
import org.example.friendexam.domain.Friend;
import org.example.friendexam.service.FriendService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/friends")
public class FriendController {
    private final FriendService friendService;

    // 친구 목록 조회 (페이징 처리 X)
//    @GetMapping
//    public String friends(Model model) {
//        Iterable<Friend> friends = friendService.findAllFriends();
//        model.addAttribute("friends", friends);
//        return "friend/list";
//    }

    // 친구 목록 조회 (페이징 O)
    @GetMapping
    public String friends(Model model,
                          @RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page-1, size);   // page 0부터 시작
        Iterable<Friend> friends = friendService.findAllFriends(pageable);
        model.addAttribute("friends", friends);
        model.addAttribute("currentPage", page);    // 현재 페이지 값
        return "friend/list";
    }

    // 친구 등록 form
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("friend", new Friend());
        return "friend/form";
    }

    // 친구 등록
    @PostMapping("/add")
    public String addFriend(@ModelAttribute Friend friend, RedirectAttributes redirectAttributes) {
        friendService.saveFriend(friend);
        redirectAttributes.addAttribute("message", "친구 등륵 완료");
        return "redirect:/friends";
    }

    // 상세 페이지
    @GetMapping("/{id}")
    public String detailFriend(@PathVariable Long id, Model model) {
        Friend friend = friendService.findFriendById(id);
        model.addAttribute("friend", friend);
        return "friend/detail";
    }

    // 친구 수정 form
    @GetMapping("edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Friend friend = friendService.findFriendById(id);
        model.addAttribute("friend", friend);
        return "friend/edit";
    }

    // 친구 수정
    @PostMapping("edit")
    public String updateFriend(@ModelAttribute Friend friend) {
        friendService.saveFriend(friend);
        return "redirect:/friends";
    }

    // 친구 삭제
    @GetMapping("delete/{id}")
    public String deleteFriend(@PathVariable Long id) {
        friendService.deleteFriendById(id);
        return "redirect:/friends";
    }
}
