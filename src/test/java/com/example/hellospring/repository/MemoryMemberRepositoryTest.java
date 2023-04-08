package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("wonhui");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

// org.junit.jupiter.api의 Assertions 버전

        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(result, member);

        // 만약 결과치가 서로 '다르다면'
        //Assertions.assertEquals(member, null);

        // org.assertj.core.api의 Assertions 버전
        //Assertions.assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(result);   // static import 한 경우
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member member3 = new Member();
        member3.setName("spring3");
        repository.save(member3);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(3);
    }
}
