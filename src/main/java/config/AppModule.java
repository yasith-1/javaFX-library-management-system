package config;

import com.google.inject.AbstractModule;
import repository.custom.*;
import repository.custom.impl.*;
import service.custom.*;
import service.custom.impl.*;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
//        Service bindings
        bind(BookService.class).to(BookServiceImpl.class);
        bind(AuthorService.class).to(AuthorServiceImpl.class);
        bind(CategoryService.class).to(CategoryServiceImpl.class);
        bind(DashboardService.class).to(DashboardServiceImpl.class);
        bind(DelayReturnService.class).to(DelayReturnServiceImpl.class);
        bind(FineService.class).to(FineServiceImpl.class);
        bind(IssuedBookService.class).to(IssuedBookServiceImpl.class);
        bind(MemberService.class).to(MemberServiceImpl.class);
        bind(PendingFineService.class).to(PendingFineServiceImpl.class);
        bind(ReturnBookService.class).to(ReturnBookServiceImpl.class);

//        Re pository bindings
        bind(AuthorRepository.class).to(AuthorRepositoryImpl.class);
        bind(BookRepository.class).to(BookRepositoryImpl.class);
        bind(CategoryRepository.class).to(CategoryRepositoryImpl.class);
        bind(DashboardRepository.class).to(DashboardRepositoryImpl.class);
        bind(DelayReturnRepository.class).to(DelayReturnRepositoryImpl.class);
        bind(FineRepository.class).to(FineRepositoryImpl.class);
        bind(IssuedBookRepository.class).to(IssuedBookRepositoryImpl.class);
        bind(MemberRepository.class).to(MemberRepositoryImpl.class);
        bind(PendingFineRepository.class).to(PendingFineRepositoryImpl.class);
        bind(ReturnBookRepository.class).to(ReturnBookRepositoryImpl.class);

    }
}
