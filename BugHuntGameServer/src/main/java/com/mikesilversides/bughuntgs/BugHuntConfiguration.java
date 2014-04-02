package com.mikesilversides.bughuntgs;

import com.yammer.dropwizard.config.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class BugHuntConfiguration extends Configuration {
//    @NotEmpty
//    @JsonProperty
//    private String template;

    @NotEmpty
    @JsonProperty
    private String defaultName = "Anonymous";

//    public String getTemplate() {
//        return template;
//    }

    public String getDefaultName() {
        return defaultName;
    }

}
