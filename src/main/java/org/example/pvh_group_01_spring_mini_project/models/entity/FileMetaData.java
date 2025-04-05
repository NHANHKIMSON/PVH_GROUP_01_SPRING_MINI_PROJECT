package org.example.pvh_group_01_spring_mini_project.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pvh_group_01_spring_mini_project.util.UUIDTypeHandler;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileMetaData extends UUIDTypeHandler {
    private String fileName;
    private String fileType;
    private String fileUrl;
    private Long fileSize;
}