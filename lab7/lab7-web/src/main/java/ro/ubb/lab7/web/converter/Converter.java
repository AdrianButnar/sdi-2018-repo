package ro.ubb.lab7.web.converter;


import ro.ubb.lab7.core.model.BaseEntity;
import ro.ubb.lab7.web.dto.BaseDto;

/**
 * Created by radu.
 */

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

