package org.example.restserver.config;

import org.example.restserver.dto.CompanyRequestDto;
import org.example.restserver.entity.Company;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Base64;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<CompanyRequestDto, Company>() {
            @Override
            protected void configure() {
                using(context -> {
                    String base64String = (String) context.getSource();
                    return base64String != null ? Base64.getDecoder().decode(base64String) : null;
                }).map(source.getProfileImage(), destination.getProfileImage());
            }
        });

        return modelMapper;
    }
}