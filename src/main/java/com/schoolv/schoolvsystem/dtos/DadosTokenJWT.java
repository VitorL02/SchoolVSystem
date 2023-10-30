package com.schoolv.schoolvsystem.dtos;

import java.time.Instant;

public class DadosTokenJWT {
    private String tokenJWT;
    private Instant experiesAt;

    public DadosTokenJWT(String tokenJWT, Instant experiesAt) {
        this.tokenJWT = tokenJWT;
        this.experiesAt = experiesAt;
    }

    public String getTokenJWT() {
        return tokenJWT;
    }

    public void setTokenJWT(String tokenJWT) {
        this.tokenJWT = tokenJWT;
    }

    public Instant getExperiesAt() {
        return experiesAt;
    }

    public void setExperiesAt(Instant experiesAt) {
        this.experiesAt = experiesAt;
    }
}
