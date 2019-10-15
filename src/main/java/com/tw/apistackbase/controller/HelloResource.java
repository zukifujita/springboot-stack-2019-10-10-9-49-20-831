package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
/**
 * Created by jxzhong on 18/08/2017.
 */
@RestController
@RequestMapping("/hello")

public class HelloResource {
    private List<Member> memberList = new ArrayList<>();
    private final Logger log = Logger.getLogger(this.getClass().getName());

    @GetMapping(path = "/getMembers", produces = {"application/json"})
    public ResponseEntity<String> getAll() {
        String memberCompile = "";
        for(Member member : memberList){
            memberCompile += member.getName() + ", \n";
        }
        return ResponseEntity.ok("Hello Members: " + memberCompile);
    }

    @PostMapping(path = "/members", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createMember(@RequestBody Member memberFromPost) {
        memberList.add(memberFromPost);
        return ResponseEntity.ok("Added Member: " + memberFromPost.getName());
    }
}