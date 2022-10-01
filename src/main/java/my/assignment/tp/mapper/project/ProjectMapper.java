package my.assignment.tp.mapper.project;

import my.assignment.tp.entity.Project;
import my.assignment.tp.provider.project.ProjectSelectProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProjectMapper {

    @Select(value = "SELECT count(id) FROM projects")
    Integer totalCount();

    @SelectProvider(type = ProjectSelectProvider.class, method = "buildFindProjectById")
    Optional<Project> findById(@Param("id") String id);

    @SelectProvider(type = ProjectSelectProvider.class,method = "buildFindAllProjects")
    List<Project> findAll(@Param("limit") Integer limit, @Param("offset") Integer offset);

    @Insert("INSERT INTO projects(id,name,introduction,owner,start_date,end_date,current_status,created_at)" +
            "values(#{id},#{name},#{introduction},#{owner},#{startDate},#{endDate},#{currentStatus},#{createdAt})")
    void save(Project project);

    @Update("UPDATE projects set name=#{name},introduction=#{introduction}," +
            "owner=#{owner},current_status = #{currentStatus}," +
            "start_date=#{startDate},end_date=#{endDate} WHERE id = #{id}")
    void update(Project project);
}
