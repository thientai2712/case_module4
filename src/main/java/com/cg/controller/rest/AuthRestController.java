//package com.cg.controller.rest;
//
//import com.cg.model.JwtResponse;
//import com.cg.model.User;
//import com.cg.service.jwt.JwtService;
//import com.cg.service.role.IRoleService;
//import com.cg.service.user.IUserService;
//import com.cg.util.AppUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseCookie;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthRestController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtService jwtService;
//
//    @Autowired
//    private IUserService userService;
//
//    @Autowired
//    private IRoleService roleService;
//
//    @Autowired
//    private AppUtil appUtil;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt = jwtService.generateTokenLogin(authentication);
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        User currentUser = userService.getByUsername(user.getUsername());
//        JwtResponse jwtResponse = new JwtResponse(
//                jwt,
//                currentUser.getId(),
//                userDetails.getUsername(),
//                currentUser.getUsername(),
//                userDetails.getAuthorities()
//        );
//        ResponseCookie springCookie = ResponseCookie.from("JWT", jwt)
//                .httpOnly(false)
//                .secure(false)
//                .path("/")
//                .maxAge(60 * 1000)
//                .domain("localhost")
////                .domain("ajax-bank-location-jwt.herokuapp.com")
////                .domain("bank-transaction.azurewebsites.net")
//                .build();
//
//        System.out.println(jwtResponse);
//
//        return ResponseEntity
//                .ok()
//                .header(HttpHeaders.SET_COOKIE, springCookie.toString())
//                .body(jwtResponse);
//    }
//
//
//
//}
