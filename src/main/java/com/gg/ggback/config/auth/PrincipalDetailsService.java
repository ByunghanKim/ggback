package com.gg.ggback.config.auth;

import com.gg.ggback.dto.MemberDto;
import com.gg.ggback.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        //System.out.println("PrincipalDetailsService의 loadUserByUsername()");

        MemberDto memberDto = memberService.selectMember(id);

        return new PrincipalDetails(memberDto);
    }
}
