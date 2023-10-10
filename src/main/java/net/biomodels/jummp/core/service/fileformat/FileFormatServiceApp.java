package net.biomodels.jummp.core.service.fileformat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"net.biomodels.jummp.*"})
@EnableAsync
public class FileFormatServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(FileFormatServiceApp.class, args);
    }
}
