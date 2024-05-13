package com.example.profile;

public class DataService {
    private String environment;

    public DataService(String environment) {
        this.environment = environment;
    }

    public String getEnvironment() {
        return environment;
    }
}
