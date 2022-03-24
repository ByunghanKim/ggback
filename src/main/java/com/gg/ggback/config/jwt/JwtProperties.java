package com.gg.ggback.config.jwt;

public interface JwtProperties {
    String KEY = "kbhjsonwebtokenmakesurethatitslongenoughtomakejwt"; // 비밀키
    Long EXPIRE_TIME = 1000 * 60L * 100; // 100분
    String HEADER_STRING = "Authorization"; //헤더 이름 (JWT 저장하는 헤더 이름)
}
