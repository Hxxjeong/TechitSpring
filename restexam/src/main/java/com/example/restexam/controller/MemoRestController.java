package com.example.restexam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/memos")
public class MemoRestController {
    private final Map<Long, String> memo = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();    // id 자동 증가

    @PostMapping
    public ResponseEntity<Long> createMemo(@RequestBody String content) {
        Long id = counter.incrementAndGet();
        memo.put(id, content);  // service logic
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getMemo(@PathVariable("id") Long id) {
        String m = memo.get(id);
        if(m == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(m);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMemo(@PathVariable("id") Long id,
                                             @RequestBody String content) {
        if(!memo.containsKey(id)) return ResponseEntity.notFound().build();
        memo.put(id, content);
        return ResponseEntity.ok(content);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMemo(@PathVariable("id") Long id) {
        String removeMemo = memo.remove(id);
        if(removeMemo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(removeMemo);
    }
}
