package com.uploadexample.fileupload.Utils;

import java.util.Optional;

public class Utilfile {

    public static Optional<String> getFileType(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

}
