package cookalone.main.service;

import cookalone.main.domain.Member;
import cookalone.main.domain.dto.account.MemberRequestDto;

import java.util.List;
import java.util.UUID;

public interface FileService {
    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception;
    public void deleteFile(String filePath) throws Exception;
}
