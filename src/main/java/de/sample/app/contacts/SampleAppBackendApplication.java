package de.sample.app.contacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@SpringBootApplication
public class SampleAppBackendApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SampleAppBackendApplication.class, args);
    }

    /**
     * Activate Validation API / JSR-303 to use {@link javax.validation.Valid} and other usefull annotations.
     *
     * @return
     */
    @Bean
    public Validator localValidatorFactoryBean()
    {
        return new LocalValidatorFactoryBean();
    }

}
