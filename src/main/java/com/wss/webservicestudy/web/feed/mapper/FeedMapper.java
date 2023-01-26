package com.wss.webservicestudy.web.feed.mapper;

import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.feed.dto.FeedRespDto;
import com.wss.webservicestudy.web.feed.entity.Feed;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FeedMapper {
    FeedMapper INSTANCE = Mappers.getMapper(FeedMapper.class);

    Feed toFeed(CreateFeedDto createFeedDto);

    FeedRespDto toFeedRespDto(Feed feed);
}
