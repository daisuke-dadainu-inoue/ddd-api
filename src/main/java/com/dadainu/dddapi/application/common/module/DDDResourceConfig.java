package com.dadainu.dddapi.application.common.module;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class DDDResourceConfig extends ResourceConfig {

    public DDDResourceConfig() {
        packages(true, "com.dadainu.dddapi");
    }
}
