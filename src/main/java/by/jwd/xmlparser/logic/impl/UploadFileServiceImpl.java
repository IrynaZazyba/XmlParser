package by.jwd.xmlparser.logic.impl;

import by.jwd.xmlparser.logic.UploadFileService;
import by.jwd.xmlparser.logic.UploadServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class UploadFileServiceImpl implements UploadFileService {

    private final static String UPLOAD_DIRECTORY = "\\upload";
    private final static Logger logger = LogManager.getLogger();

    public UploadFileServiceImpl() {
    }

    @Override
    public String uploadFile(String rootDir, Collection<Part> parts) throws UploadServiceException {
        String fileName = null;

        String uploadPath = rootDir + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        for (Part part : parts) {
            if (part.getSubmittedFileName() != null) {
                fileName = part.getSubmittedFileName();

                try {
                    part.write(uploadPath + File.separator + fileName);
                } catch (IOException e) {
                    logger.log(Level.ERROR, e.getMessage(), e);
                    throw new UploadServiceException("Error with write file to the directory " + UPLOAD_DIRECTORY, e);
                }
            }
        }

        return uploadPath + File.separator + fileName;
    }

}
