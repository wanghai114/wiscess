package com.wiscess.wechat.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * view类型的按钮
 * 
 * @author wanghai
 * @date 2014-06-11
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Data
@Builder
public class ViewButton extends Button {
	@Builder.Default
	private String type = "view";
	private String url;

}
