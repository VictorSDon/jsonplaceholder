package com.donato.jsonplaceholder.repository.jpa;

import com.donato.jsonplaceholder.model.photo.PhotoDomain;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoDomain, Long> {
}
