package com.uploadexample.fileupload.controller;


import com.uploadexample.fileupload.Response.FileUploadResponse;
import com.uploadexample.fileupload.exception.FileTypeException;
import com.uploadexample.fileupload.model.FilesEntity;
import com.uploadexample.fileupload.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping("/api")
@Api(value = "File Api Documentation")
public class FileController {

    private final FileService fileService;


    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/allFiles")
    @ApiOperation(value = "All Files List")
    public List<FilesEntity> fileEntities(){
        return fileService.fileEntities();
    }


    @PostMapping("/save")
    @ApiOperation(value = "File save method")
    public FileUploadResponse saveFile(@RequestParam("file") MultipartFile multipartFile) throws IOException, FileTypeException {
           FileUploadResponse fileUploadResponse = new FileUploadResponse();

         boolean b = fileService.fileTypeControl(multipartFile.getOriginalFilename());
           if (!b){
                throw new FileTypeException("png, jpeg, jpg, docx, pdf, xlsx must be in format");
            }

        try {

            FilesEntity fileEntity = new FilesEntity();

            fileEntity.setName(multipartFile.getOriginalFilename());
            fileEntity.setContent(multipartFile.getBytes());
            fileEntity.setSize((int) multipartFile.getSize());
            fileEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            fileService.saveFile(fileEntity);

            fileUploadResponse.setUploadTime(new Timestamp(System.currentTimeMillis()));
            fileUploadResponse.setFilename(multipartFile.getOriginalFilename());
            fileUploadResponse.setUploadStatus(true);
            fileUploadResponse.setMessage("File upload successful");

        }
        catch (Exception ioException){
            ioException.printStackTrace();
            ioException.getMessage();
        }

        return fileUploadResponse;
    }

    @GetMapping("/file/{id}")
    @ApiOperation(value = "Get File Object")
    public FilesEntity filesEntity(@PathVariable("id") Integer id){
        return fileService.getFilesEntity(id);
    }



    @PutMapping("/fileupdate/{id}")
    @ApiOperation(value = "File Update metod")
    public void updateFile(@PathVariable("id") Integer id,@RequestParam("file") MultipartFile multipartFile) {
        try {
            fileService.updateFile(id,multipartFile);
        } catch (Exception e){
            e.getMessage();
        }
    }


    @DeleteMapping("/deleteAll")
    @ApiOperation(value = "All Files Delete")
    public void deleteAllFiles(){
        fileService.deleteAllFile();
    }

    @DeleteMapping("/deleteFiles/{id}")
    @ApiOperation(value = "File delete metod")
    public void deleteFiles(@PathVariable("id") Integer id){
        fileService.deleteFile(id);
    }






}
