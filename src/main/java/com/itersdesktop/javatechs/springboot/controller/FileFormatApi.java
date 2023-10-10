package com.itersdesktop.javatechs.springboot.controller;

import com.itersdesktop.javatechs.springboot.format.OmexService;
import com.itersdesktop.javatechs.springboot.model.Model;
import com.itersdesktop.javatechs.springboot.model.RepositoryFileDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/v1.0/file-format")
public class FileFormatApi {
    Logger logger = LoggerFactory.getLogger(FileFormatApi.class);

    private OmexService omexService;

    private final TaskExecutor taskExecutor;

    public FileFormatApi(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @Autowired
    public void setOmexService(OmexService omexService) {
        this.omexService = omexService;
    }

    @PostMapping("/create-model")
    public String createModel(@RequestBody List<Model> models) {
        System.out.println(models.toString());
        logger.debug(models.toString());
        System.out.println("create a new model");
        logger.debug("create a new model");
        return "A new model has been created";
    }

    @PostMapping("/create-omex")
    public ResponseEntity<String> createCombineArchive(@RequestBody List<RepositoryFileDao> files) throws IOException, URISyntaxException {
        String modelId = files.get(0).getModelId();
        //logger.info(modelId);
        String filePath = omexService.initialiseFilePath(modelId);
        //logger.info(filePath);

        // Wait until they are all done
        CompletableFuture<String> result = omexService.createCombineArchive(files, modelId);

        /*for (RepositoryFileDao file : files) {
            System.out.println(file);
            logger.info(String.valueOf(file));
        }*/

        logger.info("Find the COMBINE archive at " + filePath);
        return new ResponseEntity<>(filePath, HttpStatus.OK);
    }

    @GetMapping(value = "/test-completable-future")
    public CompletableFuture<String> echoHelloWorld() {
        return CompletableFuture.supplyAsync(() -> {
            randomDelay();
            logger.info("Hello World from testing Completable Future!");
            return "Hello World from testing Completable Future!!!";
        }, taskExecutor);
    }

    private void randomDelay() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(10_000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
