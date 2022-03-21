package com.uploadexample.fileupload.service;

import com.uploadexample.fileupload.Utils.Utilfile;
import com.uploadexample.fileupload.constants.FilesConst;
import com.uploadexample.fileupload.model.FilesEntity;
import com.uploadexample.fileupload.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService{


    private final FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void saveFile(FilesEntity fileEntity) {
        fileRepository.save(fileEntity);
    }

    @Override
    public List<FilesEntity> fileEntities() {
        return fileRepository.findAll();
    }

    @Override
    public FilesEntity getFilesEntity(Integer id) {
        return fileRepository.findById(id).get();
    }

    @Override
    public void deleteFile(Integer id) {
        fileRepository.deleteById(id);
    }

    @Override
    public void deleteAllFile() {
        fileRepository.deleteAll();
    }


    @Override
    public void updateFile(Integer Id, MultipartFile multipartFile) throws IOException {
       Optional<FilesEntity> filesEntity = fileRepository.findById(Id);

        filesEntity.get().setName(multipartFile.getOriginalFilename());
        filesEntity.get().setContent(multipartFile.getBytes());
        filesEntity.get().setSize((int) multipartFile.getSize());
        filesEntity.get().setUpdateTime( new Timestamp(System.currentTimeMillis()));
        FilesEntity filesEntity1 = filesEntity.get();
        fileRepository.save(filesEntity1);
    }

    @Override
    public boolean fileTypeControl(String fileName) {
        Optional<String> name  = Utilfile.getFileType(fileName);
        boolean b  = FilesConst.list.contains(name.get().toLowerCase());
        return b;
    }
}
