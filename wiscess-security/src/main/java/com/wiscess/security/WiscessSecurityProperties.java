package com.wiscess.security;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * 项目用的security的参数配置文件
 * 继承了springboot security 2.0.4的SecurityProperties
 * 并添加了自定义的参数：
 * captcha: 默认为true，是否使用验证码
 * superPwd: 超级管理密码
 * 
 * 20190424增加前端使用Vue的方式发送的登录请求，参数中增加isVueMode的方式
 * 增加vue跨域属性allowedOrigins
 * @author wh
 */
@Slf4j
@ConfigurationProperties(prefix = "security")
public class WiscessSecurityProperties implements InitializingBean{

	/**
	 * 登录页面是否含验证码
	 */
  	private boolean captcha= true;
  	/**
  	 * 用户名和密码是否加密传输
  	 */
  	private boolean encryptUsername = true;
  	private boolean encryptPassword = true;
  	/**
  	 * 超级管理密码
  	 */
	private String superPwd="";
	
	/**
	 * 允许同一用户同时在线人数
	 */
	private Integer maxSessionNum = 10;
	/**
	 * 超过最大人数时是否踢掉之前的用户，true不能登录，false踢掉用户
	 */
	private Boolean maxSessionsPreventsLogin = true;
	/**
	 * 自定义error页
	 */
	private String errorPage="/error";

  	/**
  	 * 密码加密方式，后台MD5
  	 */
  	private String passwordType="md5IgnoreCase";
	
  	/**
  	 * 不进行权限认证的url
  	 */
  	private List<String> execludeUrls;
  	/**
  	 * 忽略的资源
  	 */
  	private List<String> ignored;
  	/**
  	 * 是否SSO认证
  	 */
  	public Boolean isSsoMode(){
  		return sso.getAuthUrl()!=null;
  	}
  	/**
  	 * 是否Vue认证
  	 */
  	public Boolean isVueMode() {
		return vue.getEnabled()!=null && vue.getEnabled();
	}

	public Boolean isJwtMode() { 
		return jwt.getEnabled()!=null && jwt.getEnabled(); 
	}
  	private Sso sso = new Sso();
  	
  	private Vue vue = new Vue();

  	private Jwt jwt = new Jwt();
  	
  	public class Sso{
  		private String authUrl;
  		private String failureUrl="/error";
  		private String authTestUrl;
  		private String logoutUrl="/logout";
  		private String authLogoutUrl;
  		private String encryptType="RSA";
  		
  		public String getAuthUrl() {
  			return authUrl;
  		}

  		public void setAuthUrl(String authUrl) {
  			this.authUrl = authUrl;
  		}

  		public String getFailureUrl() {
  			return failureUrl;
  		}

  		public void setFailureUrl(String failureUrl) {
  			this.failureUrl = failureUrl;
  		}

  		public String getAuthTestUrl() {
  			return authTestUrl;
  		}

  		public void setAuthTestUrl(String authTestUrl) {
  			this.authTestUrl = authTestUrl;
  		}

  		public String getLogoutUrl() {
  			return logoutUrl;
  		}

  		public void setLogoutUrl(String logoutUrl) {
  			this.logoutUrl = logoutUrl;
  		}

		public String getEncryptType() {
			return encryptType;
		}

		public void setEncryptType(String encryptType) {
			this.encryptType = encryptType;
		}

		public String getAuthLogoutUrl() {
			return authLogoutUrl;
		}

		public void setAuthLogoutUrl(String authLogoutUrl) {
			this.authLogoutUrl = authLogoutUrl;
		}
  	}
  	
  	public class Vue{
  		private Boolean enabled=false;
  		private List<String> allowedOrigins;

		public Boolean getEnabled() {
			return enabled;
		}

		public void setEnabled(Boolean enabled) {
			this.enabled = enabled;
		}

		public List<String> getAllowedOrigins() {
			return allowedOrigins;
		}

		public void setAllowedOrigins(List<String> allowedOrigins) {
			this.allowedOrigins = allowedOrigins;
		}
  	}

  	public class Jwt{
		private Boolean enabled=false;

		private String secret="1w2i3s4c5e6s7s";

		private Integer expiration=1800;

		public Boolean getEnabled() {
			return enabled;
		}

		public void setEnabled(Boolean enabled) {
			this.enabled = enabled;
		}

		public String getSecret(){ return secret;}

		public void setSecret(String secret){ this.secret=secret;}

		public Integer getExpiration(){ return expiration;}

		public void setExpiration(Integer expiration) {this.expiration=expiration;}

	}

	public boolean isCaptcha() {
		return captcha;
	}
	public void setCaptcha(boolean captcha) {
		this.captcha = captcha;
	}
	public String getSuperPwd() {
		return superPwd;
	}
	public void setSuperPwd(String superPwd) {
		this.superPwd = superPwd;
	}
	public String getErrorPage() {
		return errorPage;
	}
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}	
	public String getPasswordType() {
		return passwordType;
	}
	public void setPasswordType(String passwordType) {
		this.passwordType = passwordType;
	}
	
	public List<String> getExecludeUrls() {
		return execludeUrls;
	}
	public void setExecludeUrls(List<String> execludeUrls) {
		this.execludeUrls = execludeUrls;
	}
	public Sso getSso() {
		return sso;
	}
	public void setSso(Sso sso) {
		this.sso = sso;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("WiscessSecurityProperties loaded.");
	}
	public List<String> getIgnored() {
		return ignored;
	}
	public void setIgnored(List<String> ignored) {
		this.ignored = ignored;
	}
	public boolean isEncryptUsername() {
		return encryptUsername;
	}
	public void setEncryptUsername(boolean encryptUsername) {
		this.encryptUsername = encryptUsername;
	}
	public boolean isEncryptPassword() {
		return encryptPassword;
	}
	public void setEncryptPassword(boolean encryptPassword) {
		this.encryptPassword = encryptPassword;
	}
	public Integer getMaxSessionNum() {
		return maxSessionNum;
	}
	public void setMaxSessionNum(Integer maxSessionNum) {
		this.maxSessionNum = maxSessionNum;
	}
	public Vue getVue() {
		return vue;
	}
	public void setVue(Vue vue) {
		this.vue = vue;
	}
	public Boolean getMaxSessionsPreventsLogin() {
		return maxSessionsPreventsLogin;
	}
	public void setMaxSessionsPreventsLogin(Boolean maxSessionsPreventsLogin) {
		this.maxSessionsPreventsLogin = maxSessionsPreventsLogin;
	}
	public Jwt getJwt() { return jwt; }
	public void setJwt(Jwt jwt) {this.jwt=jwt;}
}
