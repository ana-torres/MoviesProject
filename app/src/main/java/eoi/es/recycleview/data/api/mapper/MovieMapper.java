package eoi.es.recycleview.data.api.mapper;

import java.util.ArrayList;
import java.util.List;

import eoi.es.recycleview.data.dto.MovieDTO;
import eoi.es.recycleview.data.entity.Movie;

/**
 * Converite objetos de la API en objetos de nuestra aplicación
 */
public class MovieMapper {

    /**
     * Converite una lista de DTO en una lista de objetos
     *
     * @param dtoList Listado de dtos
     * @return lista de objetos
     */
    public static List<Movie> entityListFrom(List<MovieDTO> dtoList) {

        List<Movie> entities = new ArrayList<>();
        for (MovieDTO dto : dtoList) {
            entities.add(entityFrom(dto));
        }

        return entities;
    }

    public static Movie entityFrom(MovieDTO dto) {
        Movie entity = new Movie();
        entity.setTitle(dto.getTitle());
        entity.setCoverUrl(dto.getPoster_path());
        entity.setYear(dto.getRelease_date());
        entity.setId(dto.getId());

        return entity;
    }

}
