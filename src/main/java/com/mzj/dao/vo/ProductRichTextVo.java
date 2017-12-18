package com.mzj.dao.vo;

/**
 * Created by Administrator on 2017/11/30.
 */
public class ProductRichTextVo {
    private String file_path;
    private String msg;
    private String success;

    public ProductRichTextVo() {
    }

    public ProductRichTextVo(String file_path, String msg, String success) {
        this.file_path = file_path;
        this.msg = msg;
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ProductRichTextVo{" +
                "file_path='" + file_path + '\'' +
                ", success='" + success + '\'' +
                '}';
    }
}
