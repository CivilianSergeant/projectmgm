package my.assignment.tp.service;

import my.assignment.tp.entity.Project;
import my.assignment.tp.mapper.project.ProjectMapper;
import my.assignment.tp.reponse.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public void createProject(Project project) {
        project.setId(UUID.randomUUID().toString());
        project.setStartDate(null);
        project.setEndDate(null);
        project.setCurrentStatus(0);
        project.setCreatedAt(LocalDateTime.now());
        projectMapper.save(project);
    }

    @Override
    public PageResponse getPages(Integer size, Integer page) {
        Map<String,Object> result = new HashMap<>();
        result.put("total", projectMapper.totalCount());
        result.put("currentPage",page);
        result.put("list", projectMapper.findAll(size, page));
        PageResponse pageResponse= new PageResponse(1,"Get project list successful");
        pageResponse.setData(result);
        return pageResponse;
    }

    @Override
    public void updateProject(String id, Project project) throws Exception {
        Optional<Project> projectOp = projectMapper.findById(id);
        if(projectOp.isPresent()) {
            project.setId(projectOp.get().getId());
            if (project.getName() == null) {
                project.setName(projectOp.get().getName());
            }
            if (project.getCurrentStatus() == 1) {
                project.setStartDate(LocalDateTime.now());
            }
            if (project.getCurrentStatus() == 3) {
                project.setStartDate(projectOp.get().getStartDate());
                project.setEndDate(LocalDateTime.now());
            }
            if (project.getOwner() == null) {
                project.setOwner(projectOp.get().getOwner());
            }
            project.setCreatedAt(projectOp.get().getCreatedAt());
            projectMapper.update(project);
        }else{
            throw new Exception("Sorry! Project not found with the id");
        }
    }
}
