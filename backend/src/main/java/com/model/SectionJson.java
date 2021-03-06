package com.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionJson {

    private int id;
    private String name;
    private int eventId;
    private String supervisorEmail;
    private List<String> papers;
    private List<String> speakers;
}
