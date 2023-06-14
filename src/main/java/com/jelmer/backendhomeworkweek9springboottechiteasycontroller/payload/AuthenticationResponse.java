package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.payload;

public class AuthenticationResponse {
//    in dto's zetten

    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}