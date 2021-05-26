package edu.xihua.project.pms.model.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/25 11:58 下午
 * @since 1.0
 */
@Data
public class Js2CodeSessionVO {
    //    {"errcode":40029,"errmsg":"invalid code, hints: [ req_id: 4lmcLPI2e-EgdVpA ]"}
    private Integer errcode;
    private String errmsg;
    @SerializedName("session_key")
    private String sessionKey;
    private String openid;
}
