package by.jwd.xmlparser.logic;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public interface UploadFileService {

    String uploadFile(String rootDir, Collection<Part> parts) throws UploadServiceException;


}
