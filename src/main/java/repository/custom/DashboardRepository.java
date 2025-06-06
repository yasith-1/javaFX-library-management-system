package repository.custom;

import repository.SuperRepository;

public interface DashboardRepository extends SuperRepository {
    Integer totalBooksCount();
    Integer totalMembersCount();
    Integer totalAuthorsCount();
    Integer totalIssuedBooksCount();
}
