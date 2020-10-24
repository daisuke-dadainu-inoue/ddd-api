package ddd.application.common.module;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import ddd.application.company.add.module.CompanyAddModule;
import ddd.application.company.find.module.CompanyFindByIdModule;
import ddd.application.company.find.module.CompanyFindModule;
import ddd.application.company.update.module.CompanyUpdateByIdModule;
import ddd.application.company.update.module.CompanyUpdateModule;

@ApplicationPath("api")
public class DDDResourceConfig extends ResourceConfig {

    public DDDResourceConfig() {
        packages(true, "ddd");
        register(new CompanyAddModule());
        register(new CompanyFindModule());
        register(new CompanyFindByIdModule());
        register(new CompanyUpdateModule());
        register(new CompanyUpdateByIdModule());
    }
}
