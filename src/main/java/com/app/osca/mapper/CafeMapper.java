package com.app.osca.mapper;

import com.app.osca.domain.CafeVO;
import com.app.osca.domain.dto.cafe.CafeAdCountDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CafeMapper {

    public List<CafeVO> selectAll();

    public CafeVO select(Long id);

    public List<CafeAdCountDTO> selectByMemberId(Long memberId);

    public Integer insert(CafeVO cafeVO);
}
