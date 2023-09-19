package dev.sandeep.productservice.repositories;

import dev.sandeep.productservice.models.Category;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Lazy
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
