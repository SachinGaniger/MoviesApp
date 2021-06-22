package com.igpl.moviesapp.Models;

import java.util.List;

public class CreditsResponse {

    private int id;

    private List<Cast> cast;

    public CreditsResponse() {
    }

    public CreditsResponse(int id, List<Cast> castList) {
        this.id = id;
        this.cast = castList;
    }

    public int getId() {
        return id;
    }

    public List<Cast> getCastList() {
        return cast;
    }
}
