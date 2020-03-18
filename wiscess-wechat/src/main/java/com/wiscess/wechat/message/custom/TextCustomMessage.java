/**
 * Copyright (C), 2014-2020, 北京智成卓越科技有限公司
 * FileName: TextCustomMessage
 * Author:   wh
 * Date:     2020/3/2 12:12
 * Description: 客服文本消息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wiscess.wechat.message.custom;

import com.wiscess.wechat.message.custom.model.CustomService;
import com.wiscess.wechat.message.custom.model.Text;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Data
@Builder
public class TextCustomMessage extends BaseCustomMessage{
/**
 * {
 *     "touser":"OPENID",
 *     "msgtype":"text",
 *     "text":
 *     {
 *          "content":"Hello World"
 *     }
 * }
 */
    // 接收方帐号（收到的OpenID）
    private String touser;
    // 消息类型（text/music/news）
    @Builder.Default
    private String msgtype="text";
    // 回复的消息内容
    private Text text;
    //客服账号
    private CustomService customservice;
    
    public TextCustomMessage(String touser,String content) {
    	this.msgtype="text";
    	this.touser=touser;
    	this.text=Text.builder()
    			.content(content)
    			.build();
    }
    
}
