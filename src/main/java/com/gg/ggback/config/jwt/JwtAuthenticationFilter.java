package com.gg.ggback.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gg.ggback.config.auth.PrincipalDetails;
import com.gg.ggback.dto.MemberDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // /login 주소로 요청을 하면 실행하는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //System.out.println("JwtAuthenticationFilter: 로그인 시도중");


        try {
            //1. 아이디와 비번 받는다
            ObjectMapper objectMapper = new ObjectMapper();

            MemberDto memberDto = objectMapper.readValue(request.getInputStream(),MemberDto.class);

            //System.out.println(memberDto);

            //2. 정상인지 시도/
            //AuthenticationManager로 로그인 시도를 하면 PrincipalDetailsService가 호출
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(memberDto.getId(), memberDto.getPw());

            //3. PrincipalDetails를 세션에 담는다(권한관리를 위해서 세션에 담아야함)
            //authenticationManager에 authenticationToken을 담아서 던지면
            //PrincipalDetailsService의 loadUserByUsername() 함수가 실행됨
            //그 결과가 authentication 변수에 담김
            Authentication authentication =
                    authenticationManager.authenticate(authenticationToken);


            //authentication 객체를 session영역에 저장을 해야하는데
            //그 방법이 return authentication; 하면됨
            //JWT 토큰을 사용하면서 굳이 세션을 만들 이유는 없지만
            //시큐리티에서 권한 관리를 대신 해주기 때문에 편하려고 session에 저장해둔다.


            //4. JWT토큰을 만들어서 응답

            return authentication;
//            BufferedReader
//                     br = request.getReader();
//
//            String input = null;
//
//            while((input = br.readLine()) != null) {
//                System.out.println(input);
//            }
            //System.out.println(request.getInputStream().toString());
        } catch (Exception e) {
            try {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"인증실패");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println(e.toString());
        }

        return null;
    }

    //attemptAuthentication 실행 후 인증이 정상적으로 되면 successfulAuthentication 함수가 실행된다.
    //JWT 토큰 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해주면 됨
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        //System.out.println("successfulAuthentication() 실행 인증성공");

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        //JWT 토큰 생성

        //Header
        Map<String,Object> headers = new HashMap<>();
        headers.put("typ","JWT");
        headers.put("alg","HS256");

        //Payload(내용)
        Map<String,Object> payloads = new HashMap<>();

        Date now = new Date();
        now.setTime(now.getTime() + JwtProperties.EXPIRE_TIME);
        payloads.put("exp",now);
        payloads.put("id",principalDetails.getUsername());

        String jwt = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                        .signWith(SignatureAlgorithm.HS256, JwtProperties.KEY.getBytes())
                .compact();

        //System.out.println(jwt);

        response.addHeader(JwtProperties.HEADER_STRING, jwt);
        

    }
}
