package com.hgcw.fmmall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hgcw
 * 返回类
 * @date 2021/5/18 20:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ResultVo响应类",description = "响应类")
public class ResultVo {
    //响应的状态码
    @ApiModelProperty(value = "响应的状态码",required = true)
    private int code;
    //响应的状态信息
    @ApiModelProperty(value = "响应的状态信息",required = true)
    private String msg;
    //响应的状态数据
    @ApiModelProperty(value ="响应的状态数据",required = true)
    private Object data;
}
