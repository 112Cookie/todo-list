package com.cookie.todolist.filter;


import com.cookie.todolist.controller.CredentialController;
import com.cookie.todolist.vo.Credential;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;

import java.io.IOException;

import static com.cookie.todolist.utils.ObjectComparator.compareObjects;

@WebFilter(urlPatterns = "/todo/*")
public class CredentialsFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(CredentialsFilter.class);

    @Value("${keepie.server}")
    private String keepieServer;

    @Value("${receive.url}")
    private String receiveUrl;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        logger.info("Entering doFilter");
        callKeepieService();
        Credential credential = CredentialController.getCredential();
        Credential requestCredential = new Credential();
        requestCredential.setusername(httpRequest.getHeader("username"));
        requestCredential.setpassword(httpRequest.getHeader("password"));
        try {
            if(compareObjects(credential,requestCredential)){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                httpResponse.getWriter().write("Access Denied");
                return; //
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public String callKeepieService() {
        String url = keepieServer+"/sendSecretToMe";
        String requestBody = "{\"receive_url\":\""+receiveUrl+"\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        String response = null;
        RestTemplate restTemplate = new RestTemplate();
        boolean success = false;
        while (!success) {
            try {
                response = restTemplate.postForObject(url, requestEntity,String.class);
                if (response == null || response.isEmpty()){
                    success = false;
                }else{
                    logger.info("Received response from Keepie Server: " + response);
                    success = true;
                }
            } catch (Exception e) {
                logger.error("Error occurred: " + e.getMessage());
                try {
                    Thread.sleep(1000); // 1秒后重试
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return response;
    }
}
