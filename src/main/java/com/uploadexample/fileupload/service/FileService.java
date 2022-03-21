package com.uploadexample.fileupload.service;

import com.uploadexample.fileupload.model.FilesEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FileService {

    void saveFile(FilesEntity fileEntity);

    List<FilesEntity> fileEntities();

    FilesEntity getFilesEntity(Integer id);

    void deleteFile(Integer id);

    void deleteAllFile();

    void updateFile(Integer Id, MultipartFile multipartFile) throws IOException;

    boolean fileTypeControl(String fileName);

}
