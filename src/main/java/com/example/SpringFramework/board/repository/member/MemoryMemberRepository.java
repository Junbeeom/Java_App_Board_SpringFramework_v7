package com.example.SpringFramework.board.repository.member;

import com.example.SpringFramework.board.domain.board.Board;
import com.example.SpringFramework.board.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<Long, Member>();
    private static long sequence = 0L;

    @Override
    public Member create(Member member) {
        member.setUser_no(++sequence);
        log.info("create: member={}", member);
        store.put(member.getUser_no(), member);
        return member;
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public Optional<Member> findById(long user_no) {
        return Optional.ofNullable(store.get(user_no));
    }

    @Override
    public Optional<Member> findByLoginId(String id) {
        return findAll().stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
