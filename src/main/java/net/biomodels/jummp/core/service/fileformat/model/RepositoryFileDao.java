package net.biomodels.jummp.core.service.fileformat.model;

import java.io.File;
import java.io.Serializable;

public class RepositoryFileDao implements Serializable {
    private static final long serialVersionUID = 1L;

    Long id;

    String modelId;
    Integer revisionNumber;
    String path;
    // this property is used for tracking the actual file name of the RepositoryFile object
    String filename;
    long size;
    boolean showPreview;
    String description;
    boolean hidden;
    boolean mainFile;
    boolean userSubmitted;
    String mimeType;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public Integer getRevisionNumber() {
        return revisionNumber;
    }

    public void setRevisionNumber(Integer revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean isShowPreview() {
        return showPreview;
    }

    public void setShowPreview(boolean showPreview) {
        this.showPreview = showPreview;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isMainFile() {
        return mainFile;
    }

    public void setMainFile(boolean mainFile) {
        this.mainFile = mainFile;
    }

    public boolean isUserSubmitted() {
        return userSubmitted;
    }

    public void setUserSubmitted(boolean userSubmitted) {
        this.userSubmitted = userSubmitted;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String toString() {
        return filename + ", " + path + ", " + filename + ", " + mimeType + ", " + size;
    }
}
