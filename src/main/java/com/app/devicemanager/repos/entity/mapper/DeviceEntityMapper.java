package com.app.devicemanager.repos.entity.mapper;

import com.app.devicemanager.domain.State;
import com.app.devicemanager.dto.DeviceDTO;
import com.app.devicemanager.repos.entity.DeviceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper (componentModel = "spring")
public interface DeviceEntityMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", source = "state", qualifiedByName = "toState")
    DeviceEntity toDeviceEntity(DeviceDTO deviceDTO);

    DeviceDTO toDeviceDTO(DeviceEntity deviceEntity);

    @Named("toState")
    default State toState(String state){
        return State.valueOf(state);
    }
}
