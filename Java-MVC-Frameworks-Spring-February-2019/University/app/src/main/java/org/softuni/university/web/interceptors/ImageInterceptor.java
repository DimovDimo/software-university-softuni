package org.softuni.university.web.interceptors;

import org.softuni.university.constants.InterceptorConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Component
public class ImageInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        }

        String imageLink = getImageLink();

        modelAndView.addObject(InterceptorConstants.IMAGE_LINK_MODEL_AND_VIEW, imageLink);
    }

    private String getImageLink() {
        String[] images = getImages();
        Random Dice = new Random();
        int n = Dice.nextInt(images.length);

        return images[n];
    }

    private String[] getImages() {
        return InterceptorConstants.GET_IMAGES;
    }
}
