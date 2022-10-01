package my.assignment.tp.service;

import my.assignment.tp.entity.Project;
import my.assignment.tp.reponse.PageResponse;

public interface ProjectService {

    void createProject(Project project);
    PageResponse getPages(Integer integer, Integer orElse);
    void updateProject(String id, Project project) throws Exception;
}
