package config;

import com.google.inject.AbstractModule;
import service.custom.BookService;
import service.custom.impl.BookServiceImpl;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BookService.class).to(BookServiceImpl.class);
    }
}
