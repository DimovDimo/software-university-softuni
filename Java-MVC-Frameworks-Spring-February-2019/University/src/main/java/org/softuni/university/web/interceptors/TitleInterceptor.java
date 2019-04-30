package org.softuni.university.web.interceptors;

import org.softuni.university.web.annotations.PageTitle;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TitleInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String title = "University";

        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        } else {
            handlerInstance(handler, modelAndView, title);
        }
    }

    private void handlerInstance(Object handler, ModelAndView modelAndView, String title) {
        if (handler instanceof HandlerMethod) {
            PageTitle methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(PageTitle.class);
            if (methodAnnotation != null) {
                modelAndView
                        .addObject("titleText", title + " - " + methodAnnotation.value());
            }
        }
    }
}
