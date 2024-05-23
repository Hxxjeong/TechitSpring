package org.example.friendexam.service;

import lombok.RequiredArgsConstructor;
import org.example.friendexam.domain.Friend;
import org.example.friendexam.repository.FriendRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;

    // 친구 전체 목록
    public Iterable<Friend> findAllFriends() {
        return friendRepository.findAll();
    }

    // 친구 등록
    @Transactional
    public Friend saveFriend(Friend friend) {
        // save(): id에 해당하는 값이 이미 존재하면 수정, 없으면 생성
        return friendRepository.save(friend);
    }

    // id별 조회
    public Friend findFriendById(Long id) {
        return friendRepository.findById(id).orElse(null);
    }

    // 친구 삭제
    @Transactional
    public void deleteFriendById(Long id) {
        friendRepository.deleteById(id);
    }

    // 페이징 처리된 친구 목록
    public Page<Friend> findAllFriends(Pageable pageable) {
        Pageable sortedByDescId = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        return friendRepository.findAll(sortedByDescId);
    }
}
