package org.softuni.university.web.interceptors;

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

        modelAndView.addObject("imageLink", imageLink);
    }

    private String getImageLink() {
        String[] images = getImages();
        Random Dice = new Random();
        int n = Dice.nextInt(images.length);

        return images[n];
    }

    private String[] getImages() {
        return new String[]{
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887807/study-425634_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887807/globe-1130870_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887807/prague-980732_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887807/library-74038_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887806/george-peabody-bucherei-1629308_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887807/owl-47526_640.png",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887807/harewood-house-3709909_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887807/map-of-the-world-60526_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887807/narrative-794978_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887807/library-419254_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887806/abbotsford-house-3530245_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887806/globe-967304_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887807/knowledge-1052014_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887807/knowledge-1052013_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887806/book-863418_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887806/books-1702790_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887806/books-1281581_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887806/architecture-3536005_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887806/book-3510326_640.jpg",
                "https://res.cloudinary.com/dimo-cloud/image/upload/v1554887806/book-3294950_640.jpg"
        };
    }
}
