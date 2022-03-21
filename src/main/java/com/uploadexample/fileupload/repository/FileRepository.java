package com.uploadexample.fileupload.repository;


import com.uploadexample.fileupload.model.FilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FilesEntity,Integer> {


}
