package utils;

import api.dto.store.response.OrderResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public abstract class DtoMapper {

    private DtoMapper() {
        // Utility class, no instances
    }

    /**
     * WRITE_DATES_AS_TIMESTAMPS = false → serialize as ISO-8601 string, not numeric timestamp.
     * WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS = true → include full nanoseconds precision.
     */
    public static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, true);

    /**
     * Converts one DTO to another using Jackson.
     *
     * @param source      source object
     * @param targetClass target class type
     * @param <T>         generic target type
     * @return instance of target class with copied values
     */
    public static <T> T map(Object source, Class<T> targetClass) {
        return mapper.convertValue(source, targetClass);
    }
}
