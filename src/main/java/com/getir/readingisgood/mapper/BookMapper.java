package com.getir.readingisgood.mapper;

import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.input.BookAddInput;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book convertToBook(BookAddInput bookAddInput);

    BookAddInput convertToBookDto(Book book);

    List<Book> convertToListBook(List<BookAddInput> book);

    List<BookAddInput> convertToListBookDto(List<Book> book);

}
