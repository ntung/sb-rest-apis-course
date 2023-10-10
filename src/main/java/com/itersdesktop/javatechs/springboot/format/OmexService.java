package com.itersdesktop.javatechs.springboot.format;

import com.itersdesktop.javatechs.springboot.model.RepositoryFileDao;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;
import org.mbine.co.archive.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class OmexService {
    private final Logger LOGGER = LoggerFactory.getLogger(OmexService.class);

    @Async("asyncTaskExecutor")
    public CompletableFuture<String> createCombineArchive(final List<RepositoryFileDao> files, final String modelId) throws IOException, URISyntaxException {
        String result;
        if (files.isEmpty() || Objects.equals(modelId, "")) {
            result = "";
        } else {

            String absOmexFilePath = initialiseFilePath(modelId);
            CombineArchiveFactory fact = new CombineArchiveFactory();
            ICombineArchive arch = fact.openArchive(absOmexFilePath, true);

            // Start measuring execution time
            long startTime = System.nanoTime();

            // need an async process running here
            doCreateCombineArchive(files, arch);

            // Stop measuring execution time
            long endTime = System.nanoTime();

            // Calculate the execution time in milliseconds
            long executionTime = (endTime - startTime) / 1_000_000;

            long seconds = executionTime / 1_000;
            long HH = seconds / 3600;
            long MM = (seconds % 3600) / 60;
            long SS = seconds % 60;
            String timeInHHMMSS = String.format("%02d:%02d:%02d", HH, MM, SS);

            LOGGER.info("Created the OMEX file " + absOmexFilePath + " takes " + executionTime + "ms = " + timeInHHMMSS);
            result = absOmexFilePath;
        }
        return CompletableFuture.completedFuture(result);
    }

    @Async("asyncTaskExecutor")
    void doCreateCombineArchive(final List<RepositoryFileDao> files,
                                final ICombineArchive arch) throws URISyntaxException, IOException {
        final String MODEL_CACHE = System.getenv("MODEL_CACHE");
        for (RepositoryFileDao rfDao : files) {
            String absOriginalFilePath = rfDao.getPath();
            String absFilePath = absOriginalFilePath;
            if (MODEL_CACHE != null) {
                // TODO: in the client, should extract the path upto MODEL.../versionNumber/....
                absFilePath = MODEL_CACHE + "/" + absOriginalFilePath.substring(absOriginalFilePath.lastIndexOf("modelCache/") + "modelCache/".length());
            }
            File file = new File(absFilePath);
            String fileName = file.getName();
            Path path = Paths.get(absFilePath);
            //URI uri = Formatizer.guessFormat(file);
            URI uri = new URI("https://identifiers.org/combine.specifications/omex-metadata");
            String format = uri.toString();
            boolean master = rfDao.isMainFile();
            ArtifactInfo artifactInfo = arch.createArtifact(fileName, format, master);
            OutputStream writer = arch.writeArtifact(artifactInfo);
            try {
                Files.copy(path, writer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            writer.close();
        }

        // customise the metadata.rdf
        String licenceValue = "https://creativecommons.org/publicdomain/zero/1.0/";
        DateFormat sdf = new SimpleDateFormat("E LLL dd HH:mm:ss z yyyy");
        String timeStamp = sdf.format(new Date());
        String provenanceValue = "This model was downloaded from BioModels (${BioModels.BM_ROOT_URL}/) on " + timeStamp;
        MetadataManager mdm = (MetadataManager) arch.getMetadata();
        mdm.load();
        Model model = mdm.getRDFModel();
        Resource resource;
        resource = model.getResource("file:///");
        resource.addProperty(DCTerms.provenance, provenanceValue);
        resource.addProperty(DCTerms.license, licenceValue);
        mdm.save();

        ManifestManager mfm = (ManifestManager) arch.getManifest();
        mfm.sortByLocation();
        mfm.save();

        arch.close();
    }

    public String initialiseFilePath(String modelId) {
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Date date = new Date();
        String dateTimeString = sdf.format(date);
        String TEMP_PATH = System.getProperty("java.io.tmpdir");
        return Paths.get(TEMP_PATH, modelId + "-" + dateTimeString + ".omex").toString();
    }
}
