package ro.ubb.lab11.web.converter;

import ro.ubb.lab11.core.model.BaseEntity;
import ro.ubb.lab11.web.dto.BaseDto;

/**
 * Created by radu.
 */

public interface ConverterBaseEntity<Model extends BaseEntity<Long>, Dto extends BaseDto>
        extends Converter<Model, Dto> {

}

