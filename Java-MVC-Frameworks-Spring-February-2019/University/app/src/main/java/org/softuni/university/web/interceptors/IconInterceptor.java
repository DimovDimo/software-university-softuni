package org.softuni.university.web.interceptors;

import org.softuni.university.constants.InterceptorConstants;
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

        String iconLink = InterceptorConstants.ICON_LINK;

        if (modelAndView != null) {
            modelAndView.addObject(InterceptorConstants.ICON_LINK_MODEL_AND_VIEW, iconLink);
        }
    }
}
