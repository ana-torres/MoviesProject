package eoi.es.recycleview.data.api.mapper;

import java.util.ArrayList;
import java.util.List;

import eoi.es.recycleview.data.dto.CastDTO;
import eoi.es.recycleview.data.entity.Cast;

public class CastMapper {

    public static List<Cast> entityListFrom(List<CastDTO> dtoList) {

        List<Cast> entityList = new ArrayList<>();
        for (CastDTO dto : dtoList) {

            entityList.add(entityFrom(dto));

        }
        return entityList;

    }

    public static Cast entityFrom(CastDTO dto) {

        Cast entity = new Cast();

        entity.setCharacterName(dto.getCharacter());
        entity.setName(dto.getName());
        entity.setProfileImageUrl(dto.getProfile_path());

        return entity;
    }
}
