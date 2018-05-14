package org.fkit.mapper;

import org.fkit.domain.Person;

public interface PersonMapper {
	Person selectPersonById(Integer id);
}
