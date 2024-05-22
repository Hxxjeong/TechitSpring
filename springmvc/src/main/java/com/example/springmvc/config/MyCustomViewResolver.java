package com.example.springmvc.config;

import com.example.springmvc.view.MyCustomView;
import lombok.Setter;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

@Setter
public class MyCustomViewResolver implements ViewResolver, Ordered {
    private int order;

    @Override
    public int getOrder() {
        return this.order;
    }

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        if(viewName.startsWith("my-prefix")) {
            return new MyCustomView();
        }
        return null;    // 다음 view resolver가 처리
    }
}
