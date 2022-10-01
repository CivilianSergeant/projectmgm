package my.assignment.tp.resources;

import my.assignment.tp.entity.Project;
import my.assignment.tp.reponse.ApiResponse;
import my.assignment.tp.reponse.PageResponse;
import my.assignment.tp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private static final Integer LIMIT = 2;

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Project project){
        projectService.createProject(project);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam Optional<Integer> page,
                                    @RequestParam Optional<Integer> size){


        PageResponse pageResponse;
        try {
            pageResponse = projectService.getPages(size.orElse(LIMIT),page.orElse(0));
        }catch (Exception ex){
            pageResponse= new PageResponse(0,"Sorry! something wrong");
        }
        return new ResponseEntity<>(
                pageResponse,
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCurrentStatus(@PathVariable("id") String id,
                                                 @RequestBody Project project){
        try {
            projectService.updateProject(id, project);
            return new ResponseEntity<>(
                    HttpStatus.NO_CONTENT
            );
        }catch(Exception ex){
            ApiResponse apiResponse =new ApiResponse(0,ex.getMessage());


            return new ResponseEntity<>(
                    apiResponse,
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
