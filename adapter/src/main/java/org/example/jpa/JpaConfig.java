package org.example.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("org.example.jpa")
@EnableJpaRepositories("org.example.jpa")
public class JpaConfig {
}