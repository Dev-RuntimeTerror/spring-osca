package com.app.osca.mapper;

import com.app.osca.domain.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class MemberMapperTestsKDY {

    @Autowired
    MemberMapper memberMapper;
    @Autowired
    CeoMapper ceoMapper;

//    아이디 중복검사 테스트
    @Test
    public void checkMemberEmailTest(){
        String memberEmail = "cafeㅁㄴㅇ1@NAVER.COM";
        memberMapper.selectForIdCheck(memberEmail).ifPresent((email) -> {
            log.info("{}................", email);
        });
    }

//    비밀번호 재설정 테스트
    @Test
    public void updateMemberPassword() {
        String memberPassword = "dongyeop";
        String memberEmail = "DONGYEOP@NAVER.COM";
        String encryptedPassword = "";
        final int KEY = 3;
        for (int i = 0; i < memberPassword.length(); i++) {
            encryptedPassword += (char)(memberPassword.charAt(i) * KEY);
        }
        memberMapper.updateMemberPassword(encryptedPassword, memberEmail);
    }

//    아이디 찾기 테스트
    @Test
    public void findMemberIdTest(){
        String memberName = "김동엽";
        String membnerPhonenumber = "01076501147";
        memberMapper.selectMemberEmail(memberName, membnerPhonenumber).stream().map(String::toString).forEach(log::info);

    }

//    로그인 테스트
    @Test
    public void memberLoginTest(){

        String memberEmail = "DONGYEOP@NAVER.COM";
        String memberPassword = "1234";

        memberMapper.selectForLogin(memberEmail, memberPassword).ifPresent((id) -> log.info("{}...........", id));
    }

//    넥네임 시퀸스 테스트
//    랜덤 닉네임 생성 테스트
    @Test
    public void nicknameSeqTest(){
        log.info("{}.............", memberMapper.selectNickNameSequence());
        String[] nickNames = {"사과", "바나나", "배", "포도", "딸기", "오렌지", "파인애플", "자두", "귤", "수박"};
        final long SEQ = memberMapper.selectNickNameSequence(), DIV = 10000;
        String nickname = nickNames[(int)(SEQ / DIV)] + (SEQ % DIV);
        log.info("{}.............", nickname);
    }

//    카페 유저 회원가입 테스트
    @Test
    public void CafeInsertTest(){
        Long id = memberMapper.selectMemberSequence();

        MemberVO memberVO = new MemberVO();
        memberVO.setId(id);
        memberVO.setMemberName("카페");
        memberVO.setMemberNickname("카페");
        memberVO.setMemberPhonenumber("010987ㅁㄴㅇ456123");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberEmail("gㅊㅁㅇㄴㅁㅇㄴda@namber.com");
        memberVO.setMemberRole(1L);

        boolean isFirstCafe = memberMapper.selectForCafeCheck(memberVO.getMemberPhonenumber()) == 0;

        memberMapper.insertCafe(memberVO);

        if ( isFirstCafe ) {
            ceoMapper.insertWelcomePoint(id);
        } else {
            ceoMapper.insertPoint(id);
        }
    }



//    일반유저 회원가입 테스트
    @Test
    public void generalInsertTesst(){
        Long id = memberMapper.selectMemberSequence();

        MemberVO memberVO = new MemberVO();
        memberVO.setId(id);
        memberVO.setMemberName("하하하");
        memberVO.setMemberNickname("개같은 깃");
        memberVO.setMemberPhonenumber("010987456123");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberEmail("sdsd@namber.com");

//       이메일 중복검사
        memberMapper.selectForIdCheck(memberVO.getMemberEmail()).ifPresent((email) -> {
            log.info("{}.........", "중복된 이메일 입니다.");
        });
//       닉네임 중복검사
        memberMapper.selectForNickNameCheck(memberVO.getMemberNickname()).ifPresent((nickname) -> {
            log.info("{}.........", "중복된 닉네임 입니다.");
        });

        memberMapper.insertGeneral(memberVO);
    }
}
