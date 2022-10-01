package my.assignment.tp.reponse;

import java.io.Serializable;

public class ApiResponse implements Serializable {
    private Integer status;
    private String msg;

    public ApiResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
