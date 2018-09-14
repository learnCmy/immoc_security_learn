package com.imooc.security.core.authentication.mobile;

import com.imooc.security.core.properties.SecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: cmy
 * @Date: 2018/7/28 16:09
 * @Description:
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
        // ~ Static fields/initializers
        // =====================================================================================

        public static final String SPRING_SECURITY_FORM_MOBILE_KEY = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;

        private String mobileParameter = SPRING_SECURITY_FORM_MOBILE_KEY;//在请求中你所携带的参数名称是什么
        private boolean postOnly = true;

        // ~ Constructors
        // ===================================================================================================

	    public SmsCodeAuthenticationFilter() {
            super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
        }

        // ~ Methods
        // ========================================================================================================

        public Authentication attemptAuthentication(HttpServletRequest request,
                HttpServletResponse response) throws AuthenticationException {
            if (postOnly && !request.getMethod().equals("POST")) {
                throw new AuthenticationServiceException(
                        "Authentication method not supported: " + request.getMethod());
            }

            String mobile = obtainMobile(request);

            if (mobile == null) {
                mobile = "";
            }

            mobile = mobile.trim();

            SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);

            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);
        }

    /**
     *
     * @param request
     * @return 获取手机号的方法
     */
    protected String obtainMobile(HttpServletRequest request) {
            return request.getParameter(mobileParameter);
        }

        /**
         * Provided so that subclasses may configure what is put into the authentication
         * request's details property.
         *
         * @param request that an authentication request is being created for
         * @param authRequest the authentication request object that should have its details
         * set
         */
        protected void setDetails(HttpServletRequest request,
                SmsCodeAuthenticationToken authRequest) {
            authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        }

        /**
         * Sets the parameter name which will be used to obtain the username from the login
         * request.
         *
         * @param usernameParameter the parameter name. Defaults to "username".
         */
        public void setUsernameParameter(String usernameParameter) {
            Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
            this.mobileParameter = usernameParameter;
        }

        public void setPostOnly(boolean postOnly) {
            this.postOnly = postOnly;
        }

        public final String getUsernameParameter() {
            return mobileParameter;
        }


}
