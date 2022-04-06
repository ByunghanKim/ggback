package com.gg.ggback.config.jwt;


import com.gg.ggback.config.auth.PrincipalDetails;
import com.gg.ggback.dto.MemberDto;
import com.gg.ggback.service.MemberService;
import io.jsonwebtoken.Jwts;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//시큐리티 자체 필터 중에 BasicAuthenticationFilter 라는게 있음
//권한이나 인증이 필요한 주소요청시 위에 필터를 무조건 타게 되어있음.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private MemberService memberService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberService memberService) {
        super(authenticationManager);
        this.memberService = memberService;
    }


    //인증이나 권한이 필요한 주소요청이 있을 때 해당 필터를 타게됨.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //super.doFilterInternal(request, response, chain);
        //System.out.println("인증이나 권한이 필요한 주소 요청이 됨");

        String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);
        //System.out.println("jwtHeader : " + jwtHeader);

        //헤더가 있는지 확인
        if (jwtHeader == null) {
            chain.doFilter(request, response);
            return;
        }

        //JWT 토큰을 검증하여 정상적인 사용자인지 확인
        //토큰 검증을 하고 body(payloads)의 id 값을 가져온다
        String id = Jwts.parserBuilder()
                .setSigningKey(JwtProperties.KEY.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(jwtHeader)
                .getBody().get("id").toString();

        //System.out.println(id);

        if (id != null) {
            MemberDto memberDto = memberService.selectMember(id);

            PrincipalDetails principalDetails = new PrincipalDetails(memberDto);

            //System.out.println("authentication 강제로 만든다");
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            request.setAttribute("id", principalDetails.getUsername());

            chain.doFilter(request, response);
        }


    }
}
