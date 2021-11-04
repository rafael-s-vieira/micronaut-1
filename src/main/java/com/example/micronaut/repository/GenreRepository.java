package com.example.micronaut.repository;

import com.example.micronaut.domain.Genre;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.exceptions.DataAccessException;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JdbcRepository(dialect = Dialect.ORACLE)
public interface GenreRepository extends PageableRepository<Genre, Long> {

    Genre save(@NonNull @NotBlank String name);

    @Transactional
    default Genre saveWithException(@NonNull @NotBlank String name) {
        save(name);
        throw new DataAccessException("test exception");
    }

    int update(@NonNull @NotNull @Id Long id, @NonNull @NotBlank String name);
}

// SELECT genre_."ID",genre_."NAME" FROM "GENRE" genre_ ORDER BY genre_.name ASC FETCH NEXT 5 ROWS ONLY
// SELECT genre_.`id`,genre_.`name` FROM `genre` genre_ ORDER BY genre_.name ASC LIMIT 5