package org.softuni.university.web.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class IconInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) throws Exception {

        String iconLink = "https://res.cloudinary.com/dimo-cloud/image/upload/v1554834927/icon2.png";

        if (modelAndView != null) {
            modelAndView.addObject("iconLink", iconLink);
        }
    }
}
