package com.example.jwtexam.controller;

import com.example.jwtexam.auth.dto.UserLoginDto;
import com.example.jwtexam.domain.RefreshToken;
import com.example.jwtexam.domain.Role;
import com.example.jwtexam.domain.User;
import com.example.jwtexam.dto.UserLoginRspDto;
import com.example.jwtexam.auth.jwt.util.JwtTokenizer;
import com.example.jwtexam.service.RefreshTokenService;
import com.example.jwtexam.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenizer jwtTokenizer;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserLoginDto userLoginDto,
                                BindingResult bindingResult,
                                HttpServletResponse response) {
        // username, password가 정해진 형식에 맞지 않을 때
        if(bindingResult.hasErrors()) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        // 가입한 유저인지 확인
        User user = userService.findByUsername(userLoginDto.getUsername());

        // 비밀번호가 일치하는지 확인
        if(!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword()))
            return new ResponseEntity("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);

        // 유저의 role 정보 확인
        List<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        // 토큰 발급
        String accessToken = jwtTokenizer.createAccessToken(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getUsername(),
                roles
        );
        String refreshToken = jwtTokenizer.createRefreshToken(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getUsername(),
                roles
        );

        // DB에 토큰 저장
        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setValue(refreshToken);
        refreshTokenEntity.setUserId(user.getId());

        refreshTokenService.create(refreshTokenEntity);

        // 응답으로 보낼 객체
        UserLoginRspDto rspDto = UserLoginRspDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .name(user.getName())
                .build();

        // 토큰을 쿠키로 전달
        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
        accessTokenCookie.setHttpOnly(true);    // 쿠키값을 JS에서 접근할 수 없게 함
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(Math.toIntExact(JwtTokenizer.ACCESS_TOKEN_EXPIRE_COUNT / 1000));  // cookie: s | jwt: ms

        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(Math.toIntExact(JwtTokenizer.REFRESH_TOKEN_EXPIRE_COUNT / 1000));

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);

        return new ResponseEntity(rspDto, HttpStatus.OK);
    }

    @GetMapping("/api/authtest")
    public String authTest() {
        return "auth test";
    }

    // refresh token 생성
    @PostMapping("/refreshToken")
    public ResponseEntity createRefreshToken(HttpServletRequest request,
                                             HttpServletResponse response) {
        String refreshToken = null;
        Cookie[] cookies = request.getCookies();

        // 쿠키로부터 refresh token 얻어오기
        if(cookies != null) {
            for(Cookie cookie: cookies) {
                if("refreshToken".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }

        // 쿠키가 없는 경우 예외 처리
        if(refreshToken == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        // 토큰으로부터 정보 얻기
        Claims claims = jwtTokenizer.parseRefreshToken(refreshToken);
        Long userId = Long.valueOf((Integer) claims.get("userId"));

        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾지 못하였습니다."));

        // access token 생성
        List roles = (List) claims.get("roles");

        String accessToken = jwtTokenizer.createAccessToken(
                userId,
                user.getEmail(),
                user.getName(),
                user.getUsername(),
                roles
        );

        // 쿠키에 토큰 저장
        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(Math.toIntExact(JwtTokenizer.ACCESS_TOKEN_EXPIRE_COUNT / 1000));

        response.addCookie(accessTokenCookie);

        // 응답 객체
        UserLoginRspDto rspDto = UserLoginRspDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(userId)
                .name(user.getName())
                .build();

        return new ResponseEntity(rspDto, HttpStatus.OK);
    }
}
