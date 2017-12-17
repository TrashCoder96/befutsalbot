package ru.bifutsal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by asus-pc on 17.12.2017.
 */

@Configuration
@EnableAsync
@EnableScheduling
public class JobConfig {
}
