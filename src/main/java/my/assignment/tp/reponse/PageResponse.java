package my.assignment.tp.reponse;

import java.util.Map;

public class PageResponse extends ApiResponse{


    private Map<String,?> data;

    public PageResponse(Integer status, String msg) {
        super(status, msg);
    }

    public Map<String, ?> getData() {
        return data;
    }

    public void setData(Map<String, ?> data) {
        this.data = data;
    }
}
