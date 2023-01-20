package com.apilighthouse.apilighthouse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author githuber
 * @since 2023-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TLighthouse对象", description="")
public class TLighthouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String serverName;

    private String url;

    private String remarks;

    public TLighthouse(){

    }

    public TLighthouse(String serverName, String url,String remarks){
        this.serverName = serverName;
        this.url = url;
        this.remarks = remarks;
    }


}
