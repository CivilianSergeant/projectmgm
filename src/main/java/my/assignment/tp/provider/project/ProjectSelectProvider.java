package my.assignment.tp.provider.project;

import org.apache.ibatis.jdbc.SQL;

public class ProjectSelectProvider {

    public static String buildFindAllProjects(final Integer limit,final Integer offset){
        return new SQL(){{
            SELECT("id,name,introduction,owner,start_date as startDate, end_date as endDate, current_status" +
                    " as currentStatus ");
            FROM("projects");
            if(limit!=null){
                LIMIT(limit);
            }
            if(offset!=null){
                OFFSET(offset);
            }
            ORDER_BY("created_at DESC");
            

        }}.toString();
    }

    public static String buildFindProjectById(final String id){
        return new SQL(){{
            SELECT("id,name,introduction,owner,start_date as startDate, end_date as endDate, current_status" +
                    " as currentStatus ");
            FROM("projects");

            if(id!=null){
                WHERE("id = #{id}");
            }


        }}.toString();
    }
}
